package org.jetbrains.idea.tomcat;

import com.intellij.execution.ExecutionException;
import org.jdom.Element;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public abstract class TomcatContextItem {

  private final Element myElement;
  private final String myPath;

  public TomcatContextItem(Element element, String path) {
    myElement = element;
    myPath = path;
  }

  public Element getElement() {
    return myElement;
  }

  public String getPath() {
    return myPath;
  }

  public abstract void remove() throws ExecutionException;
}
