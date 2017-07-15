/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.converter;

import com.intellij.javaee.model.xml.JndiEnvironmentRefsGroup;
import com.intellij.javaee.model.xml.ResourceEnvRef;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.ElementPresentationManager;
import com.intellij.util.xml.ResolvingConverter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;

public abstract class ResourceEnvRefConverter extends ResolvingConverter<ResourceEnvRef> {

    @Override
    @Nullable
    public ResourceEnvRef fromString(String value, ConvertContext context) {
        return ElementPresentationManager.findByName(getVariants(context), value);
    }

    @Override
    @Nullable
    public String toString(ResourceEnvRef value, ConvertContext context) {
        return (value != null) ? value.getResourceEnvRefName().getValue() : null;
    }

    @Override
    @NotNull
    public Collection<ResourceEnvRef> getVariants(ConvertContext context) {
        JndiEnvironmentRefsGroup group = getReferenceHolder(context);
        return (group != null) ? group.getResourceEnvRefs() : Collections.<ResourceEnvRef>emptyList();
    }

    @Nullable
    protected abstract JndiEnvironmentRefsGroup getReferenceHolder(ConvertContext context);
}
