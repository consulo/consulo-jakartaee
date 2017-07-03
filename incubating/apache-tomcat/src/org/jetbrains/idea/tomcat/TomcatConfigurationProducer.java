/*
 * User: anna
 * Date: 13-May-2010
 */
package org.jetbrains.idea.tomcat;

import org.jetbrains.idea.tomcat.server.TomcatConfiguration;
import com.intellij.javaee.run.configuration.J2EEConfigurationProducer;

public class TomcatConfigurationProducer extends J2EEConfigurationProducer {

  public TomcatConfigurationProducer() {
    super(TomcatConfiguration.getInstance());
  }
}
