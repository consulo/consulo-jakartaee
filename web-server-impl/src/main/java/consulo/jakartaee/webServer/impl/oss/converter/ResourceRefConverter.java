/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.converter;

import com.intellij.javaee.model.xml.JndiEnvironmentRefsGroup;
import com.intellij.javaee.model.xml.ResourceRef;
import consulo.xml.util.xml.ConvertContext;
import consulo.xml.util.xml.ElementPresentationManager;
import consulo.xml.util.xml.ResolvingConverter;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;

public abstract class ResourceRefConverter extends ResolvingConverter<ResourceRef>
{

    @Override
    @Nullable
    public ResourceRef fromString(String value, ConvertContext context) {
        return ElementPresentationManager.findByName(getVariants(context), value);
    }

    @Override
    @Nullable
    public String toString(ResourceRef value, ConvertContext context) {
        return (value != null) ? value.getResRefName().getValue() : null;
    }

    @Override
    @Nonnull
    public Collection<ResourceRef> getVariants(ConvertContext context) {
        JndiEnvironmentRefsGroup group = getReferenceHolder(context);
        return (group != null) ? group.getResourceRefs() : Collections.<ResourceRef>emptyList();
    }

    @Nullable
    protected abstract JndiEnvironmentRefsGroup getReferenceHolder(ConvertContext context);
}
