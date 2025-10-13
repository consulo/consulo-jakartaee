/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */
package consulo.jakartaee.webServer.impl.oss.server;

import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import consulo.jakarta.localize.JakartaLocalize;
import consulo.localize.LocalizeValue;
import consulo.xml.util.xml.highlighting.BasicDomElementsInspection;
import jakarta.annotation.Nonnull;

public abstract class JavaeeInspection extends BasicDomElementsInspection<JavaeeDomModelElement, Object> {
    @SafeVarargs
    protected JavaeeInspection(Class<? extends JavaeeDomModelElement> type, Class<? extends JavaeeDomModelElement>... types) {
        super(type, types);
    }

    @Nonnull
    @Override
    @SuppressWarnings({"UnresolvedPropertyKey"})
    public LocalizeValue getGroupDisplayName() {
        return JakartaLocalize.inspectionGroupDisplayNameApplicationServerInspections();
    }
}
