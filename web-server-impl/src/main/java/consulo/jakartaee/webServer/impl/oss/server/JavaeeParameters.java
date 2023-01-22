/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.server;

import consulo.util.lang.StringUtil;
import org.jetbrains.annotations.NonNls;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaeeParameters {

    private final List<String> list = new ArrayList<String>();

    public void add(@NonNls String... parameters) {
        for (String parameter : parameters) {
            if (StringUtil.isEmpty(parameter)) {
                return;
            }
        }
        list.addAll(Arrays.asList(parameters));
    }

    public void add(File file) {
        list.add(file.getAbsolutePath());
    }

    public String[] get() {
        return list.toArray(new String[list.size()]);
    }
}
