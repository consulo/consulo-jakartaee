/*
 * Copyright 2000-2007 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.jam.view.tree;

import com.intellij.ide.util.treeView.AbstractTreeBuilder;
import com.intellij.ide.util.treeView.AlphaComparator;
import com.intellij.ide.util.treeView.NodeDescriptor;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.util.ProgressIndicatorBase;
import com.intellij.openapi.progress.util.ProgressIndicatorUtils;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.ui.treeStructure.SimpleNode;
import com.intellij.ui.treeStructure.SimpleTreeStructure;
import javax.annotation.Nonnull;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * @author peter
 */
public class JamAbstractTreeBuilder extends AbstractTreeBuilder {

  public JamAbstractTreeBuilder(final Project project, final JTree tree, DefaultTreeModel treeModel, SimpleNode rootDescriptor) {
    this(tree, treeModel, new JamTreeStructure(rootDescriptor, project));
  }

  public JamAbstractTreeBuilder(final JTree tree, SimpleTreeStructure structure) {
    this(tree, new DefaultTreeModel(new DefaultMutableTreeNode()), structure);
  }
  
  public JamAbstractTreeBuilder(final JTree tree, DefaultTreeModel treeModel, SimpleTreeStructure structure) {
    super(tree, treeModel, structure, null);
    setNodeDescriptorComparator(AlphaComparator.INSTANCE);
  }

  public boolean isAlwaysShowPlus(NodeDescriptor nodeDescriptor) {
    return ((SimpleNode)nodeDescriptor).isAlwaysShowPlus();
  }

  public boolean isAutoExpandNode(NodeDescriptor nodeDescriptor) {
    return super.isAutoExpandNode(nodeDescriptor) || ((SimpleNode)nodeDescriptor).isAutoExpandNode();
  }

  protected final void expandNodeChildren(@Nonnull final DefaultMutableTreeNode node) {
    Object element = ((NodeDescriptor)node.getUserObject()).getElement();
    VirtualFile virtualFile = null;
    if (element instanceof PsiDirectory){
      virtualFile = ((PsiDirectory)element).getVirtualFile();
    }
    else if (element instanceof PsiFile){
      virtualFile = ((PsiFile)element).getVirtualFile();
    }
    if (virtualFile != null) {
      virtualFile.refresh(true, false);
    }
    super.expandNodeChildren(node);
  }

  public void init() {
    initRootNode();
  }

  protected Object getTreeStructureElement(NodeDescriptor nodeDescriptor) {
    return nodeDescriptor;
  }

  protected ProgressIndicator createProgressIndicator() {
    if (isToBuildChildrenInBackground(getRootElement())) {
      return ProgressIndicatorUtils.forceWriteActionPriority(new ProgressIndicatorBase(true), this);
    }
    return super.createProgressIndicator();
  }
}