package org.jetbrains.idea.tomcat;

import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.util.PathUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author nik
 */
public class TomcatSystemBaseDirManager {
  private Set<String> myUsedNames = new HashSet<>();
  private File myParentDirectory;

  public static TomcatSystemBaseDirManager getInstance() {
    return ServiceManager.getService(TomcatSystemBaseDirManager.class);
  }

  public TomcatSystemBaseDirManager() {
    final File parent = getParentDirectory();
    final File[] children = parent.listFiles();
    if (children != null) {
      for (File child : children) {
        myUsedNames.add(child.getName());
      }
    }
  }

  private File getParentDirectory() {
    if (myParentDirectory != null) {
      return myParentDirectory;
    }
    final File dir = new File(PathManager.getSystemPath(), "tomcat");
    try {
      myParentDirectory = dir.getCanonicalFile();
      return myParentDirectory;
    }
    catch (IOException e) {
      return dir;
    }
  }

  public File getDirectory(String directoryName) {
    return new File(getParentDirectory(), directoryName);
  }

  public String suggestNewDirectoryName(String prefix) {
    final String baseName = PathUtil.suggestFileName(prefix);
    final File parent = getParentDirectory();

    int i = 1;
    String nameCandidate = baseName;
    while (myUsedNames.contains(nameCandidate) || new File(parent, nameCandidate).exists()) {
      i++;
      nameCandidate = baseName + "_" + i;
    }
    myUsedNames.add(nameCandidate);
    return nameCandidate;
  }
}
