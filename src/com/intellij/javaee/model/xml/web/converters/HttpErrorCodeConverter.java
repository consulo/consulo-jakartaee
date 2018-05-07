/*
 * Copyright 2000-2013 JetBrains s.r.o.
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
package com.intellij.javaee.model.xml.web.converters;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.javaee.model.xml.web.HttpErrorCode;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.NamedEnumUtil;
import com.intellij.util.xml.ResolvingConverter;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;

/**
 * @author Yann C&eacute;bron
 */
public class HttpErrorCodeConverter extends ResolvingConverter<HttpErrorCode> {

  private static final Pattern HTTP_STATUS_CODE = Pattern.compile("\\d{3}");

  public final HttpErrorCode fromString(final String s, final ConvertContext context) {
    if (s == null) {
      return null;
    }
    final HttpErrorCode value = NamedEnumUtil.getEnumElementByValue(HttpErrorCode.class, s);
    if (value != null) {
      return value;
    }

    // allow custom 3-digit (error) codes
    return HTTP_STATUS_CODE.matcher(s).matches() ? HttpErrorCode.BAD_REQUEST : null;
  }

  public final String toString(final HttpErrorCode errorCode, final ConvertContext context) {
    return errorCode == null ? null : NamedEnumUtil.getEnumValueByElement(errorCode);
  }

  @Nonnull
  @Override
  public Collection<? extends HttpErrorCode> getVariants(ConvertContext context) {
    return Arrays.asList(HttpErrorCode.values());
  }

  public String getErrorMessage(@Nullable final String s, final ConvertContext context) {
    return "Invalid HTTP status code '" + s + "'";
  }

  @Nullable
  @Override
  public LookupElement createLookupElement(HttpErrorCode errorCode) {
    return LookupElementBuilder.create(errorCode.getValue()).withTailText(" (" + errorCode.name() + ")", true);
  }
}
