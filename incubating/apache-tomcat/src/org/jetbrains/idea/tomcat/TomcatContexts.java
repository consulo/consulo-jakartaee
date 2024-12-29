package org.jetbrains.idea.tomcat;

import com.intellij.execution.ExecutionException;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.io.FileUtilRt;
import org.jdom.Document;
import org.jdom.Element;
import org.jetbrains.annotations.NonNls;
import jakarta.annotation.Nullable;
import org.jetbrains.idea.tomcat.server.TomcatLocalModel;

import java.io.File;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomcatContexts {

  private static final Logger LOG = Logger.getInstance("#" + TomcatContexts.class.getName());

  @NonNls public static final String CONTEXT_ELEMENT_NAME = "Context";
  @NonNls public static final String PATH_ATTR = "path";
  @NonNls public static final String DOC_BASE_ATTR = "docBase";
  @NonNls public static final String ROOT_DIR_NAME = "ROOT";
  private static final String CONTEXT_PATH_PREFIX = "/";
  private static final String XML_EXTENSION = "xml";

  private final TomcatServerXmlWrapper myServerXml;
  private final Element myLocalHost;
  private final List<TomcatContextItem> myItems;

  public TomcatContexts(TomcatLocalModel tomcatModel, String baseDirPath) throws ExecutionException {

    myServerXml = new TomcatServerXmlWrapper(baseDirPath);

    myItems = new ArrayList<>();

    myLocalHost = myServerXml.findLocalHost();
    List<Element> contextElements = myLocalHost.getChildren(CONTEXT_ELEMENT_NAME);
    for (final Element contextElement : contextElements) {
      myItems.add(new TomcatContextItem(contextElement, contextElement.getAttributeValue(PATH_ATTR)) {

        @Override
        public void remove() throws ExecutionException {
          myLocalHost.removeContent(contextElement);
          myServerXml.save();
        }
      });
    }

    if (tomcatModel != null && tomcatModel.isVersion5OrHigher()) {
      File hostDir = new File(TomcatUtil.hostDir(baseDirPath));

      File[] contextFiles = hostDir.listFiles();

      if (contextFiles != null) {
        for (final File contextFile : contextFiles) {
          if (FileUtilRt.extensionEquals(contextFile.getName(), XML_EXTENSION)) {
            Document document;
            try {
              document = TomcatUtil.loadXMLFile(contextFile);
            }
            catch (ExecutionException e) {
              LOG.info(e);
              continue;
            }
            Element rootElement = document.getRootElement();
            if (CONTEXT_ELEMENT_NAME.equals(rootElement.getName())) {
              String path = FileUtil.getNameWithoutExtension(contextFile);
              if (ROOT_DIR_NAME.equals(path)) {
                path = "";
              }
              path = CONTEXT_PATH_PREFIX + path;
              myItems.add(new TomcatContextItem(rootElement, path) {

                @Override
                public void remove() throws ExecutionException {
                  if (!contextFile.delete()) {
                    throw new ExecutionException(TomcatBundle.message("exception.text.cannot.delete.file", contextFile.getAbsolutePath()));
                  }
                }
              });
            }
          }
        }
      }
    }
  }

  public List<TomcatContextItem> getItems() {
    return myItems;
  }

  @Nullable
  public Element findContextByPath(String contextPath) {
    if ("".equals(contextPath)) {
      contextPath = CONTEXT_PATH_PREFIX;
    }
    for (TomcatContextItem item : myItems) {
      if (contextPath.equals(item.getPath())) {
        return item.getElement();
      }
    }
    return null;
  }

  public Collection<String> getContextPaths() {
    Set<String> result = new HashSet<>();
    for (TomcatContextItem item : myItems) {
      String contextPath = item.getPath();
      if (contextPath != null) {
        result.add(contextPath);
      }
    }
    return result;
  }

  public void addOrRemoveContextElementInServerXml(String contextPath, @Nullable Element newContext) throws ExecutionException {
    Element oldContext = findContextByPath(contextPath);
    if (oldContext != null) {
      myLocalHost.removeContent(oldContext);
    }
    if (newContext != null) {
      myLocalHost.addContent(newContext.clone());
    }

    myServerXml.save();
  }
}
