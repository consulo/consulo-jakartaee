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
package com.intellij.javaee.model.common.ejb;

import org.jetbrains.annotations.NonNls;
import com.intellij.javaee.ejb.role.EjbMethodRoleEnum;
import com.intellij.javaee.model.enums.PersistenceType;

/**
 * @author peter
 */
public class EjbPsiMethodUtil {
  @NonNls public static final String CREATE_PREFIX = "create";

  public static String[] suggestImplNames(String methodName, EjbMethodRoleEnum type, EnterpriseBean ejb) {
    @NonNls String[] implName = new String[]{null, null};

    //noinspection EnumSwitchStatementWhichMissesCases
    switch (type) {
      case EJB_METHOD_ROLE_FINDER_DECL:
        // as of EJB 2.0 spec 10.6.2:
        //   The entity bean class does not implement the finder methods. The implementations of the finder methods
        //   are provided by the container.
        if (((EntityBean) ejb).getPersistenceType().getValue() == PersistenceType.BEAN) {
          implName[0] = "ejb" + Character.toUpperCase(methodName.charAt(0)) + methodName.substring(1);
        }
        break;

      case EJB_METHOD_ROLE_CREATE_DECL:
        if (ejb instanceof SessionBean || ejb instanceof MessageDrivenBean) {
          // for SB: ejbCreate<METHOD> methods return type must be void.
          // for MB: ejbCreate method return type must be void.
          implName[0] = "ejbCreate" + methodName.substring(CREATE_PREFIX.length());

        }
        else if (ejb instanceof EntityBean) {
          // for EB: ejbCreate<METHOD>() return type must be the entity bean?s primary key type.
          //For each ejbCreate<METHOD>(...) method, the entity bean class must define a matching ejbPostCreate<METHOD>(...)
          //  which return type must be void.
          implName[0] = "ejbCreate" + methodName.substring(CREATE_PREFIX.length());
          implName[1] = "ejbPostCreate" + methodName.substring(CREATE_PREFIX.length());
        }
        break;

      case EJB_METHOD_ROLE_CMP_GETTER_DECL:
      case EJB_METHOD_ROLE_CMP_SETTER_DECL:
        implName[0] = methodName;
        break;
      case EJB_METHOD_ROLE_CMR_GETTER_DECL:
      case EJB_METHOD_ROLE_CMR_SETTER_DECL:
        implName[0] = methodName;
        break;
      case EJB_METHOD_ROLE_BUSINESS_METHOD_DECL:
        implName[0] = methodName;
        break;

      case EJB_METHOD_ROLE_HOME_BUSINESS_METHOD_DECL:
        implName[0] = "ejbHome" + Character.toUpperCase(methodName.charAt(0)) + methodName.substring(1);
        break;
    }
    return implName;
  }

}
