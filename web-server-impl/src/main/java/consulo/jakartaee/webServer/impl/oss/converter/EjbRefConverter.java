/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.converter;

import com.intellij.javaee.model.xml.EjbRef;
import com.intellij.javaee.model.xml.JndiEnvironmentRefsGroup;
import consulo.xml.util.xml.ConvertContext;
import consulo.xml.util.xml.ElementPresentationManager;
import consulo.xml.util.xml.ResolvingConverter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;

public abstract class EjbRefConverter extends ResolvingConverter<EjbRef>
{

    @Override
    @Nullable
    public EjbRef fromString(String value, ConvertContext context) {
        return ElementPresentationManager.findByName(getVariants(context), value);
    }

    @Override
    @Nullable
    public String toString(EjbRef value, ConvertContext context) {
        return (value != null) ? value.getEjbRefName().getValue() : null;
    }

    @Override
    @Nonnull
    public Collection<EjbRef> getVariants(ConvertContext context) {
        JndiEnvironmentRefsGroup group = getReferenceHolder(context);
        return (group != null) ? group.getEjbRefs() : Collections.<EjbRef>emptyList();
    }

    @Nullable
    protected abstract JndiEnvironmentRefsGroup getReferenceHolder(ConvertContext context);
}
