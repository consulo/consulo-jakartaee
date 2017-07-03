/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.server;

import org.jetbrains.annotations.NonNls;
import com.intellij.javaee.appServerIntegrations.DefaultPersistentData;

public class JavaeePersistentData extends DefaultPersistentData {

    @NonNls
    @SuppressWarnings({"PublicField", "InstanceVariableNamingConvention", "NonConstantFieldWithUpperCaseName"})
    public String HOME = "";

    @NonNls
    @SuppressWarnings({"PublicField", "InstanceVariableNamingConvention", "NonConstantFieldWithUpperCaseName"})
    public String VERSION = "";
}
