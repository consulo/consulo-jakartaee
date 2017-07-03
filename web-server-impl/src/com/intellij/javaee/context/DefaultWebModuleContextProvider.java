package com.intellij.javaee.context;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.javaee.model.xml.application.JavaeeApplication;
import com.intellij.javaee.model.xml.application.JavaeeModule;
import com.intellij.javaee.model.xml.application.Web;
import consulo.javaee.module.extension.JavaEEApplicationModuleExtension;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class DefaultWebModuleContextProvider implements WebModuleContextProvider {

  public static final DefaultWebModuleContextProvider INSTANCE = new DefaultWebModuleContextProvider();

  private DefaultWebModuleContextProvider() {

  }

  @Override
  @Nullable
  public String getContext(@NotNull JavaEEApplicationModuleExtension earFacet, @Nullable String moduleWebUri) {
    final JavaeeApplication javaeeApplication = earFacet.getRoot();
    return javaeeApplication == null ? null : getContext(javaeeApplication, moduleWebUri);
  }

  @Nullable
  public static String getContext(@NotNull JavaeeApplication javaeeApplication, @Nullable String moduleWebUri) {
    final List<JavaeeModule> modules = javaeeApplication.getModules();
    for (JavaeeModule module : modules) {
      final Web web = module.getWeb();
      final String webUri = web.getWebUri().getValue();
      //todo[nik] support case when webUri contains slash
      if (moduleWebUri == null || moduleWebUri.equals(webUri)) {
        final String contextRoot = web.getContextRoot().getValue();
        if (contextRoot != null) {
          return contextRoot;
        }
      }
    }
    return null;
  }
}
