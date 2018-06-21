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
package com.intellij.javaee.ejb.role;

/**
 * @author peter
 */
public enum EjbMethodRoleEnum
{
	EJB_METHOD_ROLE_BUSINESS_METHOD_IMPL,
	EJB_METHOD_ROLE_SELECTOR_IMPL,
	EJB_METHOD_ROLE_FINDER_IMPL,
	EJB_METHOD_ROLE_CREATE_IMPL,
	EJB_METHOD_ROLE_POST_CREATE_IMPL,
	EJB_METHOD_ROLE_CMP_GETTER_IMPL,
	EJB_METHOD_ROLE_CMP_SETTER_IMPL,
	EJB_METHOD_ROLE_HOME_BUSINESS_METHOD_IMPL,
	EJB_METHOD_ROLE_CMR_GETTER_IMPL,
	EJB_METHOD_ROLE_CMR_SETTER_IMPL,

	EJB_METHOD_ROLE_BUSINESS_METHOD_DECL,
	EJB_METHOD_ROLE_FINDER_DECL,
	EJB_METHOD_ROLE_CREATE_DECL,
	EJB_METHOD_ROLE_CMP_GETTER_DECL,
	EJB_METHOD_ROLE_CMP_SETTER_DECL,
	EJB_METHOD_ROLE_HOME_BUSINESS_METHOD_DECL,
	EJB_METHOD_ROLE_CMR_GETTER_DECL,
	EJB_METHOD_ROLE_CMR_SETTER_DECL

  /*public static final int EJB_METHOD_ROLE_BUSINESS_METHOD_IMPL = 51;
  public static final int EJB_METHOD_ROLE_FINDER_IMPL = 52;
  public static final int EJB_METHOD_ROLE_CREATE_IMPL = 53;
  public static final int EJB_METHOD_ROLE_POST_CREATE_IMPL = 54;
  public static final int EJB_METHOD_ROLE_CMP_GETTER_IMPL = 55;
  public static final int EJB_METHOD_ROLE_CMP_SETTER_IMPL = 56;
  public static final int EJB_METHOD_ROLE_HOME_BUSINESS_METHOD_IMPL = 57;
  public static final int EJB_METHOD_ROLE_CMR_GETTER_IMPL = 58;
  public static final int EJB_METHOD_ROLE_CMR_SETTER_IMPL = 59;

  public static final int EJB_METHOD_ROLE_BUSINESS_METHOD_DECL = 1;
  public static final int EJB_METHOD_ROLE_FINDER_DECL = 2;
  public static final int EJB_METHOD_ROLE_CREATE_DECL = 3;
  public static final int EJB_METHOD_ROLE_CMP_GETTER_DECL = 4;
  public static final int EJB_METHOD_ROLE_CMP_SETTER_DECL = 5;
  public static final int EJB_METHOD_ROLE_HOME_BUSINESS_METHOD_DECL = 6;
  public static final int EJB_METHOD_ROLE_CMR_GETTER_DECL = 7;
  public static final int EJB_METHOD_ROLE_CMR_SETTER_DECL = 8;*/
}
