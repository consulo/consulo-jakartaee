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
package com.intellij.javaee;

import org.jetbrains.annotations.PropertyKey;
import com.intellij.AbstractBundle;

/**
 * Created by IntelliJ IDEA.
 * User: lesya
 * Date: Aug 25, 2005
 * Time: 6:54:12 PM
 */
public class J2EEBundle extends AbstractBundle
{
	private static final J2EEBundle ourInstance = new J2EEBundle();

	private J2EEBundle()
	{
		super("messages.J2EEBundle");
	}

	public static String message(@PropertyKey(resourceBundle = "messages.J2EEBundle") String key)
	{
		return ourInstance.getMessage(key);
	}

	public static String message(@PropertyKey(resourceBundle = "messages.J2EEBundle") String key, Object... params)
	{
		return ourInstance.getMessage(key, params);
	}
}
