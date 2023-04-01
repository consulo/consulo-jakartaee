/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.server;

import com.intellij.javaee.J2EEBundle;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import consulo.xml.util.xml.highlighting.BasicDomElementsInspection;

import javax.annotation.Nonnull;

public abstract class JavaeeInspection extends BasicDomElementsInspection<JavaeeDomModelElement, Object> {
    @SafeVarargs
    protected JavaeeInspection(Class<? extends JavaeeDomModelElement> type, Class<? extends JavaeeDomModelElement>... types) {
        super(type, types);
    }

    @Override
    @Nonnull
    @SuppressWarnings({"UnresolvedPropertyKey"})
    public String getGroupDisplayName() {
        return J2EEBundle.message("inspection.group.display.name.application.server.inspections");
    }

    @Override
    @Nonnull
    public String getDisplayName() {
        return getShortName();
    }
}
