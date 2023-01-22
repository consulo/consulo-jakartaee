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

import com.intellij.javaee.model.common.ejb.*;
import consulo.application.util.function.CommonProcessors;
import consulo.application.util.function.Processor;
import consulo.language.editor.CodeInsightBundle;
import consulo.module.Module;
import consulo.util.lang.Comparing;
import consulo.xml.util.xml.ConvertContext;
import consulo.xml.util.xml.DomElement;
import consulo.xml.util.xml.ModuleContextProvider;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;

/**
 * @author peter
 */
public class EjbResolveConverter extends JavaeeResolvingConverter<EnterpriseBean> {
  private boolean myAllowEntityBeans;
  private boolean myAllowSessionBeans;
  private boolean myAllowMessageDrivenBeans;

  public EjbResolveConverter() {
    this(true, true, true);
  }

  protected EjbResolveConverter(final boolean allowEntityBeans, final boolean allowMessageDrivenBeans, final boolean allowSessionBeans) {
    myAllowEntityBeans = allowEntityBeans;
    myAllowMessageDrivenBeans = allowMessageDrivenBeans;
    myAllowSessionBeans = allowSessionBeans;
  }

  public EnterpriseBean fromString(final String s, final ConvertContext context) {
    if (s == null) return null;

    final EnterpriseBean[] result = new EnterpriseBean[]{null};
    processBeans(context, new Processor<EnterpriseBean>() {
      public boolean process(final EnterpriseBean enterpriseBean) {
        if (Comparing.equal(s, enterpriseBean.getEjbName().getValue())) {
          result[0] = enterpriseBean;
          return false;
        }
        return true;
      }
    });
    return result[0];
  }

  private Module[] getContextModules(final ConvertContext context) {
    final DomElement domElement = context.getInvocationElement();
    if (domElement.getManager().isMockElement(domElement)) {
      return new Module[] {domElement.getModule()};
    }
    return ModuleContextProvider.getModules(context.getFile());

  }
  private void processBeans(final ConvertContext context, final Processor<EnterpriseBean> processor) {
    for (Module contextModule : getContextModules(context)) {
      final List<EnterpriseBean> list = EjbCommonModelUtil.getAllEjbs(context.getProject(), contextModule, null);
      for (final EnterpriseBean bean : list) {
        if (myAllowEntityBeans && bean instanceof EntityBean && !processor.process(bean)) {
          return;
        }
        if (myAllowSessionBeans && bean instanceof SessionBean && !processor.process(bean)) {
          return;
        }
        if (myAllowMessageDrivenBeans && bean instanceof MessageDrivenBean && !processor.process(bean)) {
          return;
        }
      }
    }
  }

  public String getErrorMessage(final String s, final ConvertContext context) {
    return CodeInsightBundle.message("error.cannot.resolve.0.1", "EJB", s);
  }

  @Nonnull
  public Collection<? extends EnterpriseBean> getVariants(final ConvertContext context) {
    final CommonProcessors.CollectProcessor<EnterpriseBean> processor = new CommonProcessors.CollectProcessor<EnterpriseBean>();
    processBeans(context, processor);
    return processor.getResults();
  }
}
