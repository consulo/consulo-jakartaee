/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss;

import java.util.HashMap;
import java.util.ListResourceBundle;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Nonnull;
import javax.swing.Icon;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.PropertyKey;
import com.intellij.CommonBundle;
import com.intellij.ide.plugins.cl.PluginClassLoader;
import com.intellij.openapi.util.IconLoader;

public abstract class JavaeeBundle extends ListResourceBundle {

    @NonNls
    private static final String PATH = "resources.javaee";

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(PATH);

    private static final Map<String, Icon> ICONS = new HashMap<String, Icon>();

    public static String getText(@PropertyKey(resourceBundle = PATH) String key, Object... params) {
        return CommonBundle.message(BUNDLE, key, params);
    }

    public static Icon getIcon(@NonNls String path) {
        Icon icon = ICONS.get(path);
        if (icon == null) {
            icon = IconLoader.getIcon(path);
            ICONS.put(path, icon);
        }
        return icon;
    }

    public static Icon getTransparentIcon(@NonNls String path) {
        Icon icon = ICONS.get("___" + path);
        if (icon == null) {
            icon = IconLoader.getTransparentIcon(getIcon(path));
            ICONS.put("___" + path, icon);
        }
        return icon;
    }

    @Override
    @SuppressWarnings({"ZeroLengthArrayAllocation"})
    protected Object[][] getContents() {
        ClassLoader loader = getClass().getClassLoader();
        if (loader instanceof PluginClassLoader) {
            @NonNls String key = "plugin." + ((PluginClassLoader) loader).getPluginId() + ".description";
            return new Object[][]{{key, getText("Integration.description", getName())}};
        } else {
            return new Object[][]{};
        }
    }

    @Nonnull
    protected abstract String getName();
}
