package com.intellij.javaee.appServerIntegrations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.intellij.javaee.artifact.JavaeeArtifactUtil;
import com.intellij.javaee.context.DefaultWebModuleContextProvider;
import com.intellij.javaee.context.DeploymentModelContext;
import com.intellij.javaee.context.FacetContextProvider;
import com.intellij.javaee.context.JavaeeAppFacetContextProvider;
import com.intellij.javaee.context.WebModuleContextProvider;
import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.javaee.serverInstances.J2EEServerInstance;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.deployment.DeploymentUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.packaging.artifacts.Artifact;
import com.intellij.util.Url;
import consulo.javaee.module.extension.JavaEEApplicationModuleExtension;
import consulo.javaee.module.extension.JavaEEModuleExtension;

/**
 * @author nik
 */
public class ApplicationServerUrlMapping implements AppServerDeployedFileUrlProvider
{

	private static final JavaEEModuleExtension DEFAULT_DEPLOYMENT_CONTEXT_KEY = null;

	private final Map<J2EEServerInstance, ServerInstanceMapping> myServerInstance2Mapping = new HashMap<>();

	private WebModuleContextProvider myCompositeWebModuleContextProvider;

	private WebModuleContextProvider getWebModuleContextProviders()
	{
		if(myCompositeWebModuleContextProvider == null)
		{
			final List<WebModuleContextProvider> webModuleContextProviders = new ArrayList<>();
			collectWebModuleContextProviders(webModuleContextProviders);
			myCompositeWebModuleContextProvider = new WebModuleContextProvider()
			{

				@Override
				@Nullable
				public String getContext(@Nonnull JavaEEApplicationModuleExtension earFacet, @Nullable String moduleWebUri)
				{
					for(WebModuleContextProvider provider : webModuleContextProviders)
					{
						String context = provider.getContext(earFacet, moduleWebUri);
						if(context != null)
						{
							return context;
						}
					}
					return null;
				}
			};
		}
		return myCompositeWebModuleContextProvider;
	}

	protected void collectWebModuleContextProviders(List<WebModuleContextProvider> webModuleContextProvider)
	{
		webModuleContextProvider.add(DefaultWebModuleContextProvider.INSTANCE);
	}

	private List<FacetContextProvider> myFacetContextProvider;

	private List<FacetContextProvider> getFacetContextProviders()
	{
		if(myFacetContextProvider == null)
		{
			myFacetContextProvider = new ArrayList<>();
			collectFacetContextProviders(myFacetContextProvider);
		}
		return myFacetContextProvider;
	}

	protected void collectFacetContextProviders(List<FacetContextProvider> facetContextProvider)
	{
		facetContextProvider.add(new JavaeeAppFacetContextProvider());
	}

	@Nullable
	public VirtualFile findSourceFile(@Nonnull J2EEServerInstance serverInstance, @Nonnull CommonModel model, @Nonnull Url url)
	{
		return null;
	}

	@Nullable
	@Deprecated
	/**
	 * @deprecated override {@link #findSourceFile(com.intellij.javaee.serverInstances.J2EEServerInstance, com.intellij.javaee.run.configuration.CommonModel, com.intellij.util.Url)}
	 */ public VirtualFile findSourceFile(@Nonnull J2EEServerInstance serverInstance, @Nonnull CommonModel model, @Nonnull String url)
	{
		return null;
	}

	/**
	 * @deprecated override {@link ApplicationServerUrlMapping#getUrlForDeployedFile(com.intellij.javaee.serverInstances.J2EEServerInstance, com.intellij.javaee.deployment.DeploymentModel, *
	 * JavaEEModuleExtension, String)} instead
	 */
	@Override
	@Nullable
	public String getUrlForDeployedFile(final J2EEServerInstance serverInstance, final DeploymentModel deploymentModel, final String relativePath)
	{
		return null;
	}

	public String getUrlForDeployedFile(J2EEServerInstance serverInstance, DeploymentModel deploymentModel)
	{
		return doGetUrlForDeployedFile(serverInstance, deploymentModel, null, null);
	}

	@Nullable
	public String getUrlForDeployedFile(@Nonnull J2EEServerInstance serverInstance,
			@Nonnull DeploymentModel deploymentModel,
			@Nonnull JavaEEModuleExtension<?> javaeeFacet,
			@Nonnull String relativePath)
	{
		return doGetUrlForDeployedFile(serverInstance, deploymentModel, javaeeFacet, relativePath);
	}

	public void startTrackingServerInstance(@Nonnull J2EEServerInstance serverInstance)
	{
		myServerInstance2Mapping.put(serverInstance, new ServerInstanceMapping());
	}

	public void stopTrackingServerInstance(@Nonnull J2EEServerInstance serverInstance)
	{
		myServerInstance2Mapping.remove(serverInstance);
	}

	public void updateDeploymentContexts(@Nonnull J2EEServerInstance serverInstance, @Nonnull DeploymentModel deploymentModel, boolean deployed)
	{
		ServerInstanceMapping serverInstanceMapping = myServerInstance2Mapping.get(serverInstance);
		if(serverInstanceMapping == null)
		{
			return;
		}
		if(deployed)
		{
			serverInstanceMapping.put(deploymentModel, getContextsForUnknownFacet(deploymentModel, false));
		}
		else
		{
			serverInstanceMapping.remove(deploymentModel);
		}
	}

	private String doGetUrlForDeployedFile(@Nonnull J2EEServerInstance serverInstance,
			@Nonnull DeploymentModel deploymentModel,
			@Nullable JavaEEModuleExtension javaeeFacet,
			@Nullable String relativePath)
	{

		ServerInstanceMapping serverInstanceMapping = myServerInstance2Mapping.get(serverInstance);
		if(serverInstanceMapping == null)
		{
			return null;
		}
		DeploymentMapping deploymentMapping = serverInstanceMapping.get(deploymentModel);
		if(deploymentMapping == null)
		{
			return null;
		}

		String context = deploymentMapping.get(javaeeFacet);
		return context == null ? null : doCreateUrl(serverInstance.getCommonModel(), context, relativePath);
	}

	private DeploymentMapping getContextsForUnknownFacet(final @Nonnull DeploymentModel deploymentModel, final boolean defaultOnly)
	{
		final DeploymentMapping deploymentMapping = new DeploymentMapping();

		final Artifact artifact = deploymentModel.getArtifact();
		if(artifact == null)
		{
			String context = getProvidedContext(deploymentModel);
			if(context == null)
			{
				context = getFileContext(deploymentModel);
			}
			deploymentMapping.put(DEFAULT_DEPLOYMENT_CONTEXT_KEY, context);
			return deploymentMapping;
		}

		new ReadAction()
		{

			@Override
			protected void run(@Nonnull Result result)
			{
				Project project = deploymentModel.getCommonModel().getProject();
				JavaeeArtifactUtil javaeeArtifactUtil = JavaeeArtifactUtil.getInstance();
				for(FacetContextProvider facetContextProvider : getFacetContextProviders())
				{
					for(JavaEEModuleExtension facet : javaeeArtifactUtil.getFacetsIncludedInArtifact(project, artifact, facetContextProvider.getFacetId()))
					{
						String facetContext = getContextForKnownFacet(deploymentModel, facet);
						if(facetContext != null)
						{
							deploymentMapping.put(facet, facetContext);
							if(!deploymentMapping.containsKey(DEFAULT_DEPLOYMENT_CONTEXT_KEY))
							{
								deploymentMapping.put(DEFAULT_DEPLOYMENT_CONTEXT_KEY, facetContext);
								if(defaultOnly)
								{
									return;
								}
							}
						}
					}
				}
			}
		}.execute();

		return deploymentMapping;
	}

	protected String getFileContext(DeploymentModel deploymentModel)
	{
		return null;
	}

	protected String doCreateUrl(@Nonnull CommonModel serverConfig, @Nullable String context, @Nullable String relativePath)
	{
		return createUrl(serverConfig, context, relativePath);
	}

	public static String createUrl(@Nonnull CommonModel serverConfig, @Nullable String context, @Nullable String relativePath)
	{
		String result = "http://" + serverConfig.getHost() + ':' + serverConfig.getPort();
		if(StringUtil.isNotEmpty(context))
		{
			result = DeploymentUtil.concatPaths(result, context);
		}
		if(StringUtil.isNotEmpty(relativePath))
		{
			result = DeploymentUtil.concatPaths(result, relativePath);
		}
		else
		{
			result = DeploymentUtil.concatPaths(result, "/");
		}
		return result;
	}

	public String getDefaultUrlForServerConfig(@Nonnull CommonModel serverConfig)
	{
		for(DeploymentModel deploymentModel : serverConfig.getDeploymentModels())
		{
			DeploymentMapping deploymentMapping = getContextsForUnknownFacet(deploymentModel, true);
			String context = deploymentMapping.get(null);
			if(context != null)
			{
				return doCreateUrl(serverConfig, context, null);
			}
		}
		return doCreateUrl(serverConfig, null, null);
	}

	private String getContextForKnownFacet(final @Nonnull DeploymentModel deploymentModel, final @Nonnull JavaEEModuleExtension javaeeFacet)
	{
		String providedContext = getProvidedContext(deploymentModel);
		if(providedContext != null)
		{
			return providedContext;
		}

		return new ReadAction<String>()
		{

			@Override
			protected void run(@Nonnull Result<String> result)
			{
				Class facetTypeId = javaeeFacet.getClass();
				for(FacetContextProvider facetContextProvider : getFacetContextProviders())
				{
					if(facetContextProvider.getFacetId().equals(facetTypeId))
					{
						result.setResult(facetContextProvider.getDeploymentContext(getWebModuleContextProviders(), deploymentModel, javaeeFacet));
						return;
					}
				}
			}
		}.execute().getResultObject();
	}

	@Nullable
	private static String getProvidedContext(@Nonnull DeploymentModel deployment)
	{
		if(!(deployment instanceof DeploymentModelContext))
		{
			return null;
		}
		DeploymentModelContext contextProvider = (DeploymentModelContext) deployment;
		return contextProvider.isDefaultContextRoot() ? null : contextProvider.getContextRoot();
	}

	private static class ServerInstanceMapping extends HashMap<DeploymentModel, DeploymentMapping>
	{
		// nothing but map
	}

	private static class DeploymentMapping extends HashMap<JavaEEModuleExtension, String>
	{
		// nothing but map
	}
}
