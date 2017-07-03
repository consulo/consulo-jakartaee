/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss;

import org.jetbrains.annotations.NonNls;
import com.intellij.ide.plugins.cl.PluginClassLoader;
import com.intellij.openapi.diagnostic.Logger;

public class JavaeeLogger {

    private static final Logger logger = Logger.getInstance(getLoggerName());

    private JavaeeLogger() {
    }

    public static void error(Throwable t) {
        logger.error(t);
    }

    @NonNls
    private static String getLoggerName() {
        ClassLoader loader = JavaeeLogger.class.getClassLoader();
        if (loader instanceof PluginClassLoader) {
            return "com.fuhrer.plugin." + ((PluginClassLoader) loader).getPluginId();
        } else {
            return "com.fuhrer.plugin.JavaEE";
        }
    }
}
