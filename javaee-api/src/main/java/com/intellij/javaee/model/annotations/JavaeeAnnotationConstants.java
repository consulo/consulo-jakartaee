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

package com.intellij.javaee.model.annotations;

import com.intellij.javaee.model.common.JavaeeCommonConstants;
import com.intellij.javaee.model.common.ejb.EjbAnnotationConstants;
import org.jetbrains.annotations.NonNls;

/**
 * Created by IntelliJ IDEA.
 * User: greg
 * Date: 09.12.2005
 * Time: 20:00:16
 */
public interface JavaeeAnnotationConstants extends JavaeeCommonConstants, EjbAnnotationConstants {

  @NonNls String VALUE_PARAM = "value";
  @NonNls String NAME_PARAM = "name";

  // *** javax.annotation.security ***
  @NonNls String DECLARE_ROLES_ANNO = "javax.annotation.security.DeclareRoles";
  @NonNls String DENY_ALL_ANNO = "javax.annotation.security.DenyAll";
  @NonNls String PERMIT_ALL_ANNO = "javax.annotation.security.PermitAll";
  @NonNls String ROLES_ALLOWED_ANNO = "javax.annotation.security.RolesAllowed";
  @NonNls String RUN_AS_ANNO = "javax.annotation.security.RunAs";

  // *** javax.interceptor ***
  @NonNls String AROUND_INVOKE_ANNO = "javax.interceptor.AroundInvoke";
  @NonNls String AROUND_TIMEOUT_ANNO = "javax.interceptor.AroundTimeout";
  @NonNls String EXCLUDE_CLASS_INTERCEPTORS_ANNO = "javax.interceptor.ExcludeClassInterceptors";
  @NonNls String EXCLUDE_DEFAULT_INTERCEPTORS_ANNO = "javax.interceptor.ExcludeDefaultInterceptors";
  @NonNls String INTERCEPTORS_ANNO = "javax.interceptor.Interceptors";

  String AUTHENTICATION_TYPE_PREFIX = "javax.annotation.Resource.AuthenticationType" + "";
  // *** javax.annotation ***
  @NonNls String GENERATED_ANNO = "javax.annotation.Generated";
  @NonNls String GENERATED_COMMENTS_PARAM = "comments";
  @NonNls String GENERATED_DATE_PARAM = "date";
  @NonNls String POST_CONSTRUCT_ANNO = "javax.annotation.PostConstruct";
  @NonNls String PRE_DESTROY_ANNO = "javax.annotation.PreDestroy";
  @NonNls String RESOURCE_ANNO = "javax.annotation.Resource";
  @NonNls String RESOURCE_AUTHENTICATION_TYPE_PARAM = "authenticationType";
  @NonNls String RESOURCE_DESCRIPTION_PARAM = "description";
  @NonNls String RESOURCE_MAPPED_NAME_PARAM = "mappedName";
  @NonNls String RESOURCE_SHAREABLE_PARAM = "shareable";
  @NonNls String RESOURCE_TYPE_PARAM = "type";
  @NonNls String RESOURCES_ANNO = "javax.annotation.Resources";
  @NonNls String REFERENCE_MAPPED_NAME_PARAM = RESOURCE_MAPPED_NAME_PARAM;
  @NonNls String REFERENCE_NAME_PARAM = "name";
  @NonNls String REFERENCE_TYPE_PARAM = RESOURCE_TYPE_PARAM;

  // *** javax.xml.ws ***
  @NonNls String ACTION_ANNO = "javax.xml.ws.Action";
  @NonNls String ACTION_FAULT_PARAM = "fault";
  @NonNls String ACTION_INPUT_PARAM = "input";
  @NonNls String ACTION_OUTPUT_PARAM = "output";
  @NonNls String BINDING_TYPE_ANNO = "javax.xml.ws.BindingType";
  @NonNls String FAULT_ACTION_ANNO = "javax.xml.ws.FaultAction";
  // @NonNls String FAULT_ACTION_CLASS_NAME_PARAM = "className";
  @NonNls String REQUEST_WRAPPER_ANNO = "javax.xml.ws.RequestWrapper";
// @NonNls String REQUEST_WRAPPER_CLASS_NAME_PARAM = "className";
  // @NonNls String REQUEST_WRAPPER_LOCAL_NAME_PARAM = "localName";
// @NonNls String REQUEST_WRAPPER_TARGET_NAMESPACE_PARAM = "targetNamespace";
  @NonNls String RESPECT_BINDING_ANNO = "javax.xml.ws.RespectBinding";
  @NonNls String RESPECT_BINDING_ENABLED_PARAM = "enabled";
  @NonNls String RESPONSE_WRAPPER_ANNO = "javax.xml.ws.ResponseWrapper";
// @NonNls String RESPONSE_WRAPPER_CLASS_NAME_PARAM = "className";
  // @NonNls String RESPONSE_WRAPPER_LOCAL_NAME_PARAM = "localName";
// @NonNls String RESPONSE_WRAPPER_TARGET_NAMESPACE_PARAM = "targetNamespace";
  @NonNls String SERVICE_MODE_ANNO = "javax.xml.ws.ServiceMode";
  @NonNls String WEB_ENDPOINT_ANNO = "javax.xml.ws.WebEndpoint";
  @NonNls String WEB_FAULT_ANNO = "javax.xml.ws.WebFault";
  @NonNls String WEB_FAULT_FAULT_BEAN_PARAM = "faultBean";
  // @NonNls String WEB_FAULT_TARGET_NAMESPACE_PARAM = "targetNamespace";
  @NonNls String WEB_SERVICE_CLIENT_ANNO = "javax.xml.ws.WebServiceClient";
  // @NonNls String WEB_SERVICE_CLIENT_TARGET_NAMESPACE_PARAM = "targetNamespace";
  // @NonNls String WEB_SERVICE_CLIENT_WSDL_LOCATION_PARAM = "wsdlLocation";
  @NonNls String WEB_SERVICE_PROVIDER_ANNO = "javax.xml.ws.WebServiceProvider";
  @NonNls String WEB_SERVICE_PROVIDER_PORT_NAME_PARAM = "portName";
  @NonNls String WEB_SERVICE_PROVIDER_SERVICE_NAME_PARAM = "serviceName";
  // @NonNls String WEB_SERVICE_PROVIDER_TARGET_NAMESPACE_PARAM = "targetNamespace";
  // @NonNls String WEB_SERVICE_PROVIDER_WSDL_LOCATION_PARAM = "wsdlLocation";
  @NonNls String WEB_SERVICE_REF_ANNO = "javax.xml.ws.WebServiceRef";
  @NonNls String WEB_SERVICE_REF_MAPPED_NAME_PARAM = "mappedName";
  @NonNls String WEB_SERVICE_REF_TYPE_PARAM = "type";
  // @NonNls String WEB_SERVICE_REF_WSDL_LOCATION_PARAM = "wsdlLocation";
  @NonNls String WEB_SERVICE_REFS_ANNO = "javax.xml.ws.WebServiceRefs";
  @NonNls String CLASS_NAME_PARAM = "className";
  @NonNls String LOCAL_NAME_PARAM = "localName";
  @NonNls String TARGET_NAMESPACE_PARAM = "targetNamespace";
  @NonNls String WSDL_LOCATION_PARAM = "wsdlLocation";

    // *** javax.jws ***
  @NonNls String HANDLER_CHAIN_ANNO = "javax.jws.HandlerChain";
  @NonNls String HANDLER_CHAIN_FILE_PARAM = "file";
  @NonNls String ONEWAY_ANNO = "javax.jws.Oneway";
  @NonNls String WEB_METHOD_ACTION_PARAM = "action";
  @NonNls String WEB_METHOD_ANNO = "javax.jws.WebMethod";
  @NonNls String WEB_METHOD_EXCLUDE_PARAM = "exclude";
  @NonNls String WEB_METHOD_OPERATION_NAME_PARAM = "operationName";
  @NonNls String WEB_PARAM_ANNO = "javax.jws.WebParam";
  // @NonNls String WEB_PARAM_HEADER_PARAM = "header";
  @NonNls String WEB_PARAM_MODE_PARAM = "mode";
  // @NonNls String WEB_PARAM_PART_NAME_PARAM = "partName";
  // @NonNls String WEB_PARAM_TARGET_NAMESPACE_PARAM = "targetNamespace";
  @NonNls String WEB_RESULT_ANNO = "javax.jws.WebResult";
  // @NonNls String WEB_RESULT_HEADER_PARAM = "header";
  // @NonNls String WEB_RESULT_PART_NAME_PARAM = "partName";
  // @NonNls String WEB_RESULT_TARGET_NAMESPACE_PARAM = "targetNamespace";
  @NonNls String WEB_SERVICE_ANNO = "javax.jws.WebService";
  @NonNls String WEB_SERVICE_ENDPOINT_INTERFACE_PARAM = "endpointInterface";
  @NonNls String WEB_SERVICE_PORT_NAME_PARAM = "portName";
  @NonNls String WEB_SERVICE_SERVICE_NAME_PARAM = "serviceName";
  // @NonNls String WEB_SERVICE_TARGET_NAMESPACE_PARAM = "targetNamespace";
  @NonNls String WEB_SERVICE_WSDL_LOCATION_PARAM = "wsdlLocation";
  @NonNls String HEADER_PARAM = "header";
  @NonNls String PART_NAME_PARAM = "partName";
  //@NonNls String TARGET_NAMESPACE_PARAM = "targetNamespace";

}
