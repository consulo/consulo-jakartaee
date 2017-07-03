package org.jetbrains.idea.tomcat.server;

import com.intellij.javaee.oss.server.JavaeeIntegration;
import com.intellij.javaee.oss.server.JavaeePersistentDataWithBaseEditor;
import com.intellij.ui.components.JBLabel;
import org.jetbrains.idea.tomcat.TomcatConstants;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * User: michael.golubev
 */
public class TomcatPersistentDataEditor extends JavaeePersistentDataWithBaseEditor<TomcatPersistentData> {

  private JBLabel myTomEELabel;
  private JPanel myMainPanel;

  private final boolean myTomEEOnly;

  public TomcatPersistentDataEditor(TomcatIntegration integration, boolean tomEEOnly) {
    super(integration);
    myTomEEOnly = tomEEOnly;
    getCustomPanelPlaceHolder().add(myMainPanel);
  }

  @Override
  protected void onHomeChanged() {
    boolean tomEE = isValidHomeSelected() && TomcatIntegration.isTomEE(getHome());
    myTomEELabel.setVisible(!myTomEEOnly && tomEE);
    super.onHomeChanged();
  }

  @Override
  protected void doValidateBaseDir(String baseDir) throws FileNotFoundException {
    File base = new File(baseDir);
    JavaeeIntegration.checkDir(base);
    JavaeeIntegration.checkDir(new File(base, TomcatConstants.CATALINA_CONFIG_DIRECTORY_NAME));
  }
}
