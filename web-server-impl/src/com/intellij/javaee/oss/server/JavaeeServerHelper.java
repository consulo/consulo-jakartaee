/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipFile;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.intellij.javaee.appServerIntegrations.ApplicationServerHelper;
import com.intellij.javaee.appServerIntegrations.ApplicationServerInfo;
import com.intellij.javaee.appServerIntegrations.ApplicationServerPersistentData;
import com.intellij.javaee.appServerIntegrations.ApplicationServerPersistentDataEditor;
import com.intellij.javaee.appServerIntegrations.CantFindApplicationServerJarsException;
import com.intellij.javaee.model.common.JavaeeCommonConstants;
import com.intellij.javaee.oss.JavaeeBundle;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.util.Function;
import com.intellij.util.containers.ContainerUtil;

public class JavaeeServerHelper implements ApplicationServerHelper {

    @NonNls
    private static final String[] CLASSES = {
            JavaeeCommonConstants.SERVLET_CLASS,
            JavaeeCommonConstants.JSP_PAGE_CLASS,
            JavaeeCommonConstants.ENTITY_BEAN_CLASS
    };

    public ApplicationServerPersistentDataEditor<JavaeePersistentData> createConfigurable() {
        return new JavaeePersistentDataEditor();
    }

    public ApplicationServerPersistentData createPersistentDataEmptyInstance() {
        return new JavaeePersistentData();
    }

    public ApplicationServerInfo getApplicationServerInfo(final ApplicationServerPersistentData data) throws CantFindApplicationServerJarsException {
        final List<File> libraries = new ArrayList<File>();
        Function<String, String> mapper = new Function<String, String>() {
            @NonNls
            public String fun(String type) {
                return type.replace('.', '/') + ".class";
            }
        };
        final Collection<String> classes = ContainerUtil.map2List(CLASSES, mapper);
        new Task.Modal(null, JavaeeBundle.getText("ServerHelper.libraries.title"), true) {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                getLibraries(libraries, classes, ((JavaeePersistentData) data).HOME, indicator);
            }
        }.queue();
        if (!JavaeeIntegration.getInstance().allLibrariesFound(classes, mapper)) {
            throw new CantFindApplicationServerJarsException(JavaeeBundle.getText("ServerHelper.libraries.error"));
        }
        String name = JavaeeIntegration.getInstance().getName() + ' ' + ((JavaeePersistentData) data).VERSION;
        return new ApplicationServerInfo(libraries.toArray(new File[libraries.size()]), name);
    }

    private void getLibraries(List<File> libraries, Collection<String> classes, String home, ProgressIndicator indicator) {
        indicator.setIndeterminate(true);
        List<File> candidates = getCandidates(home, indicator);
        indicator.setIndeterminate(false);
        for (File file : candidates) {
            if (!indicator.isCanceled()) {
                indicator.setText(file.getAbsolutePath());
                indicator.setFraction((double) candidates.indexOf(file) / (double) candidates.size());
                try {
                    ZipFile zip = new ZipFile(file);
                    for (Iterator<String> iter = classes.iterator(); iter.hasNext();) {
                        String name = iter.next();
                        if (zip.getEntry(name) != null) {
                            libraries.add(file);
                            iter.remove();
                        }
                    }
                } catch (IOException ignore) {
                }
            }
        }
        if (indicator.isCanceled()) {
            libraries.clear();
        }
    }

    private List<File> getCandidates(String home, ProgressIndicator indicator) {
        List<File> locations = new ArrayList<File>();
        List<File> candidates = new ArrayList<File>();
        Set<File> visited = new HashSet<File>();
        JavaeeIntegration.getInstance().addLibraryLocations(home, locations);
        for (File file : locations) {
            getCandidates(file, candidates, visited, indicator);
        }
        return candidates;
    }

    private void getCandidates(File base, List<File> candidates, Set<File> visited, ProgressIndicator indicator) {
        if (base.isDirectory() && visited.add(base) && !indicator.isCanceled()) {
            for (File file : base.listFiles()) {
                if (file.isDirectory()) {
                    getCandidates(file, candidates, visited, indicator);
                } else if (file.getName().endsWith(".jar")) {
                    candidates.add(file);
                }
            }
        }
    }
}
