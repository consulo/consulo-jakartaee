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

// Generated on Mon Nov 28 17:06:26 MSK 2005
// DTD/Schema  :    ejb-jar_2_0.dtd

package com.intellij.javaee.model.xml.compatibility;


import com.intellij.util.xml.*;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import org.jetbrains.annotations.NotNull;

/**
 * ejb-jar_2_0.dtd:message-driven-destination interface.
 * Type message-driven-destination documentation
 * <pre>
 * The message-driven-destination element provides advice to the Deployer
 * as to whether a message-driven bean is intended for a Queue or a
 * Topic. The declaration consists of: the type of the message-driven
 * bean's intended destination and an optional declaration of whether a
 * durable or non-durable subscription should be used if the
 * destination-type is javax.jms.Topic.
 * Used in: message-driven
 * </pre>
 */
public interface MessageDrivenDestination extends JavaeeDomModelElement {

	/**
         * <b>EJB2.0 Compatibility Method. Don't use for EJB 2.1 and up.</b><p>
	 * Returns the value of the destination-type child.
	 * Type destination-type documentation
	 * <pre>
	 * The destination-type element specifies the type of the JMS
	 * destination. The type is specified by the Java interface expected to
	 * be implemented by the destination.
	 * The destination-type element must be one of the two following:
	 * <destination-type>javax.jms.Queue</destination-type>
	 * <destination-type>javax.jms.Topic</destination-type>
	 * Used in: message-driven-destination
	 * </pre>
         * @return the value of the destination-type child
	 */
	@NotNull
	GenericDomValue<MessageDrivenDestinationType> getDestinationType();


	/**
         * <b>EJB2.0 Compatibility Method. Don't use for EJB 2.1 and up.</b><p>
	 * Returns the value of the subscription-durability child.
	 * Type subscription-durability documentation
	 * <pre>
	 * The subscription-durability element specifies whether a JMS topic
	 * subscription is intended to be durable or nondurable.
	 * The subscription-durability element must be one of the two following:
	 * 	<subscription-durability>Durable</subscription-durability>
	 * 	<subscription-durability>NonDurable</subscription-durability>
	 * Used in: message-driven-destination
	 * </pre>
         * @return the value of the subscription-durability child
	 */
	GenericDomValue<SubscriptionDurabilityType> getSubscriptionDurability();


}
