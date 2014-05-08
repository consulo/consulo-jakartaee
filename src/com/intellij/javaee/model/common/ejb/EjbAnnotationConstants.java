/*
 * Copyright 2000-2009 JetBrains s.r.o.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.intellij.javaee.model.common.ejb;

import org.jetbrains.annotations.NonNls;

/**
 * @author Gregory.Shrago
 */
public interface EjbAnnotationConstants {
  // todo support AppException inheritance & add inspection, default = true!

  // generated for javax.ejb package (EJB 3.1)
  // *** javax.ejb ***
  @NonNls String ACCESS_TIMEOUT_ANNO = "javax.ejb.AccessTimeout";
  // @NonNls String ACCESS_TIMEOUT_UNIT_PARAM = "unit";
  @NonNls String ACTIVATION_CONFIG_PROPERTY_ANNO = "javax.ejb.ActivationConfigProperty";
  @NonNls String ACTIVATION_CONFIG_PROPERTY_NAME_PARAM = "propertyName";
  @NonNls String ACTIVATION_CONFIG_PROPERTY_VALUE_PARAM = "propertyValue";
  @NonNls String AFTER_BEGIN_ANNO = "javax.ejb.AfterBegin";
  @NonNls String AFTER_COMPLETION_ANNO = "javax.ejb.AfterCompletion";
  @NonNls String APPLICATION_EXCEPTION_ANNO = "javax.ejb.ApplicationException";
  @NonNls String APPLICATION_EXCEPTION_INHERITED_PARAM = "inherited";
  @NonNls String APPLICATION_EXCEPTION_ROLLBACK_PARAM = "rollback";
  @NonNls String ASYNCHRONOUS_ANNO = "javax.ejb.Asynchronous";
  @NonNls String BEFORE_COMPLETION_ANNO = "javax.ejb.BeforeCompletion";
  @NonNls String CONCURRENCY_MANAGEMENT_ANNO = "javax.ejb.ConcurrencyManagement";
  @NonNls String DEPENDS_ON_ANNO = "javax.ejb.DependsOn";
  @NonNls String EJB_ANNO = "javax.ejb.EJB";
  @NonNls String EJB_BEAN_INTERFACE_PARAM = "beanInterface";
  @NonNls String EJB_BEAN_NAME_PARAM = "beanName";
  // @NonNls String EJB_DESCRIPTION_PARAM = "description";
  // @NonNls String EJB_MAPPED_NAME_PARAM = "mappedName";
  @NonNls String EJBS_ANNO = "javax.ejb.EJBs";
  @NonNls String INIT_ANNO = "javax.ejb.Init";
  @NonNls String LOCAL_ANNO = "javax.ejb.Local";
  @NonNls String LOCAL_BEAN_ANNO = "javax.ejb.LocalBean";
  @NonNls String LOCAL_HOME_ANNO = "javax.ejb.LocalHome";
  @NonNls String LOCK_ANNO = "javax.ejb.Lock";
  @NonNls String MESSAGE_DRIVEN_ACTIVATION_CONFIG_PARAM = "activationConfig";
  @NonNls String MESSAGE_DRIVEN_ANNO = "javax.ejb.MessageDriven";
  // @NonNls String MESSAGE_DRIVEN_DESCRIPTION_PARAM = "description";
  // @NonNls String MESSAGE_DRIVEN_MAPPED_NAME_PARAM = "mappedName";
  @NonNls String MESSAGE_DRIVEN_MESSAGE_LISTENER_INTERFACE_PARAM = "messageListenerInterface";
  @NonNls String POST_ACTIVATE_ANNO = "javax.ejb.PostActivate";
  @NonNls String PRE_PASSIVATE_ANNO = "javax.ejb.PrePassivate";
  @NonNls String REMOTE_ANNO = "javax.ejb.Remote";
  @NonNls String REMOTE_HOME_ANNO = "javax.ejb.RemoteHome";
  @NonNls String REMOVE_ANNO = "javax.ejb.Remove";
  @NonNls String REMOVE_RETAIN_IF_EXCEPTION_PARAM = "retainIfException";
  @NonNls String SCHEDULE_ANNO = "javax.ejb.Schedule";
  @NonNls String SCHEDULE_DAY_OF_MONTH_PARAM = "dayOfMonth";
  @NonNls String SCHEDULE_DAY_OF_WEEK_PARAM = "dayOfWeek";
  @NonNls String SCHEDULE_HOUR_PARAM = "hour";
  @NonNls String SCHEDULE_INFO_PARAM = "info";
  @NonNls String SCHEDULE_MINUTE_PARAM = "minute";
  @NonNls String SCHEDULE_MONTH_PARAM = "month";
  @NonNls String SCHEDULE_PERSISTENT_PARAM = "persistent";
  @NonNls String SCHEDULE_SECOND_PARAM = "second";
  @NonNls String SCHEDULE_TIMEZONE_PARAM = "timezone";
  @NonNls String SCHEDULE_YEAR_PARAM = "year";
  @NonNls String SCHEDULES_ANNO = "javax.ejb.Schedules";
  @NonNls String SINGLETON_ANNO = "javax.ejb.Singleton";
  // @NonNls String SINGLETON_DESCRIPTION_PARAM = "description";
  // @NonNls String SINGLETON_MAPPED_NAME_PARAM = "mappedName";
  @NonNls String STARTUP_ANNO = "javax.ejb.Startup";
  @NonNls String STATEFUL_ANNO = "javax.ejb.Stateful";
  // @NonNls String STATEFUL_DESCRIPTION_PARAM = "description";
  // @NonNls String STATEFUL_MAPPED_NAME_PARAM = "mappedName";
  @NonNls String STATEFUL_TIMEOUT_ANNO = "javax.ejb.StatefulTimeout";
  // @NonNls String STATEFUL_TIMEOUT_UNIT_PARAM = "unit";
  @NonNls String STATELESS_ANNO = "javax.ejb.Stateless";
  // @NonNls String STATELESS_DESCRIPTION_PARAM = "description";
  // @NonNls String STATELESS_MAPPED_NAME_PARAM = "mappedName";
  @NonNls String TIMEOUT_ANNO = "javax.ejb.Timeout";
  @NonNls String TRANSACTION_ATTRIBUTE_ANNO = "javax.ejb.TransactionAttribute";
  @NonNls String TRANSACTION_MANAGEMENT_ANNO = "javax.ejb.TransactionManagement";
  @NonNls String UNIT_PARAM = "unit";
  @NonNls String DESCRIPTION_PARAM = "description";
  @NonNls String MAPPED_NAME_PARAM = "mappedName";
}
