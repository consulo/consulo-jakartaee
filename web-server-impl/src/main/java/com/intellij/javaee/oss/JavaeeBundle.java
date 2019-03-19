/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss;

import com.intellij.AbstractBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.PropertyKey;

import javax.annotation.Nonnull;

public class JavaeeBundle extends AbstractBundle {

    @NonNls
    private static final String PATH = "resources.javaee";

    private static final JavaeeBundle ourInstance = new JavaeeBundle();

    public JavaeeBundle() {
        super(PATH);
    }

    @Nonnull
    public static String message(@PropertyKey(resourceBundle = PATH) String key, Object... params) {
        return ourInstance.getMessage(key, params);
    }
}
