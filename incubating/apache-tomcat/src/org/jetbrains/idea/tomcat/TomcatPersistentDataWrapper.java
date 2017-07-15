package org.jetbrains.idea.tomcat;

import com.intellij.javaee.appServerIntegrations.ApplicationServer;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.idea.tomcat.server.TomcatPersistentData;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomcatPersistentDataWrapper {

  private final TomcatPersistentData myTomcatData;

  public TomcatPersistentDataWrapper(@NotNull ApplicationServer applicationServer) {
    myTomcatData = (TomcatPersistentData)applicationServer.getPersistentData();
  }

  public String getHomeDirectory() {
    return StringUtil.trimEnd(FileUtil.toSystemDependentName(myTomcatData.HOME), File.separator);
  }

  public String getSourceBaseDirectoryPath() {
    String baseDir = myTomcatData.BASE;
    if (StringUtil.isNotEmpty(baseDir)) {
      return FileUtil.toSystemDependentName(baseDir);
    }
    return getHomeDirectory();
  }

  public int getSourceLocalPort() {
    return new TomcatServerXmlWrapper(getSourceBaseDirectoryPath()).getHttpPort();
  }

  public boolean hasSourceLocalPort() {
    return new TomcatServerXmlWrapper(getSourceBaseDirectoryPath()).hasSourceLocalPort();
  }
}
