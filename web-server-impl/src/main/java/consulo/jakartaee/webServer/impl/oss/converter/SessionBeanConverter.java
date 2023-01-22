/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.converter;

import com.intellij.javaee.facet.JavaeeFacetUtil;
import com.intellij.javaee.model.xml.ejb.EjbJar;
import com.intellij.javaee.model.xml.ejb.SessionBean;
import consulo.javaee.module.extension.EjbModuleExtension;
import consulo.xml.util.xml.ConvertContext;
import consulo.xml.util.xml.ElementPresentationManager;
import consulo.xml.util.xml.ResolvingConverter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;

public class SessionBeanConverter extends ResolvingConverter<SessionBean>
{

    @Override
    @Nullable
    public SessionBean fromString(String value, ConvertContext context) {
        return ElementPresentationManager.findByName(getVariants(context), value);
    }

    @Override
    @Nullable
    public String toString(SessionBean value, ConvertContext context) {
        return (value != null) ? value.getEjbName().getValue() : null;
    }

    @Override
    @Nonnull
    public Collection<SessionBean> getVariants(ConvertContext context) {
        EjbModuleExtension facet = JavaeeFacetUtil.getInstance().getJavaeeFacet(context, EjbModuleExtension.class);
        if (facet != null) {
            EjbJar root = facet.getXmlRoot();
            if (root != null) {
                return root.getEnterpriseBeans().getSessions();
            }
        }
        return Collections.emptyList();
    }
}
