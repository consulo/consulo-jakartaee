/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.util;

import com.intellij.javaee.oss.JavaeeLogger;
import com.intellij.util.PathUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public abstract class DirectoryScanner {

    @Nullable
    private final Pattern pattern;

    protected DirectoryScanner(@NonNls String pattern) {
        this.pattern = (pattern != null) ? Pattern.compile(pattern) : null;
    }

    public void scan(@NonNls String dir) throws IOException {
        scan(dir, getClass());
    }

    public void scan(@NonNls String dir, Class<?> ref) {
        try {
            File base = new File(PathUtil.getJarPathForClass(ref));
            if (base.isDirectory()) {
                scanDirectory(base, dir);
            } else if (base.exists()) {
                scanArchive(base, dir);
            }
        } catch (Throwable t) {
            JavaeeLogger.error(t);
        }
    }

    private void scanDirectory(File base, String dir) {
        File[] files = new File(base, dir).listFiles();
        if (files != null) {
            for (final File file : files) {
                if ((pattern == null) || pattern.matcher(file.getName()).matches()) {
                    try {
                        handle(new FileWrapper(file.getName(), '/' + dir + '/' + file.getName()) {
                            @Override
                            public InputStream getStream() throws IOException {
                                return new FileInputStream(file);
                            }
                        });
                    } catch (Throwable t) {
                        JavaeeLogger.error(t);
                    }
                }
            }
        }
    }

    private void scanArchive(File base, String dir) throws IOException {
        final ZipFile zip = new ZipFile(base);
        for (Enumeration<? extends ZipEntry> e = zip.entries(); e.hasMoreElements();) {
            final ZipEntry entry = e.nextElement();
            String name = entry.getName();
            if (name.startsWith(dir)) {
                name = entry.getName().substring(name.lastIndexOf('/') + 1);
                if ((pattern == null) || pattern.matcher(name).matches()) {
                    try {
                        handle(new FileWrapper(entry.getName().replaceFirst("(?:.*/)?(.*)", "$1"), '/' + entry.getName()) {
                            @Override
                            public InputStream getStream() throws IOException {
                                return zip.getInputStream(entry);
                            }
                        });
                    } catch (Throwable t) {
                        JavaeeLogger.error(t);
                    }
                }
            }
        }
    }

    protected abstract void handle(FileWrapper file) throws Exception;
}
