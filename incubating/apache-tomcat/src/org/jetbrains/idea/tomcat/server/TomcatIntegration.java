package org.jetbrains.idea.tomcat.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import jakarta.annotation.Nonnull;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.idea.tomcat.TomcatBundle;
import org.jetbrains.idea.tomcat.TomcatConstants;
import org.jetbrains.idea.tomcat.TomcatStartupPolicy;
import org.jetbrains.idea.tomcat.TomcatUrlMapping;
import org.jetbrains.idea.tomcat.descriptor.TomcatContextDescriptor;
import org.jetbrains.idea.tomcat.model.TomcatContextRoot;
import com.intellij.javaee.appServerIntegrations.ApplicationServerUrlMapping;
import com.intellij.javaee.deployment.DeploymentProvider;
import com.intellij.javaee.openapi.ex.AppServerIntegrationsManager;
import com.intellij.javaee.oss.descriptor.JavaeeDescriptorsManager;
import com.intellij.javaee.oss.server.JavaeeDeploymentProvider;
import com.intellij.javaee.oss.server.JavaeeIntegration;
import com.intellij.javaee.oss.util.Version;
import com.intellij.javaee.run.localRun.ColoredCommandLineExecutableObject;
import com.intellij.javaee.run.localRun.ExecutableObject;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.Function;
import consulo.javaee.module.extension.JavaEEModuleExtension;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
@Deprecated
public class TomcatIntegration extends JavaeeIntegration
{
  @NonNls
  protected static final String SHARED_DIR = "shared";

  private static final Pattern TOM_EE_JAR_PATTERN = Pattern.compile("tomee-.*\\.jar");

  private static final Pattern OPENEJB_CLIENT_JAR_PATTERN = Pattern.compile("openejb-client-.*\\.jar");

  private static final Pattern OPENEJB_CORE_JAR_PATTERN = Pattern.compile("openejb-core-.*\\.jar");

  public static TomcatIntegration getInstance() {
    return AppServerIntegrationsManager.getInstance().getIntegration(TomcatIntegration.class);
  }

  @Nonnull
  @Override
  public String getName() {
    return TomcatBundle.message("tomcat.application.server.name");
  }

  @Override
  protected void collectDescriptors(JavaeeDescriptorsManager descriptorsManager) {
    descriptorsManager.addItem(TomcatContextDescriptor.class, TomcatContextRoot.class, "context");
  }

  public static boolean isTomEE(String home) {
    return findLibByPattern(home, TOM_EE_JAR_PATTERN);
  }

  private static boolean findLibByPattern(String home, Pattern jarPattern) {
    File libDir = new File(home, TomcatConstants.CATALINA_LIB_DIRECTORY_NAME);
    return libDir.isDirectory() && !findFilesByMask(jarPattern, libDir).isEmpty();
  }

  private static List<File> findFilesByMask(@Nonnull Pattern pattern, @Nonnull File dir) {
    final ArrayList<File> found = new ArrayList<File>();
    final File[] files = dir.listFiles();
    if (files != null) {
      for (File file : files) {
        if (pattern.matcher(file.getName()).matches()) {
          found.add(file);
        }
      }
    }
    return found;
  }

  @Override
  protected void checkValidServerHome(String home, String version) throws Exception {
    if (StringUtil.isEmptyOrSpaces(home)) {
      throw new Exception(TomcatBundle.message("error.message.tomcat.home.path.should.not.be.empty"));
    }

    checkDir(new File(home, "bin"));
    checkDir(new File(home, TomcatConstants.CATALINA_BIN_DIRECTORY_NAME));
    if (new Version(version).getMajor() >= 6) {
      checkDir(new File(home, TomcatConstants.CATALINA_LIB_DIRECTORY_NAME));
    }
    else {
      File common = new File(home, TomcatConstants.CATALINA_COMMON_DIRECTORY_NAME);
      checkDir(common);
      checkDir(new File(common, TomcatConstants.CATALINA_LIB_DIRECTORY_NAME));
    }

    if (isTomEE(home)) {
      if (!findLibByPattern(home, OPENEJB_CLIENT_JAR_PATTERN)
          || !findLibByPattern(home, OPENEJB_CORE_JAR_PATTERN)) {
        throw new FileNotFoundException("TomEE deployer JAR(s) not found");
      }
    }
  }

  @Override
  protected boolean allLibrariesFound(Collection<String> classes, Function<String, String> mapper) {
    return allLibrariesExceptEjbFound(classes, mapper);
  }

  @Override
  protected void addLibraryLocations(String home, List<File> locations) {
    locations.add(new File(home, TomcatConstants.CATALINA_LIB_DIRECTORY_NAME));
    locations.add(new File(new File(home, TomcatConstants.CATALINA_COMMON_DIRECTORY_NAME), TomcatConstants.CATALINA_LIB_DIRECTORY_NAME));
    locations.add(new File(new File(home, SHARED_DIR), TomcatConstants.CATALINA_LIB_DIRECTORY_NAME));
  }

  @Override
  public String getContextRoot(JavaEEModuleExtension facet) {
    return null;
  }

  @Nonnull
  public ApplicationServerUrlMapping getDeployedFileUrlProvider() {
    return TomcatUrlMapping.INSTANCE;
  }

  @Override
  public boolean isStartupScriptTerminating(@Nonnull ExecutableObject startupScript) {
    if (startupScript instanceof ColoredCommandLineExecutableObject) {
      final String[] parameters = ((ColoredCommandLineExecutableObject)startupScript).getParameters();
      if (parameters.length >= 2) {
        String fileName = new File(parameters[0]).getName();
        if (TomcatStartupPolicy.getDefaultCatalinaFileName().equalsIgnoreCase(fileName) && parameters[1].equals("start")) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public DeploymentProvider getDeploymentProvider(boolean local) {
    return new JavaeeDeploymentProvider() {

      @Override
      public boolean isDeployOrderMatter() {
        return true;
      }
    };
  }
}
