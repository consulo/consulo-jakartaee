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
public enum EjbClassRoleEnum
{
	EJB_CLASS_ROLE_HOME_INTERFACE,
	EJB_CLASS_ROLE_REMOTE_INTERFACE,
	EJB_CLASS_ROLE_EJB_CLASS,
	EJB_CLASS_ROLE_LOCAL_HOME_INTERFACE,
	EJB_CLASS_ROLE_LOCAL_INTERFACE,
	EJB_CLASS_ROLE_BUSINESS_LOCAL_INTERFACE,
	EJB_CLASS_ROLE_BUSINESS_REMOTE_INTERFACE,
	EJB_CLASS_ROLE_SERVICE_ENDPOINT_INTERFACE,
	EJB_CLASS_ROLE_INTERCEPTOR_CLASS,
	EMPTY_ROLE;

	public boolean isInterface()
	{
		switch(this)
		{
			case EJB_CLASS_ROLE_BUSINESS_LOCAL_INTERFACE:
			case EJB_CLASS_ROLE_BUSINESS_REMOTE_INTERFACE:
			case EJB_CLASS_ROLE_HOME_INTERFACE:
			case EJB_CLASS_ROLE_LOCAL_HOME_INTERFACE:
			case EJB_CLASS_ROLE_LOCAL_INTERFACE:
			case EJB_CLASS_ROLE_REMOTE_INTERFACE:
			case EJB_CLASS_ROLE_SERVICE_ENDPOINT_INTERFACE:
				return true;
			case EJB_CLASS_ROLE_EJB_CLASS:
			case EJB_CLASS_ROLE_INTERCEPTOR_CLASS:
			case EMPTY_ROLE:
				return false;
		}
		return false;
	}

	public boolean isRemote()
	{
		switch(this)
		{
			case EJB_CLASS_ROLE_BUSINESS_LOCAL_INTERFACE:
			case EJB_CLASS_ROLE_LOCAL_HOME_INTERFACE:
			case EJB_CLASS_ROLE_EJB_CLASS:
			case EJB_CLASS_ROLE_INTERCEPTOR_CLASS:
			case EJB_CLASS_ROLE_LOCAL_INTERFACE:
			case EMPTY_ROLE:
				return false;
			case EJB_CLASS_ROLE_BUSINESS_REMOTE_INTERFACE:
			case EJB_CLASS_ROLE_HOME_INTERFACE:
			case EJB_CLASS_ROLE_REMOTE_INTERFACE:
			case EJB_CLASS_ROLE_SERVICE_ENDPOINT_INTERFACE:
				return true;
		}
		return false;
	}
}
