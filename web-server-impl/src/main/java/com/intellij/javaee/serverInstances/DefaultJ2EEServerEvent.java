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
package com.intellij.javaee.serverInstances;

public class DefaultJ2EEServerEvent implements J2EEServerEvent {
  private final boolean myIsRunning;
  private final boolean myIsDisconnected;
  private final boolean myIsBeforeDisconnect;
  private final boolean myIsStateProcessKilled;

  /**
   * @deprecated First two parameters are no longer used.
   */
  public DefaultJ2EEServerEvent(String newState, String eventDescription, boolean isRunning, boolean isDisconnected) {
    this(isRunning, isDisconnected);
  }

  public DefaultJ2EEServerEvent(boolean isRunning, boolean isDisconnected, boolean isBeforeDisconnect, boolean isStateProcessKilled) {
    myIsRunning = isRunning;
    myIsDisconnected = isDisconnected;
    myIsBeforeDisconnect = isBeforeDisconnect;
    myIsStateProcessKilled = isStateProcessKilled;
  }

  public DefaultJ2EEServerEvent(boolean isRunning, boolean isDisconnected, boolean isBeforeDisconnect) {
    this(isRunning, isDisconnected, isBeforeDisconnect, false);
  }

  public DefaultJ2EEServerEvent(boolean isRunning, boolean isDisconnected) {
    this(isRunning, isDisconnected, false);
  }

  public boolean isStateRunning() {
    return myIsRunning;
  }

  public boolean isStateDisconnected() {
    return myIsDisconnected;
  }

  @Override
  public boolean isStateBeforeDisconnect() {
    return myIsBeforeDisconnect;
  }

  @Override
  public boolean isStateProcessKilled() {
    return myIsStateProcessKilled;
  }
}
