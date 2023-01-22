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

/**
 * MessageDestination DestinationType enum.
 * <pre>
 * The destination-type element specifies the type of the JMS
 * destination. The type is specified by the Java interface expected to
 * be implemented by the destination.
 * The destination-type element must be one of the two following:
 * <destination-type>javax.jms.Queue</destination-type>
 * <destination-type>javax.jms.Topic</destination-type>
 * Used in: message-driven-destination
 * </pre>
 */
public enum MessageDrivenDestinationType implements NamedEnum {
        QUEUE ("javax.jms.Queue"),
        TOPIC("javax.jms.Topic");

        private final String value;
        private MessageDrivenDestinationType(String value) { this.value = value; }
        public String getValue() { return value; }

}
