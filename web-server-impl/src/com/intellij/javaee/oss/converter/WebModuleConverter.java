/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.converter;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.intellij.javaee.facet.JavaeeFacetUtil;
import com.intellij.javaee.model.xml.application.JavaeeApplication;
import com.intellij.javaee.model.xml.application.JavaeeModule;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.ResolvingConverter;
import consulo.javaee.module.extension.JavaEEApplicationModuleExtension;

public class WebModuleConverter extends ResolvingConverter<JavaeeModule> {

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
