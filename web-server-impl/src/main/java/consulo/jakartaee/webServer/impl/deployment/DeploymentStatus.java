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
package consulo.jakartaee.webServer.impl.deployment;

import com.intellij.javaee.J2EEBundle;
import consulo.application.AllIcons;
import consulo.ui.image.Image;
import consulo.ui.image.ImageEffects;

import java.util.HashMap;
import java.util.Map;

public enum DeploymentStatus
{
	DEPLOYED(J2EEBundle.message("deployment.status.name.deployed"), J2EEBundle.message("deployment.status.description.deployed"), AllIcons.RunConfigurations.TestPassed),
	NOT_DEPLOYED(J2EEBundle.message("deployment.status.name.not.deployed"), J2EEBundle.message("deployment.status.description.not.deployed"), AllIcons.RunConfigurations.TestError),
	FAILED(J2EEBundle.message("deployment.status.name.failed"), J2EEBundle.message("deployment.status.description.failed"), AllIcons.RunConfigurations.TestFailed),
	DEACTIVATING(J2EEBundle.message("deployment.status.name.deactivating"), J2EEBundle.message("deployment.status.description.deactivating"), AllIcons.Process.Step_1),
	PREPARING(J2EEBundle.message("deployment.status.name.preparing"), J2EEBundle.message("deployment.status.description.preparing"), AllIcons.Process.Step_1),
	PREPARED(J2EEBundle.message("deployment.status.name.prepared"), J2EEBundle.message("deployment.status.description.prepared"), AllIcons.Process.Step_2),
	ACTIVATING(J2EEBundle.message("deployment.status.name.activating"), J2EEBundle.message("deployment.status.description.activating"), AllIcons.Process.Step_3),
	UNPREPARED(J2EEBundle.message("deployment.status.name.unprepared"), J2EEBundle.message("deployment.status.description.unprepared"), AllIcons.Process.Step_2),
	UNPREPARING(J2EEBundle.message("deployment.status.name.unpreparing"), J2EEBundle.message("deployment.status.description.unpreparing"), AllIcons.Process.Step_2),
	UNKNOWN(J2EEBundle.message("deployment.status.name.unknown"), J2EEBundle.message("deployment.status.description.unknown"), AllIcons.Actions.Help),
	DISCONNECTED(J2EEBundle.message("deployment.status.name.disconnected"), J2EEBundle.message("deployment.status.description.desconnected"), ImageEffects.grayed(AllIcons.Nodes.Plugin)),
	EXCLUDED_FROM_DEPLOYMENT(J2EEBundle.message("deployment.status.name.excluded.from.deployment"), J2EEBundle.message("deployment.status.description.excluded.from.deployment"), null)
			{
				private final Map<Image, Image> myPatchedIcon = new HashMap<>();

				@Override
				public Image getIcon(Image deploymentIcon)
				{
					Image icon = myPatchedIcon.get(deploymentIcon);
					if(icon == null)
					{
						icon = ImageEffects.layered(deploymentIcon, AllIcons.Nodes.ExcludedFromCompile);
						myPatchedIcon.put(deploymentIcon, icon);
					}
					return icon;
				}
			};

	private final String myName; // for debug only
	private final String myDescription;
	private final Image myIcon;

	private DeploymentStatus(String name, String description, Image icon)
	{
		myName = name;
		myDescription = description;
		myIcon = icon;
	}

	public String toString()
	{
		return myName;
	}

	public Image getIcon(Image deploymentIcon)
	{
		return myIcon;
	}

	public String getName()
	{
		return myName;
	}

	public String getDescription()
	{
		return myDescription;
	}
}
