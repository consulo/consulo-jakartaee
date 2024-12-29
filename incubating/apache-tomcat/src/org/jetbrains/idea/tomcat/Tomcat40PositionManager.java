/*
 * Copyright 2000-2005 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jetbrains.idea.tomcat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.annotation.Nonnull;

import org.jetbrains.annotations.NonNls;
import com.intellij.debugger.NoDataException;
import com.intellij.debugger.SourcePosition;
import com.intellij.debugger.engine.DebugProcess;
import com.intellij.debugger.requests.ClassPrepareRequestor;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.javaee.deployment.JspDeploymentManager;
import com.intellij.javaee.oss.server.DisposablePositionManager;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileAdapter;
import com.intellij.openapi.vfs.VirtualFileEvent;
import com.intellij.openapi.vfs.VirtualFileListener;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiManager;
import com.intellij.util.PathUtil;
import com.intellij.util.containers.ContainerUtil;
import consulo.internal.com.sun.jdi.AbsentInformationException;
import consulo.internal.com.sun.jdi.Location;
import consulo.internal.com.sun.jdi.ReferenceType;
import consulo.internal.com.sun.jdi.request.ClassPrepareRequest;
import consulo.javaee.jsp.JspFileType;
import consulo.javaee.jsp.JspxFileType;
import consulo.javaee.module.extension.JavaEEModuleExtension;

/**
 * Created by IntelliJ IDEA.
 * User: lex
 * Date: Apr 6, 2004
 * Time: 2:46:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tomcat40PositionManager implements DisposablePositionManager {
  private static final Logger LOG = Logger.getInstance("#com.intellij.j2ee.web.tomcat.Tomcat40PositionManager");
  private static final Set<FileType> ourFileTypes = ContainerUtil.immutableSet(JspFileType.INSTANCE, JspxFileType.INSTANCE);

  /**
     The pattern below matches blocks of code like this (Tomcat comments in the generated code):

           // begin [file="/jsp/snp/snoop.jsp";from=(27,16);to=(27,41)]
                out.print( request.getServerName() );
                {other code here}
           // end
   */
  @NonNls private static final String PATTERN_TEXT =
    "\\/\\/\\s*begin\\s+\\[file=\"(.*?)\";from=\\((\\d+),(\\d+)\\);to=\\((\\d+),(\\d+)\\)\\][\\n\\r]*(.*?)[\\n\\r]*\\s*\\/\\/\\s*end";

  private static final int JSP_SOURCE_GROUP  = 1;
  private static final int FROM_LINE_GROUP   = 2;
  private static final int FROM_COLUMN_GROUP = 3;
  private static final int TO_LINE_GROUP     = 4;
  private static final int TO_COLUMN_GROUP   = 5;
  private static final int CODE_GROUP        = 6;

  private final DebugProcess myDebugProcess;
  private final Pattern myPattern;
  private final String myGeneratedFilesPath;

  private final Map<ServletPosition, JspPosition>     myServletToJspPositionMap = new HashMap<>();
  private final Map<JspPosition,     ServletPosition> myJspToServletPositionMap = new HashMap<>();

  private VirtualFile myGeneratedFilesDirectory;
  private VirtualFileListener myFileListener;

  private final JspDeploymentManager myJavaClassHelper;
  @NonNls private final String APACHE_PACKAGE = "org.apache.jsp";

  private final Map<VirtualFile, Long>    myServletTimeStamps = new com.intellij.util.containers.HashMap<>();
  private final Map<VirtualFile, PsiFile> myServletToPsiFile  = new com.intellij.util.containers.HashMap<>();
  @NonNls protected static final String JSP_SUFFIX = "$jsp";
  private final JavaEEModuleExtension[] myScope;
  private LocalFileSystem.WatchRequest myGeneratedPathsWatchRequest;

  public Tomcat40PositionManager(DebugProcess debugProcess, @Nonnull String generatedFilePath, JavaEEModuleExtension[] scopeFacets) {
    myScope = scopeFacets;
    myDebugProcess = debugProcess;
    myGeneratedFilesPath = generatedFilePath;
    myPattern = Pattern.compile(PATTERN_TEXT, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    myJavaClassHelper = JspDeploymentManager.getInstance();

    runWriteAction(() -> {
      LocalFileSystem fs = LocalFileSystem.getInstance();
      myGeneratedFilesDirectory = fs.refreshAndFindFileByPath(myGeneratedFilesPath);
    });

    if(myGeneratedFilesDirectory == null) {
      debugProcess.printToConsole(TomcatBundle.message("message.text.cannot.handle.breakpoint.path.not.found", myGeneratedFilesPath));
    }

    invoke(() -> {
      if (myGeneratedFilesDirectory != null) {
        final FileTypeManager typeManager = FileTypeManager.getInstance();
        processDirectory(myGeneratedFilesDirectory, typeManager);

        myFileListener = new UpdateListener();
        VirtualFileManager.getInstance().addVirtualFileListener(myFileListener);
        myGeneratedPathsWatchRequest = LocalFileSystem.getInstance().addRootToWatch(myGeneratedFilesDirectory.getPath(), true);
      }
    });

    refreshGeneratedFilesDirectory();
  }

  private void refreshGeneratedFilesDirectory() {
    runWriteAction(() -> {
      if(myGeneratedFilesDirectory != null) {
        myGeneratedFilesDirectory.refresh(false, true);
      }
    });
  }

  private void runWriteAction(final Runnable r) {
    invoke(() -> ApplicationManager.getApplication().runWriteAction(r));
  }

  private void invoke(Runnable runnable) {
    try {
      ApplicationManager.getApplication().invokeAndWait(runnable, ModalityState.NON_MODAL);
    }
    catch (Exception e) {
      LOG.debug(e);
    }
  }

  private void processDirectory(VirtualFile dir, FileTypeManager typeManager) {
    final VirtualFile[] children = dir.getChildren();
    for (int idx = 0; idx < children.length; idx++) {
      final VirtualFile child = children[idx];
      if (child.isDirectory()) {
        processDirectory(child, typeManager);
      }
      else {
        if (JavaFileType.INSTANCE.equals(child.getFileType()) && child.getNameWithoutExtension().endsWith(JSP_SUFFIX)) {
          parseGeneratedServlet(child);
        }
      }
    }
  }

  private static boolean isModified(Map servletTimeStamps, VirtualFile file) {
    Long ts = (Long)servletTimeStamps.get(file);
    return (ts.longValue() != file.getTimeStamp());
  }

  private PsiFile getPsiFile(VirtualFile servletFile) {
    if (!servletFile.isValid()) {
      if (myServletTimeStamps.containsKey(servletFile)) {
        myServletTimeStamps.remove(servletFile);
      }
      if (myServletToPsiFile.containsKey(servletFile)) {
        myServletToPsiFile.remove(servletFile);
      }
      return null;
    }
    if (myServletTimeStamps.containsKey(servletFile)) {
      if (!isModified(myServletTimeStamps, servletFile)) {
        return myServletToPsiFile.get(servletFile);
      }
    }
    // either new or modified file
    if(FileDocumentManager.getInstance().getDocument(servletFile) != null){
      final String servletText = FileDocumentManager.getInstance().getDocument(servletFile).getText();
      PsiFile servletPsiFile = PsiFileFactory.getInstance(myDebugProcess.getProject()).createFileFromText(servletFile.getName(), servletText);
      myServletToPsiFile.put(servletFile, servletPsiFile);
      myServletTimeStamps.put(servletFile, new Long(servletFile.getTimeStamp()));
      return servletPsiFile;
    }
    else{
      myDebugProcess.printToConsole(TomcatBundle.message("message.text.cannot.read.from.file.with.exception", servletFile.getPath(), ""));
      return null;
    }
  }

  private String findClassQualifiedName(final VirtualFile file,
                                        final int startOffset,
                                        final int endOffset) {
    return ApplicationManager.getApplication().runReadAction(new Computable<String>() {
      public String compute() {
        PsiFile psiFile = getPsiFile(file);

        if(psiFile == null) return null;


       PsiElement element = psiFile.findElementAt(startOffset);
        while (!(element instanceof PsiClass)) {
          if (element == null) {
            break;
          }
          if (element instanceof PsiComment) {
            final TextRange range = element.getTextRange();
            if (range.getStartOffset() <= startOffset && range.getEndOffset() >= endOffset) {
              break;
            }
          }
          element = element.getParent();
        }
        if (!(element instanceof PsiClass)) {
          return null;
        }
        return ((PsiClass)element).getQualifiedName();
      }
    });
  }


  private void parseGeneratedServlet(final VirtualFile servletFile) {
    ApplicationManager.getApplication().runReadAction(() -> {
      Project project = myDebugProcess.getProject();
      if (project.isDisposed()) {
        dispose();
        return;
      }
      PsiFile psiFile = PsiManager.getInstance(project).findFile(servletFile);
      if(psiFile == null) return;

      String servletText = psiFile.getText();

      final Document document = EditorFactory.getInstance().createDocument(servletText);
      Matcher matcher = myPattern.matcher(servletText);
      while (matcher.find()) {
        String jspFilePath = PathUtil.getCanonicalPath(matcher.group(JSP_SOURCE_GROUP));
        int startServletLine = document.getLineNumber(servletText.indexOf(matcher.group(CODE_GROUP), matcher.start())) + 1;
        int startJspLine = Integer.parseInt(matcher.group(FROM_LINE_GROUP)) + 1;
        int endJspLine = Integer.parseInt(matcher.group(TO_LINE_GROUP)) + 1;

        if(startServletLine + (endJspLine - startJspLine) >= document.getLineCount()) {
          endJspLine = startJspLine + (document.getLineCount() - startServletLine - 1);
        }

        for (int jspLine = startJspLine, servletLine = startServletLine; jspLine <= endJspLine; jspLine++, servletLine++) {
          JspPosition jspPosition = new JspPosition(jspFilePath, jspLine);
          String classQualifiedName = findClassQualifiedName(servletFile, document.getLineStartOffset(servletLine), document.getLineEndOffset(servletLine));
          if(classQualifiedName != null) {
            ServletPosition servletPosition = new ServletPosition(servletFile, classQualifiedName, servletLine);
            putMapping(servletPosition, jspPosition);
          }
        }
      }
    });
  }

  @Override
  public void dispose() {
    if (myGeneratedPathsWatchRequest != null) {
      LocalFileSystem.getInstance().removeWatchedRoot(myGeneratedPathsWatchRequest);
      myGeneratedPathsWatchRequest = null;
    }
    if (myFileListener != null) {
      VirtualFileManager.getInstance().removeVirtualFileListener(myFileListener);
      myFileListener = null;
    }
  }

  private static class ServletPosition {
    private final VirtualFile myFile;
    private final String myClassName;
    private final int myLine;

    public ServletPosition(String className, int line) {
      myFile = null;
      myClassName = className;
      myLine = line;
    }

    public ServletPosition(@Nonnull VirtualFile file, String className, int line) {
      myFile = file;
      myClassName = className;
      myLine = line;
    }

    public VirtualFile getFile() {
      return myFile;
    }

    public String getMyClassName() {
      return myClassName;
    }

    public int getLine() {
      return myLine;
    }

    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof ServletPosition)) return false;

      final ServletPosition servletPosition = (ServletPosition)o;

      if (myLine != servletPosition.myLine) return false;
      if (!myClassName.equals(servletPosition.myClassName)) return false;

      return true;
    }

    public int hashCode() {
      int result;
      result = myClassName.hashCode();
      result = 29 * result + myLine;
      return result;
    }
  }

  private static class JspPosition {
    private final String myFileName;
    private final int myLine;

    public JspPosition(String fileName, int line) {
      myFileName = fileName;
      myLine = line;
    }

    public String getFileName() {
      return myFileName;
    }

    public int getLine() {
      return myLine;
    }

    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof JspPosition)) return false;

      final JspPosition javaPosition = (JspPosition)o;

      if (myLine != javaPosition.myLine) return false;
      if (myFileName != null ? !myFileName.equals(javaPosition.myFileName) : javaPosition.myFileName != null) return false;

      return true;
    }

    public int hashCode() {
      int result;
      result = (myFileName != null ? myFileName.hashCode() : 0);
      result = 29 * result + myLine;
      return result;
    }
  }

  private void putMapping(ServletPosition servletPosition, JspPosition javaPosition) {
    myServletToJspPositionMap.put(servletPosition, javaPosition);
    myJspToServletPositionMap.put(javaPosition, servletPosition);
  }

  private JspPosition findJspPosition(ServletPosition servletPosition) {
    return myServletToJspPositionMap.get(servletPosition);
  }

  private ServletPosition findServletPosition(JspPosition jspPosition) {
    return myJspToServletPositionMap.get(jspPosition);
  }


  private void clearMappings(VirtualFile servletFile) {
    Set<ServletPosition> servletPositions = myServletToJspPositionMap.keySet();
    for (Iterator<ServletPosition> iterator = servletPositions.iterator(); iterator.hasNext();) {
      ServletPosition servletPosition = iterator.next();
      if(servletPosition.getFile().equals(servletFile)) {
        JspPosition jspPosition = myServletToJspPositionMap.remove(servletPosition);
        myJspToServletPositionMap.remove(jspPosition);
      }
    }
  }

  private class UpdateListener extends VirtualFileAdapter {
    private final FileTypeManager myTypeManager;

    public UpdateListener() {
      myTypeManager = FileTypeManager.getInstance();
    }

    public void fileCreated(@Nonnull VirtualFileEvent event) {
      VirtualFile file = event.getFile();
      if (isGenerated(file)) {
        if (file.isDirectory()) {
          checkCreatedDir(file);
        }
        else {
          if (isGeneratedServlet(file)) {
            parseGeneratedServlet(file);
          }
        }
      }
    }

    public void fileDeleted(@Nonnull VirtualFileEvent event) {
      VirtualFile file = event.getFile();
      if (isGenerated(file) && isGeneratedServlet(file)) {
        clearMappings(file);
      }
    }

    public void contentsChanged(@Nonnull VirtualFileEvent event) {
      VirtualFile file = event.getFile();
      if (isGenerated(file) && isGeneratedServlet(file)) {
        parseGeneratedServlet(file);
      }
    }

    private void checkCreatedDir(VirtualFile dir) {
      VirtualFile[] children = dir.getChildren();
      for (int idx = 0; idx < children.length; idx++) {
        VirtualFile child = children[idx];
        if (child.isDirectory()) {
          checkCreatedDir(child);
        }
        else {
          if (isGeneratedServlet(child)) {
            parseGeneratedServlet(child);
          }
        }
      }
    }

    private boolean isGenerated(VirtualFile file) {
      return VfsUtil.isAncestor(myGeneratedFilesDirectory, file, true);
    }

    private boolean isGeneratedServlet(VirtualFile file) {
      if (!JavaFileType.INSTANCE.equals(file.getFileType())) {
        return false;
      }
      return file.getName().indexOf(JSP_SUFFIX) >= 0;
    }
  }


  public SourcePosition getSourcePosition(final Location location) throws NoDataException {
    SourcePosition sourcePosition = null;

    if(myGeneratedFilesDirectory == null) return null;
    JspPosition jspPosition = findJspPosition(new ServletPosition(location.declaringType().name(), location.lineNumber()));
    if(jspPosition != null) {
      PsiFile jspSource = myJavaClassHelper.getDeployedJspSource(jspPosition.getFileName(), myDebugProcess.getProject(), myScope);
      if(jspSource != null) {
        sourcePosition =  SourcePosition.createFromLine(jspSource, jspPosition.getLine() - 1);
      }
    }

    if(sourcePosition == null) throw NoDataException.INSTANCE;

    return sourcePosition;
  }

  @Nonnull
  public List<ReferenceType> getAllClasses(@Nonnull SourcePosition classPosition) throws NoDataException {
    final FileType fileType = classPosition.getFile().getFileType();
    if(fileType != JspFileType.INSTANCE && fileType != JspxFileType.INSTANCE) {
      throw NoDataException.INSTANCE;
    }

    List<ReferenceType> result = new ArrayList<>();

    List<ReferenceType> allClasses = myDebugProcess.getVirtualMachineProxy().allClasses();
    for (Iterator<ReferenceType> iterator = allClasses.iterator(); iterator.hasNext();) {
      ReferenceType referenceType = iterator.next();
      if(referenceType.name().startsWith(APACHE_PACKAGE)) {
        if(locationsOfLine(referenceType, classPosition).size() > 0) {
          result.add(referenceType);
        }
      }
    }
    return result;
  }

  @Nonnull
  public List<Location> locationsOfLine(@Nonnull ReferenceType type, @Nonnull final SourcePosition position) throws NoDataException {
    final FileType fileType = position.getFile().getFileType();
    if(fileType != JspFileType.INSTANCE && fileType != JspxFileType.INSTANCE) throw NoDataException.INSTANCE;

    String deployedJspName = ApplicationManager.getApplication().runReadAction(new Computable<String>(){
      public String compute() {
        return myJavaClassHelper.getSourceJspDeployment(position.getFile());
      }
    });

    JspPosition jspPosition = new JspPosition(deployedJspName, position.getLine() + 1);

    ServletPosition servletPosition = findServletPosition(jspPosition);
    if(servletPosition != null) {
      try {
        return type.locationsOfLine(servletPosition.getLine());
      }
      catch (AbsentInformationException e) {
      }
    }

    return Collections.emptyList();
  }

  public ClassPrepareRequest createPrepareRequest(@Nonnull final ClassPrepareRequestor requestor, @Nonnull final SourcePosition position)
    throws NoDataException {
    final FileType fileType = position.getFile().getFileType();
    if(fileType != JspFileType.INSTANCE && fileType != JspxFileType.INSTANCE) {
      throw NoDataException.INSTANCE;
    }

    return myDebugProcess.getRequestsManager().createClassPrepareRequest(new ClassPrepareRequestor() {
      public void processClassPrepare(DebugProcess debuggerProcess, ReferenceType referenceType) {
        refreshGeneratedFilesDirectory();
        try {
          if(locationsOfLine(referenceType, position).size() > 0) {
            requestor.processClassPrepare(debuggerProcess, referenceType);
          }
        }
        catch (NoDataException e) {
        }
      }
    }, APACHE_PACKAGE + ".*");
  }

  @Nonnull
  @Override
  public Set<? extends FileType> getAcceptedFileTypes() {
    return ourFileTypes;
  }
}
