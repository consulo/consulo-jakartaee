/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.converter;

import java.util.Collection;
import java.util.Collections;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.javaee.facet.JavaeeFacetUtil;
import com.intellij.javaee.model.xml.ejb.EjbJar;
import com.intellij.javaee.model.xml.ejb.MessageDrivenBean;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.ElementPresentationManager;
import com.intellij.util.xml.ResolvingConverter;
import consulo.javaee.module.extension.EjbModuleExtension;

public class MessageBeanConverter extends ResolvingConverter<MessageDrivenBean> {

    @Override
    @Nullable
    public MessageDrivenBean fromString(String value, ConvertContext context) {
        return ElementPresentationManager.findByName(getVariants(context), value);
    }

    @Override
    @Nullable
    public String toString(MessageDrivenBean value, ConvertContext context) {
        return (value != null) ? value.getEjbName().getValue() : null;
    }

    @Override
    @NotNull
    public Collection<MessageDrivenBean> getVariants(ConvertContext context) {
        EjbModuleExtension facet = JavaeeFacetUtil.getInstance().getJavaeeFacet(context, EjbModuleExtension.class);
        if (facet != null) {
            EjbJar root = facet.getXmlRoot();
            if (root != null) {
                return root.getEnterpriseBeans().getMessageDrivens();
            }
        }
        return Collections.emptyList();
    }
}
