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

package com.intellij.javaee.model.common;

import com.intellij.javaee.model.enums.ResAuth;
import com.intellij.psi.xml.XmlDoctype;
import com.intellij.psi.xml.XmlDocument;
import com.intellij.psi.xml.XmlProlog;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.ResolvingConverter;
import com.intellij.util.xml.DomUtil;
import org.jetbrains.annotations.NonNls;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author peter
 */
public class ResAuthConverter extends ResolvingConverter<ResAuth> {
  @NonNls private static final String SERVLET = "SERVLET";
  @NonNls private static final String CONTAINER = "CONTAINER";

  private static boolean is23(final ConvertContext context) {
    final DomElement element = DomUtil.getFileElement(context.getInvocationElement()).getRootElement();
    final XmlTag tag = element.getXmlTag();
    if (tag != null && "web-app".equals(element.getXmlElementName()) && tag.getAttribute("version") == null) {
      XmlDocument document = (XmlDocument)tag.getParent();
      final XmlProlog prolog = document.getProlog();
      if (prolog != null) {
        final XmlDoctype doctype = prolog.getDoctype();
        if (doctype != null) {
          final String uri = doctype.getDtdUri();
          if (uri != null && uri.contains("2_3")) return true;
        }
      }
      return false;
    }
    return true;
  }

  @Nonnull
  public Collection<? extends ResAuth> getVariants(final ConvertContext context) {
    return Arrays.asList(ResAuth.class.getEnumConstants());
  }

  public ResAuth fromString(@Nullable @NonNls final String s, final ConvertContext context) {
    if (is23(context)) {
      return ResAuth.APPLICATION.getValue().equals(s) ? ResAuth.APPLICATION : ResAuth.CONTAINER.getValue().equals(s) ? ResAuth.CONTAINER : null;
    }
    return SERVLET.equals(s) ? ResAuth.APPLICATION : CONTAINER.equals(s) ? ResAuth.CONTAINER : null;
  }

  public String toString(@Nullable final ResAuth resAuth, final ConvertContext context) {
    if (resAuth == null) return null;
    return is23(context) ? resAuth.getValue() : resAuth == ResAuth.APPLICATION ? SERVLET : CONTAINER;
  }
}
