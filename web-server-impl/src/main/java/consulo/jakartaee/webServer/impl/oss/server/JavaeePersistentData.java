/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.server;

import org.jetbrains.annotations.NonNls;
import consulo.jakartaee.webServer.impl.appServerIntegrations.DefaultPersistentData;

public class JavaeePersistentData extends DefaultPersistentData {

    @NonNls
    @SuppressWarnings({"PublicField", "InstanceVariableNamingConvention", "NonConstantFieldWithUpperCaseName"})
    public String HOME = "";

    @NonNls
    @SuppressWarnings({"PublicField", "InstanceVariableNamingConvention", "NonConstantFieldWithUpperCaseName"})
    public String VERSION = "";
}
