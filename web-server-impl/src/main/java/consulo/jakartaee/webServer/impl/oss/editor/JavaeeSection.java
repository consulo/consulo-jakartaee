/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.editor;

import consulo.xml.util.xml.DomElement;

import java.util.List;

public interface JavaeeSection<T extends DomElement> {

    JavaeeSectionInfo<T>[] createColumnInfos();

    List<T> getValues();
}
