package com.intellij.javaee.model.xml.web.converters;

import com.intellij.jam.view.JamDeleteHandler;
import com.intellij.jam.model.common.CommonModelElement;
import com.intellij.javaee.model.xml.Listener;
import com.intellij.javaee.model.xml.web.*;
import com.intellij.psi.PsiElement;
import com.intellij.util.containers.ContainerUtil;

import java.util.Collection;

/**
 * @author Dmitry Avdeev
 *         Date: 6/10/11
 */
public class WebDeleteHandler extends JamDeleteHandler {

  public void addPsiElements(final CommonModelElement javaeeModelElement, final Collection<PsiElement> result) {
    if (javaeeModelElement instanceof Servlet) {
      final Servlet servlet = (Servlet)javaeeModelElement;
      ContainerUtil.addIfNotNull(servlet.getServletClass().getValue(), result);
      for (final ServletMapping mapping : servlet.getParent().getServletMappings()) {
        if (servlet.equals(mapping.getServletName().getValue())) {
          result.add(mapping.getXmlTag());
        }
      }
    }
    else if (javaeeModelElement instanceof Filter) {
      final Filter filter = (Filter)javaeeModelElement;
      ContainerUtil.addIfNotNull(filter.getFilterClass().getValue(), result);
      for (final FilterMapping mapping : filter.getParent().getFilterMappings()) {
        if (filter.equals(mapping.getFilterName().getValue())) {
          result.add(mapping.getXmlTag());
        }
      }
    }
    else if (javaeeModelElement instanceof Listener) {
      ContainerUtil.addIfNotNull(((Listener)javaeeModelElement).getListenerClass().getValue(), result);
    }
  }

}
