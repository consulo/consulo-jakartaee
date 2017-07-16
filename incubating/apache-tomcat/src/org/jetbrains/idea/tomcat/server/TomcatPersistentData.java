package org.jetbrains.idea.tomcat.server;

import org.jdom.Element;
import org.jetbrains.idea.tomcat.TomcatUtil;
import com.intellij.javaee.appServerIntegrations.DefaultPersistentData;
import com.intellij.javaee.oss.server.JavaeePersistentDataWithBase;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.openapi.util.io.FileUtil;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
@Deprecated
public class TomcatPersistentData extends JavaeePersistentDataWithBase {

  public TomcatPersistentData() {
    HOME = TomcatUtil.getDefaultLocation();
  }

  public void readExternal(final Element element) throws InvalidDataException {
    CompatibleData compatibleData = new CompatibleData();
    compatibleData.readExternal(element);
    HOME = FileUtil.toSystemDependentName(compatibleData.CATALINA_HOME);
    BASE = FileUtil.toSystemDependentName(compatibleData.CATALINA_BASE);
  }

  @Override
  public void writeExternal(Element element) throws WriteExternalException {
    CompatibleData compatibleData = new CompatibleData();
    compatibleData.CATALINA_HOME = FileUtil.toSystemIndependentName(HOME);
    compatibleData.CATALINA_BASE = FileUtil.toSystemIndependentName(BASE);
    compatibleData.writeExternal(element);
  }

  private static class CompatibleData extends DefaultPersistentData {

    public String CATALINA_HOME = "";
    public String CATALINA_BASE = "";
  }
}
