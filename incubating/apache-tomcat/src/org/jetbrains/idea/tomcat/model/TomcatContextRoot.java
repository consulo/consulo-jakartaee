package org.jetbrains.idea.tomcat.model;

import com.intellij.ide.presentation.Presentation;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
@Presentation(provider = TomcatPresentationProvider.class)
public interface TomcatContextRoot extends JavaeeDomModelElement {
}
