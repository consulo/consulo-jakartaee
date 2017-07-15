/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.server;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.intellij.util.PathUtil;

class JavaeeClassLoader extends URLClassLoader {

    private static final URL[] EMPTY = {};

    private final Set<String> excludes = new HashSet<String>();
  private final ClassLoader parent;

  JavaeeClassLoader(Collection<File> libraries, Collection<Class<?>> excludes, ClassLoader parent) {
        super(EMPTY, null);
    this.parent = parent;
    for (File library : libraries) {
            if (library.exists()) {
                addFile(library);
                if (library.isDirectory()) {
                    addLibraries(library);
                }
            }
        }
        for (Class<?> exclude : excludes) {
            this.excludes.add(exclude.getName());
        }
    }

    @Override
    @SuppressWarnings({"NonSynchronizedMethodOverridesSynchronizedMethod"})
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (name.startsWith("com.intellij.") ||  excludes.contains(name)) {
            return getClass().getClassLoader().loadClass(name);
        } else if (name.startsWith("com.fuhrer.")) {
          Class<?> clazz = parent.loadClass(name);
          if (clazz != null) {
            String path = PathUtil.getJarPathForClass(clazz);
            addFile(new File(path));
          }          
        }
      return super.loadClass(name, resolve);
    }

    private void addLibraries(File dir) {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                addLibraries(file);
            } else if (file.getName().endsWith(".jar")) {
                addFile(file);
            }
        }
    }

    private void addFile(File file) {
        try {
            addURL(file.toURI().toURL());
        } catch (MalformedURLException ignore) {
        }
    }
}
