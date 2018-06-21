package com.intellij.javaee.model.xml.web.converters;

import java.util.Collection;

import com.intellij.jam.model.common.CommonModelElement;
import com.intellij.jam.view.JamDeleteHandler;
import com.intellij.javaee.model.xml.Listener;
import com.intellij.javaee.model.xml.web.Filter;
import com.intellij.javaee.model.xml.web.FilterMapping;
import com.intellij.javaee.model.xml.web.Servlet;
import com.intellij.javaee.model.xml.web.ServletMapping;
import com.intellij.psi.PsiElement;
import com.intellij.util.containers.ContainerUtil;

/**
 * @author Dmitry Avdeev
 *         Date: 6/10/11
 */
public class WebDeleteHandler extends JamDeleteHandler {

  public void addPsiElements(final CommonModelElement javaeeModelElement, final Collection<PsiElement> result) {
    if (javaeeModelElement instanceof Servlet) {
      final Servlet servlet = (Servlet)javaeeModelElement;
      ContainerUtil.addIfNotNull(result, servlet.getServletClass().getValue());
      for (final ServletMapping mapping : servlet.getParent().getServletMappings()) {
        if (servlet.equals(mapping.getServletName().getValue())) {
          result.add(mapping.getXmlTag());
        }
      }
    }
    else if (javaeeModelElement instanceof Filter) {
      final Filter filter = (Filter)javaeeModelElement;
      ContainerUtil.addIfNotNull(result, filter.getFilterClass().getValue());
      for (final FilterMapping mapping : filter.getParent().getFilterMappings()) {
        if (filter.equals(mapping.getFilterName().getValue())) {
          result.add(mapping.getXmlTag());
        }
      }
    }
    else if (javaeeModelElement instanceof Listener) {
      ContainerUtil.addIfNotNull(result, ((Listener)javaeeModelElement).getListenerClass().getValue());
    }
  }

}
