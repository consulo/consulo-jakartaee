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

// Generated on Mon Nov 28 17:07:24 MSK 2005
// DTD/Schema  :    http://java.sun.com/xml/ns/javaee

package com.intellij.javaee.model.xml.compatibility;

import consulo.xml.util.xml.NamedEnum;
import org.jetbrains.annotations.NonNls;

/**
 * MessageDestination SubscriptionDurability enum.
 * <pre>
 * The subscription-durability element specifies whether a JMS topic
 * subscription is intended to be durable or nondurable.
 * The subscription-durability element must be one of the two following:
 * 	<subscription-durability>Durable</subscription-durability>
 * 	<subscription-durability>NonDurable</subscription-durability>
 * Used in: message-driven-destination
 * </pre>
 */
public enum SubscriptionDurabilityType implements NamedEnum {
        DURABLE ("Durable"),
        NONDURABLE("NonDurable");

        private final String value;
        private SubscriptionDurabilityType(@NonNls String value) { this.value = value; }
        public String getValue() { return value; }

}
