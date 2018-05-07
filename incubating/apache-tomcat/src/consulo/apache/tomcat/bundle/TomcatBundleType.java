/*
 * Copyright 2013-2017 consulo.io
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

package consulo.apache.tomcat.bundle;

import java.io.File;
import java.io.IOException;

import javax.swing.Icon;

import org.jetbrains.annotations.NonNls;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.jetbrains.idea.tomcat.TomcatDeploymentSettingsEditor;
import org.jetbrains.idea.tomcat.TomcatModuleDeploymentModel;
import org.jetbrains.idea.tomcat.server.TomcatIntegration;
import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.oss.server.JavaeeIntegration;
import com.intellij.javaee.oss.util.VersionUtil;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkModificator;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.libraries.JarVersionDetectionUtil;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.remoteServer.configuration.deployment.DeploymentSource;
import consulo.apache.tomcat.TomcatIcons;
import consulo.javaee.bundle.JavaEEServerBundleType;
import consulo.roots.types.BinariesOrderRootType;

/**
 * @author VISTALL
 * @since 04.11.13.
 */
public class TomcatBundleType extends JavaEEServerBundleType
{
	@Nonnull
	public static TomcatBundleType getInstance()
	{
		return EP_NAME.findExtension(TomcatBundleType.class);
	}

	public static String getExecutablePath(String home)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(home);
		builder.append(File.separator);
		builder.append("bin");
		builder.append(File.separator);
		builder.append("catalina");
		if(SystemInfo.isWindows)
		{
			builder.append(".bat");
		}
		else
		{
			builder.append(".sh");
		}
		return builder.toString();
	}

	public TomcatBundleType()
	{
		this("APACHE_TOMCAT_SDK");
	}

	protected TomcatBundleType(@NonNls String name)
	{
		super(name);
	}

	@Override
	public boolean isJreCustomizable()
	{
		return true;
	}

	@Override
	public DeploymentModel createNewDeploymentModel(CommonModel commonModel, DeploymentSource source)
	{
		return new TomcatModuleDeploymentModel(commonModel, source);
	}

	@Override
	public SettingsEditor<DeploymentModel> createAdditionalDeploymentSettingsEditor(CommonModel commonModel, DeploymentSource source)
	{
		return new TomcatDeploymentSettingsEditor(commonModel, source);
	}

	@Override
	public boolean isValidSdkHome(String s)
	{
		return new File(getExecutablePath(s)).exists();
	}

	@Nullable
	@Override
	public String getVersionString(String sdkHome)
	{
		File catalinaJar = new File(sdkHome, "lib/catalina.jar");
		boolean isServerLib = false;
		if(!catalinaJar.exists())
		{
			catalinaJar = new File(sdkHome, "server/lib/catalina.jar");
			isServerLib = true;
			if(!catalinaJar.exists())
			{
				return null;
			}
		}

		String version;
		try
		{
			String versionProperty = StringUtil.notNullize(VersionUtil.readJarProperty(catalinaJar, "org/apache/catalina/util/ServerInfo.properties", "server.info"));
			version = StringUtil.trimStart(versionProperty, "Apache Tomcat/");
			if(StringUtil.isEmpty(version))
			{
				version = JarVersionDetectionUtil.getImplementationVersion(catalinaJar);
			}
			if(StringUtil.isEmpty(version))
			{
				throw new IOException("Version properties are empty");
			}
		}
		catch(IOException e)
		{
			if(!isServerLib)
			{
				return null;
			}
			version = "4.0.x";
		}
		return version;
	}

	@Override
	public void setupSdkPaths(Sdk sdk)
	{
		SdkModificator modificator = sdk.getSdkModificator();

		VirtualFile homeDirectory = sdk.getHomeDirectory();
		assert homeDirectory != null;

		VirtualFile jspApi = homeDirectory.findFileByRelativePath("lib/jsp-api.jar");
		if(jspApi != null)
		{
			modificator.addRoot(jspApi, BinariesOrderRootType.getInstance());
		}

		VirtualFile servletApi = homeDirectory.findFileByRelativePath("lib/servlet-api.jar");
		if(servletApi != null)
		{
			modificator.addRoot(servletApi, BinariesOrderRootType.getInstance());
		}

		modificator.commitChanges();
	}

	@Override
	public boolean isRootTypeApplicable(OrderRootType type)
	{
		return JavaSdk.getInstance().isRootTypeApplicable(type);
	}

	@Override
	public String suggestSdkName(String currentSdkName, String sdkHome)
	{
		return getPresentableName() + " " + getVersionString(sdkHome);
	}

	@Nonnull
	@Override
	public String getPresentableName()
	{
		return "Apache Tomcat";
	}

	@Nullable
	@Override
	public Icon getIcon()
	{
		return TomcatIcons.Tomcat;
	}

	@Override
	public JavaeeIntegration getIntegration()
	{
		return TomcatIntegration.getInstance();
	}
}