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
package consulo.jakartaee.webServer.impl.dataSource;

import consulo.process.ProcessHandler;

/**
 * FIXME [VISTLAL] you seriously depend to database plugin by one class?
 */
public interface ServerInstance
{
	void start(ProcessHandler processHandler);

	boolean isStopped();

	boolean isStarting();

	boolean isConnected();

	void shutdown();

	boolean connect() throws Exception;

	void registerServerError(Throwable e1);

	String getName();
}