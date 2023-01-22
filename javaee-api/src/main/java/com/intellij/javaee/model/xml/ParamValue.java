/*
 * Copyright 2000-2007 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Generated on Tue Feb 14 17:35:31 MSK 2006
// DTD/Schema  :    http://java.sun.com/xml/ns/javaee

package com.intellij.javaee.model.xml;

import com.intellij.javaee.model.xml.converters.ContextParamNameConverter;
import com.intellij.javaee.model.xml.converters.ContextParamValueConverter;
import consulo.xml.util.xml.*;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:param-valueType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:param-valueType documentation</h3>
 * This type is a general type that can be used to declare
 * 	parameter/value lists.
 * </pre>
 */
public interface ParamValue extends JavaeeDomModelElement, com.intellij.javaee.model.common.ejb.ParamValue, DescriptionOwner {

  /**
   * Returns the list of description children.
   *
   * @return the list of description children.
   */
  List<Description> getDescriptions();

  /**
   * Adds new child to the list of description children.
   *
   * @return created child
   */
  Description addDescription();


  /**
   * Returns the value of the param-name child.
   * <pre>
   * <h3>Element http://java.sun.com/xml/ns/javaee:param-name documentation</h3>
   * The param-name element contains the name of a
   * 	    parameter.
   * </pre>
   * <pre>
   * <h3>Type http://java.sun.com/xml/ns/javaee:string documentation</h3>
   * This is a special string datatype that is defined by Java EE as
   * 	a base type for defining collapsed strings. When schemas
   * 	require trailing/leading space elimination as well as
   * 	collapsing the existing whitespace, this base type may be
   * 	used.
   * </pre>
   *
   * @return the value of the param-name child.
   */
  @NameValue
  @Convert(ContextParamNameConverter.class)
  @Required
  @Stubbed
  GenericDomValue<String> getParamName();


  /**
   * Returns the value of the param-value child.
   * <pre>
   * <h3>Element http://java.sun.com/xml/ns/javaee:param-value documentation</h3>
   * The param-value element contains the value of a
   * 	    parameter.
   * </pre>
   * <pre>
   * <h3>Type http://java.sun.com/xml/ns/javaee:xsdStringType documentation</h3>
   * This type adds an "id" attribute to xsd:string.
   * </pre>
   *
   * @return the value of the param-value child.
   */
  @Convert(ContextParamValueConverter.class)
  @Required
  @Stubbed
  GenericDomValue<String> getParamValue();
}
