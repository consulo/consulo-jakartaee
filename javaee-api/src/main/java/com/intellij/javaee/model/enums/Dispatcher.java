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

// Generated on Mon Jan 23 20:05:38 MSK 2006
// DTD/Schema  :    http://java.sun.com/xml/ns/javaee

package com.intellij.javaee.model.enums;

/**
 * http://java.sun.com/xml/ns/javaee:dispatcherType enumeration.
 * <pre>
 * <h3>Enumeration http://java.sun.com/xml/ns/javaee:dispatcherType documentation</h3>
 * The dispatcher has four legal values: FORWARD, REQUEST, INCLUDE,
 * 	and ERROR. A value of FORWARD means the Filter will be applied
 * 	under RequestDispatcher.forward() calls.  A value of REQUEST
 * 	means the Filter will be applied under ordinary client calls to
 * 	the path or servlet. A value of INCLUDE means the Filter will be
 * 	applied under RequestDispatcher.include() calls.  A value of
 * 	ERROR means the Filter will be applied under the error page
 * 	mechanism.  The absence of any dispatcher elements in a
 * 	filter-mapping indicates a default of applying filters only under
 * 	ordinary client calls to the path or servlet.
 * </pre>
 */
public enum Dispatcher {
	ERROR,
	FORWARD,
	INCLUDE,
	REQUEST
}
