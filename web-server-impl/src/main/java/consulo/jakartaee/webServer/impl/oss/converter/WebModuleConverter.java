/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.converter;

import com.intellij.javaee.facet.JavaeeFacetUtil;
import com.intellij.javaee.model.xml.application.JavaeeApplication;
import com.intellij.javaee.model.xml.application.JavaeeModule;
import consulo.javaee.module.extension.JavaEEApplicationModuleExtension;
import consulo.xml.util.xml.ConvertContext;
import consulo.xml.util.xml.ResolvingConverter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;

public class WebModuleConverter extends ResolvingConverter<JavaeeModule>
{

    @Override
    @Nullable
    public JavaeeModule fromString(String value, ConvertContext context) {
        if (value != null) {
            for (JavaeeModule module : getVariants(context)) {
                if (value.equals(module.getWeb().getWebUri().getValue())) {
                    return module;
                }
            }
        }
        return null;
    }

    @Override
    @Nullable
    public String toString(JavaeeModule value, ConvertContext context) {
        return (value != null) ? value.getWeb().getWebUri().getValue() : null;
    }

    @Override
    @Nonnull
    public Collection<? extends JavaeeModule> getVariants(ConvertContext context) {
        Collection<JavaeeModule> list = new ArrayList<JavaeeModule>();
        JavaEEApplicationModuleExtension facet = JavaeeFacetUtil.getInstance().getJavaeeFacet(context, JavaEEApplicationModuleExtension.class);
        if (facet != null) {
            JavaeeApplication root = facet.getRoot();
            if (root != null) {
                for (JavaeeModule module : root.getModules()) {
                    if (module.getWeb().getXmlTag() != null) {
                        list.add(module);
                    }
                }
            }
        }
        return list;
    }
}
