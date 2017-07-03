/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.server;

import java.util.HashMap;
import java.util.Map;

import com.intellij.javaee.oss.util.CachedConfig;

public abstract class JavaeePortConfig extends CachedConfig<JavaeeServerModel>
{

    public static final int INVALID_PORT = Integer.MAX_VALUE;

    private static final Map<Key, JavaeePortConfig> cache = new HashMap<Key, JavaeePortConfig>();

    private int port;

    @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass", "AccessingNonPublicFieldOfAnotherObject"})
    protected static <M extends JavaeeServerModel> int get(Factory<M> factory, M model, int dflt) {
        JavaeePortConfig config = get(cache, factory, model);
        if (config != null) {
            int port = config.port;
            if (port != INVALID_PORT) {
                return port;
            }
        }
        return dflt;
    }

    @Override
    protected void update(JavaeeServerModel data) {
        port = getPort(data);
    }

    protected abstract int getPort(JavaeeServerModel model);

    @SuppressWarnings({"ProtectedInnerClass", "MarkerInterface", "ClassNameSameAsAncestorName"})
    protected interface Factory<T extends JavaeeServerModel> extends CachedConfig.Factory<T, JavaeePortConfig> {

    }
}
