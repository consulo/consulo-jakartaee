/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.converter;

import com.intellij.javaee.facet.JavaeeFacetUtil;
import com.intellij.javaee.model.xml.ejb.EjbJar;
import com.intellij.javaee.model.xml.ejb.MessageDrivenBean;
import consulo.javaee.module.extension.EjbModuleExtension;
import consulo.xml.util.xml.ConvertContext;
import consulo.xml.util.xml.ElementPresentationManager;
import consulo.xml.util.xml.ResolvingConverter;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;

public class MessageBeanConverter extends ResolvingConverter<MessageDrivenBean>
{

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
    @Nonnull
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
