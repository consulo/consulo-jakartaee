/*
 * Copyright 2000-2010 JetBrains s.r.o.
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

/*
 * User: anna
 * Date: 13-May-2010
 */
package consulo.jakartaee.webServer.impl.run.configuration;

import consulo.execution.RunnerAndConfigurationSettings;
import consulo.execution.action.ConfigurationContext;
import consulo.execution.action.Location;
import consulo.execution.action.RuntimeConfigurationProducer;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;

public class J2EEConfigurationProducer extends RuntimeConfigurationProducer
{
  private PsiFile myContainingFile;

  public J2EEConfigurationProducer(final J2EEConfigurationType configurationType) {
    super(configurationType);
  }

  @Override
  public PsiElement getSourceElement() {
    return myContainingFile;
  }

  @Override
  protected RunnerAndConfigurationSettings createConfigurationByElement(Location location, ConfigurationContext context) {
    myContainingFile = location.getPsiElement().getContainingFile();
    if (myContainingFile == null) return null;
    return J2EEConfigurationFactory.getInstance().createSettingsByFile(myContainingFile, (J2EEConfigurationType)getConfigurationType());
  }

  public int compareTo(Object o) {
    return PREFERED;
  }
}
