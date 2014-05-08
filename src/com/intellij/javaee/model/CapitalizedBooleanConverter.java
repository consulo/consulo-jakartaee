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
package com.intellij.javaee.model;

import com.intellij.javaee.J2EEBundle;
import com.intellij.openapi.util.Pair;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.ResolvingConverter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author peter
 */
public abstract class CapitalizedBooleanConverter extends ResolvingConverter<Boolean> {
  public Boolean fromString(final String s, final ConvertContext context) {
    final Pair<String,String> strings = getRequiredValues(context);
    return strings.first.equals(s) ? Boolean.TRUE : strings.second.equals(s) ? Boolean.FALSE : null;
  }

  protected abstract Pair<String,String> getRequiredValues(final ConvertContext context);

  public String toString(final Boolean aBoolean, final ConvertContext context) {
    final Pair<String,String> strings = getRequiredValues(context);
    return aBoolean ? strings.first : strings.second;
  }

  public String getErrorMessage(final String s, final ConvertContext context) {
    final Pair<String,String> strings = getRequiredValues(context);
    return J2EEBundle.message("value.should.either.0.or.1", strings.first, strings.second);
  }

  @NotNull
  public Collection<? extends Boolean> getVariants(final ConvertContext context) {
    return Arrays.asList(Boolean.TRUE, Boolean.FALSE);
  }
}
