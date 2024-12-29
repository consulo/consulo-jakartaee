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

// Generated on Wed Apr 29 15:54:26 MSD 2009
// DTD/Schema  :    http://java.sun.com/xml/ns/javaee

package com.intellij.javaee.model.xml.ejb;

import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.javaee.model.xml.Description;
import consulo.xml.util.xml.GenericDomValue;
import consulo.xml.util.xml.Required;
import jakarta.annotation.Nonnull;

import java.util.List;

/**
 * http://java.sun.com/xml/ns/javaee:timerType interface.
 * <pre>
 * <h3>Type http://java.sun.com/xml/ns/javaee:timerType documentation</h3>
 * The timerType specifies an enterprise bean timer.  Each
 * 	timer is automatically created by the container upon
 * 	deployment.  Timer callbacks occur based on the
 * 	schedule attributes.  All callbacks are made to the
 * 	timeout-method associated with the timer.
 * 	A timer can have an optional start and/or end date. If
 * 	a start date is specified, it takes precedence over the
 * 	associated timer schedule such that any matching
 * 	expirations prior to the start time will not occur.
 * 	Likewise, no matching expirations will occur after any
 * 	end date.   Start/End dates are specified using the
 * 	XML Schema dateTime type, which follows the ISO-8601
 * 	standard for date(and optional time-within-the-day)
 * 	representation.
 * 	An optional flag can be used to control whether
 * 	this timer has persistent(true) delivery semantics or
 * 	non-persistent(false) delivery semantics.  If not specified,
 *         the value defaults to persistent(true).
 * 	A time zone can optionally be associated with a timer.
 * 	If specified, the timer's schedule is evaluated in the context
 *         of that time zone, regardless of the default time zone in which
 * 	the container is executing.   Time zones are specified as an
 * 	ID string.  The set of required time zone IDs is defined by
 * 	the Zone Name(TZ) column of the public domain zoneinfo database.
 * 	An optional info string can be assigned to the timer and
 * 	retrieved at runtime through the Timer.getInfo() method.
 * 	The timerType can only be specified on stateless session
 * 	beans, singleton session beans, and message-driven beans.
 * </pre>
 */
public interface Timer extends JavaeeDomModelElement {

	/**
	 * Returns the list of description children.
	 * @return the list of description children.
	 */
	@Nonnull
	List<Description> getDescriptions();
	/**
	 * Adds new child to the list of description children.
	 * @return created child
	 */
	Description addDescription();


	/**
	 * Returns the value of the schedule child.
	 * @return the value of the schedule child.
	 */
	@Nonnull
	@Required
	TimerSchedule getSchedule();


	/**
	 * Returns the value of the start child.
	 * @return the value of the start child.
	 */
	@Nonnull
	GenericDomValue<String> getStart();


	/**
	 * Returns the value of the end child.
	 * @return the value of the end child.
	 */
	@Nonnull
	GenericDomValue<String> getEnd();


	/**
	 * Returns the value of the timeout-method child.
	 * @return the value of the timeout-method child.
	 */
	@Nonnull
	@Required
	NamedMethod getTimeoutMethod();


	/**
	 * Returns the value of the persistent child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:true-falseType documentation</h3>
	 * This simple type designates a boolean with only two
	 * 	permissible values
	 * 	- true
	 * 	- false
	 * </pre>
	 * @return the value of the persistent child.
	 */
	@Nonnull
	GenericDomValue<Boolean> getPersistent();


	/**
	 * Returns the value of the timezone child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:string documentation</h3>
	 * This is a special string datatype that is defined by Java EE as
	 * 	a base type for defining collapsed strings. When schemas
	 * 	require trailing/leading space elimination as well as
	 * 	collapsing the existing whitespace, this base type may be
	 * 	used.
	 * </pre>
	 * @return the value of the timezone child.
	 */
	@Nonnull
	GenericDomValue<String> getTimezone();


	/**
	 * Returns the value of the info child.
	 * <pre>
	 * <h3>Type http://java.sun.com/xml/ns/javaee:string documentation</h3>
	 * This is a special string datatype that is defined by Java EE as
	 * 	a base type for defining collapsed strings. When schemas
	 * 	require trailing/leading space elimination as well as
	 * 	collapsing the existing whitespace, this base type may be
	 * 	used.
	 * </pre>
	 * @return the value of the info child.
	 */
	@Nonnull
	GenericDomValue<String> getInfo();


}
