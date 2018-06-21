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
package com.intellij.javaee.run.localRun;

import javax.annotation.Nullable;

import com.intellij.execution.runners.ProgramRunner;


public interface ExecutableObjectStartupPolicy
{
	ExecutableObjectStartupPolicy DEFAULT = new ExecutableObjectStartupPolicy()
	{
		@Override
		public EnvironmentHelper getEnvironmentHelper()
		{
			return null;
		}

		@Override
		public ScriptHelper createStartupScriptHelper(final ProgramRunner runner)
		{
			return null;
		}

		@Override
		public ScriptHelper createShutdownScriptHelper(final ProgramRunner runner)
		{
			return null;
		}
	};

	/**
	 * @deprecated implement {@link ExecutableObjectStartupPolicy#createStartupScriptHelper(ProgramRunner)} instead
	 */
	@Nullable
	default ScriptsHelper getStartupHelper()
	{
		return null;
	}

	/**
	 * @deprecated implement {@link ExecutableObjectStartupPolicy#createShutdownScriptHelper(ProgramRunner)} instead
	 */
	@Nullable
	default ScriptsHelper getShutdownHelper()
	{
		return null;
	}

	@Nullable
	ScriptHelper createStartupScriptHelper(ProgramRunner runner);

	@Nullable
	ScriptHelper createShutdownScriptHelper(ProgramRunner runner);

	@Nullable
	EnvironmentHelper getEnvironmentHelper();
}
