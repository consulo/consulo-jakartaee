/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.converter;

import com.intellij.javaee.model.xml.EjbLocalRef;
import com.intellij.javaee.model.xml.JndiEnvironmentRefsGroup;
import consulo.xml.util.xml.ConvertContext;
import consulo.xml.util.xml.ElementPresentationManager;
import consulo.xml.util.xml.ResolvingConverter;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;

public abstract class EjbLocalRefConverter extends ResolvingConverter<EjbLocalRef>
{

    @Override
    @Nullable
    public EjbLocalRef fromString(String value, ConvertContext context) {
        return ElementPresentationManager.findByName(getVariants(context), value);
    }

    @Override
    @Nullable
    public String toString(EjbLocalRef value, ConvertContext context) {
        return (value != null) ? value.getEjbRefName().getValue() : null;
    }

    @Override
    @Nonnull
    public Collection<EjbLocalRef> getVariants(ConvertContext context) {
        JndiEnvironmentRefsGroup group = getReferenceHolder(context);
        return (group != null) ? group.getEjbLocalRefs() : Collections.<EjbLocalRef>emptyList();
    }

    @Nullable
    protected abstract JndiEnvironmentRefsGroup getReferenceHolder(ConvertContext context);
}
