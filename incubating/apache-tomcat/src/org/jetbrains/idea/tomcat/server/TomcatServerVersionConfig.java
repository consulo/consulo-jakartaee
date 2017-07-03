package org.jetbrains.idea.tomcat.server;

import java.io.File;
import java.io.IOException;

import org.jetbrains.annotations.NotNull;
import com.intellij.javaee.oss.server.JavaeePersistentData;
import com.intellij.javaee.oss.server.JavaeeServerVersionConfig;
import com.intellij.javaee.oss.server.JavaeeServerVersionDescriptor;
import com.intellij.javaee.oss.util.VersionUtil;
import com.intellij.openapi.roots.libraries.JarVersionDetectionUtil;
import com.intellij.openapi.util.text.StringUtil;

/**
 * @author michael.golubev
 */
public class TomcatServerVersionConfig extends JavaeeServerVersionConfig {

  private static final Factory<JavaeePersistentData> ourFactory;

  static {
    ourFactory = new Factory<JavaeePersistentData>() {

      @NotNull
      public TomcatServerVersionConfig createConfig(JavaeePersistentData data) {
        return new TomcatServerVersionConfig();
      }
    };
  }

  private TomcatServerVersionConfig() {

  }

  @Override
  protected JavaeeServerVersionDescriptor getVersionDescriptor(String home) throws Exception {
    File catalinaJar = new File(home, "lib/catalina.jar");
    boolean isServerLib = false;
    if (!catalinaJar.exists()) {
      catalinaJar = new File(home, "server/lib/catalina.jar");
      isServerLib = true;
      if (!catalinaJar.exists()) {
        throw new IOException("Can't find catalina.jar");
      }
    }

    String version;
    try {
      String versionProperty
        = StringUtil.notNullize(VersionUtil.readJarProperty(catalinaJar, "org/apache/catalina/util/ServerInfo.properties", "server.info"));
      version = StringUtil.trimStart(versionProperty, "Apache Tomcat/");
      if (StringUtil.isEmpty(version)) {
        version = JarVersionDetectionUtil.getImplementationVersion(catalinaJar);
      }
      if (StringUtil.isEmpty(version)) {
        throw new IOException("Version properties are empty");
      }
    }
    catch (IOException e) {
      if (!isServerLib) {
        throw e;
      }
      version = "4.0.x";
    }
    return new JavaeeServerVersionDescriptor(version, catalinaJar);
  }

  public static String get(JavaeePersistentData persistentData) throws Exception {
    return get(ourFactory, persistentData);
  }
} 
