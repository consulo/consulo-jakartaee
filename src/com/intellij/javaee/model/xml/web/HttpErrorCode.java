/*
 * Copyright 2000-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")"),
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
package com.intellij.javaee.model.xml.web;

import com.intellij.util.xml.NamedEnum;

/**
 * @author Yann C&eacute;bron
 */
@SuppressWarnings("SpellCheckingInspection")
public enum HttpErrorCode implements NamedEnum {

  BAD_REQUEST("400"),
  UNAUTHORIZED("401"),
  PAYMENT_REQUIRED("402"),
  FORBIDDEN("403"),
  NOT_FOUND("404"),
  METHOD_NOT_ALLOWED("405"),
  NOT_ACCEPTABLE("406"),
  PROXY_AUTHENTICATION_REQUIRED("407"),
  REQUEST_TIMEOUT("408"),
  CONFLICT("409"),
  GONE("410"),
  LENGTH_REQUIRED("411"),
  PRECONDITION_FAILED("412"),
  REQUEST_TOO_LONG("413"),
  REQUEST_URI_TOO_LONG("414"),
  UNSUPPORTED_MEDIA_TYPE("415"),
  REQUESTED_RANGE_NOT_SATISFIABLE("416"),
  EXPECTATION_FAILED("417"),
  INSUFFICIENT_SPACE_ON_RESOURCE("419"),
  METHOD_FAILURE("420"),
  UNPROCESSABLE_ENTITY("422"),
  LOCKED("423"),
  FAILED_DEPENDENCY("424"),
  INTERNAL_SERVER_ERROR("500"),
  NOT_IMPLEMENTED("501"),
  BAD_GATEWAY("502"),
  SERVICE_UNAVAILABLE("503"),
  GATEWAY_TIMEOUT("504"),
  HTTP_VERSION_NOT_SUPPORTED("505"),
  INSUFFICIENT_STORAGE("507");

  private final String value;

  private HttpErrorCode(String value) {
    this.value = value;
  }

  @Override
  public String getValue() {
    return value;
  }
}
