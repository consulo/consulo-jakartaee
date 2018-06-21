/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.editor;

import com.intellij.util.xml.DomElement;

import java.util.List;

public interface JavaeeSection<T extends DomElement> {

    JavaeeSectionInfo<T>[] createColumnInfos();

    List<T> getValues();
}
