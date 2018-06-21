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

import com.intellij.javaee.model.common.ejb.EnterpriseBean;
import com.intellij.javaee.model.common.ejb.EntityBean;
import com.intellij.javaee.model.enums.CmpVersion;
import com.intellij.javaee.model.enums.PersistenceType;
import com.intellij.openapi.util.Condition;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.xml.ConvertContext;
import javax.annotation.Nonnull;

import java.util.Collection;

/**
 * @author peter
 */
public class RelationshipEntityBeanResolveConverter extends EjbResolveConverter{

  public RelationshipEntityBeanResolveConverter() {
    super(true, false, false);
  }


  @Nonnull
  public Collection<? extends EnterpriseBean> getVariants(final ConvertContext context) {
    return ContainerUtil.findAll(super.getVariants(context), new Condition<EnterpriseBean>() {
      public boolean value(final EnterpriseBean object) {
        return canAddRelationship(object);
      }
    }
  );
  }

  public static boolean canAddRelationship(final EnterpriseBean ejb) {
    if (ejb == null || !(ejb instanceof EntityBean)) {
      return false;
    }

    final EntityBean entityBean = (EntityBean)ejb;
    return entityBean.getPersistenceType().getValue() == PersistenceType.CONTAINER &&
           entityBean.getCmpVersion().getValue() == CmpVersion.CmpVersion_2_X &&
           entityBean.getLocal().getValue() != null;
  }

}
