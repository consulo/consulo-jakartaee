package consulo.javaee.run.configuration.editor;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;

import org.jetbrains.annotations.NotNull;
import com.intellij.icons.AllIcons;
import com.intellij.javaee.deployment.DeploymentModel;
import com.intellij.javaee.run.configuration.CommonModel;
import com.intellij.openapi.actionSystem.ActionToolbarPosition;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.ui.popup.ListPopupStep;
import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import com.intellij.openapi.util.Disposer;
import com.intellij.packaging.artifacts.Artifact;
import com.intellij.packaging.artifacts.ArtifactManager;
import com.intellij.packaging.artifacts.ArtifactPointer;
import com.intellij.packaging.impl.ui.ChooseArtifactsDialog;
import com.intellij.remoteServer.configuration.deployment.ArtifactDeploymentSource;
import com.intellij.remoteServer.configuration.deployment.DeploymentSource;
import com.intellij.remoteServer.impl.configuration.deploySource.impl.ArtifactDeploymentSourceImpl;
import com.intellij.ui.ColoredListCellRenderer;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.panels.Wrapper;
import com.intellij.util.ui.JBUI;
import consulo.javaee.artifact.ExplodedWarArtifactType;
import consulo.javaee.bundle.JavaEEServerBundleType;
import consulo.javaee.deployment.impl.JavaEEDeploymentSettingsImpl;
import consulo.javaee.run.configuration.JavaEEConfigurationImpl;
import consulo.packaging.artifacts.ArtifactPointerUtil;
import consulo.packaging.impl.run.BuildArtifactsBeforeRunTaskProvider;

/**
 * @author VISTALL
 * @since 09-Jul-17
 */
public class JavaEEDeploymentConfigurationEditor extends SettingsEditor<JavaEEConfigurationImpl>
{
	public static String ARTIFACT = "Artifact...";

	private final Project myProject;
	private final JavaEEServerBundleType myBundleType;
	private final CommonModel myCommonModel;

	private JBList<DeployItem> myDeploySourceList = new JBList<>(new DefaultListModel<>());

	public JavaEEDeploymentConfigurationEditor(Project project, JavaEEServerBundleType bundleType, CommonModel commonModel)
	{
		myProject = project;
		myBundleType = bundleType;
		myCommonModel = commonModel;
	}

	@NotNull
	@Override
	protected JComponent createEditor()
	{
		DefaultListModel<DeployItem> model = (DefaultListModel<DeployItem>) myDeploySourceList.getModel();

		myDeploySourceList.setCellRenderer(new ColoredListCellRenderer<DeployItem>()
		{
			@Override
			protected void customizeCellRenderer(@NotNull JList<? extends DeployItem> list, DeployItem value, int index, boolean selected, boolean hasFocus)
			{
				DeploymentSource deploymentSource = value.getDeploymentSource();
				if(deploymentSource instanceof ArtifactDeploymentSource)
				{
					ArtifactPointer artifactPointer = ((ArtifactDeploymentSource) deploymentSource).getArtifactPointer();
					Artifact artifact = artifactPointer.get();
					if(artifact != null)
					{
						append(artifact.getName());
						setIcon(artifact.getArtifactType().getIcon());
					}
					else
					{
						append(artifactPointer.getName(), SimpleTextAttributes.ERROR_ATTRIBUTES);
						setIcon(AllIcons.Toolbar.Unknown);
					}
				}
				else
				{
					throw new UnsupportedOperationException(deploymentSource.getClass().getName());
				}
			}
		});

		JPanel rootPanel = new JPanel(new BorderLayout());
		rootPanel.setBorder(IdeBorderFactory.createTitledBorder("Deploy at server startup", false));

		JPanel mainPanel = new JPanel(new BorderLayout());

		ToolbarDecorator decorator = ToolbarDecorator.createDecorator(myDeploySourceList);
		decorator.setAddAction(anActionButton ->
		{
			ListPopupStep<String> step = new BaseListPopupStep<String>("Select source", ARTIFACT)
			{
				@Override
				public PopupStep onChosen(String selectedValue, boolean finalChoice)
				{
					if(ARTIFACT.equals(selectedValue))
					{
						return doFinalStep(() -> selectArtifact(model));
					}
					return super.onChosen(selectedValue, finalChoice);
				}

				@Override
				public PopupStep doFinalStep(Runnable runnable)
				{
					return super.doFinalStep(runnable);
				}

				@Override
				public Icon getIconFor(String value)
				{
					if(ARTIFACT.equals(value))
					{
						return AllIcons.Nodes.Artifact;
					}
					return super.getIconFor(value);
				}
			};

			ListPopup popup = JBPopupFactory.getInstance().createListPopup(step);
			popup.show(anActionButton.getPreferredPopupPoint());
		});

		decorator.setRemoveAction(anActionButton ->
		{
			DeployItem selectedValue = myDeploySourceList.getSelectedValue();
			if(selectedValue != null)
			{
				ArtifactPointer artifactPointer = selectedValue.getArtifactPointer();
				if(artifactPointer != null)
				{
					Artifact artifact = artifactPointer.get();

					if(artifact != null)
					{
						BuildArtifactsBeforeRunTaskProvider.setBuildArtifactBeforeRunOption(myDeploySourceList, myProject, artifact, false);
					}
				}

				Disposer.dispose(selectedValue);

				model.removeElement(selectedValue);
			}
		});
		decorator.setToolbarPosition(ActionToolbarPosition.RIGHT);
		JPanel panel = decorator.createPanel();
		panel.setPreferredSize(JBUI.size(200, -1));
		mainPanel.add(panel, BorderLayout.WEST);

		Wrapper settingsPanel = new Wrapper();
		settingsPanel.setBorder(JBUI.Borders.empty(5));

		myDeploySourceList.addListSelectionListener(e ->
		{
			if(e.getValueIsAdjusting())
			{
				return;
			}

			DeployItem selectedValue = myDeploySourceList.getSelectedValue();

			settingsPanel.removeAll();

			if(selectedValue == null)
			{
				return;
			}

			SettingsEditor<DeploymentModel> editor = selectedValue.getEditor();

			JComponent component = editor.getComponent();

			settingsPanel.setContent(component);

			editor.resetFrom(selectedValue.getDeploymentModel());
		});

		mainPanel.add(settingsPanel, BorderLayout.CENTER);

		rootPanel.add(mainPanel, BorderLayout.CENTER);

		return rootPanel;
	}

	private void selectArtifact(DefaultListModel<DeployItem> model)
	{
		Artifact[] artifacts = ArtifactManager.getInstance(myProject).getArtifacts();

		List<Artifact> listArtifacts = new ArrayList<>(artifacts.length);
		loop:
		for(Artifact artifact : artifacts)
		{
			if(artifact.getArtifactType() != ExplodedWarArtifactType.getInstance())
			{
				continue;
			}

			Enumeration<DeployItem> elements = model.elements();
			while(elements.hasMoreElements())
			{
				DeployItem item = elements.nextElement();

				Artifact tempArtifact = item.getArtifactPointer().get();
				if(artifact.equals(tempArtifact))
				{
					continue loop;
				}
			}

			listArtifacts.add(artifact);
		}
		ChooseArtifactsDialog dialog = new ChooseArtifactsDialog(myProject, listArtifacts, "Choose Artifact", null);
		dialog.show();

		if(dialog.isOK())
		{
			for(Artifact artifact : dialog.getChosenElements())
			{
				ArtifactPointer artifactPointer = ArtifactPointerUtil.getPointerManager(myProject).create(artifact);
				ArtifactDeploymentSourceImpl deploymentSource = new ArtifactDeploymentSourceImpl(artifactPointer);

				DeployItem element = new DeployItem(myCommonModel, deploymentSource, myBundleType);
				model.addElement(element);
				myDeploySourceList.setSelectedValue(element, true);

				BuildArtifactsBeforeRunTaskProvider.setBuildArtifactBeforeRunOption(myDeploySourceList, myProject, artifact, true);
			}
		}
	}

	@Override
	protected void disposeEditor()
	{
		super.disposeEditor();

		DefaultListModel<DeployItem> model = (DefaultListModel<DeployItem>) myDeploySourceList.getModel();
		Enumeration<DeployItem> elements = model.elements();
		while(elements.hasMoreElements())
		{
			Disposer.dispose(elements.nextElement());
		}
	}

	@Override
	protected void resetEditorFrom(JavaEEConfigurationImpl configuration)
	{
		DefaultListModel<DeployItem> model = (DefaultListModel<DeployItem>) myDeploySourceList.getModel();
		Enumeration<DeployItem> enumeration = model.elements();
		while(enumeration.hasMoreElements())
		{
			DeployItem item = enumeration.nextElement();
			model.removeElement(item);
			Disposer.dispose(item);
		}

		List<DeploymentModel> deploymentModels = configuration.getDeploymentSettings().getDeploymentModels();
		for(DeploymentModel deploymentModel : deploymentModels)
		{
			DeploymentSource deploymentSource = deploymentModel.getDeploymentSource();

			DeployItem item = new DeployItem(myCommonModel, deploymentSource, myBundleType);
			item.getEditor().resetFrom(deploymentModel);
			try
			{
				item.getEditor().applyTo(item.getDeploymentModel());
			}
			catch(ConfigurationException ignored)
			{
			}

			model.addElement(item);
		}
	}

	@Override
	protected void applyEditorTo(JavaEEConfigurationImpl configuration) throws ConfigurationException
	{
		JavaEEDeploymentSettingsImpl deploymentSettings = (JavaEEDeploymentSettingsImpl) configuration.getDeploymentSettings();
		DefaultListModel<DeployItem> model = (DefaultListModel<DeployItem>) myDeploySourceList.getModel();

		deploymentSettings.removeAll();

		Enumeration<DeployItem> enumeration = model.elements();
		while(enumeration.hasMoreElements())
		{
			DeployItem deployItem = enumeration.nextElement();

			DeploymentSource deploymentSource = deployItem.getDeploymentSource();

			DeploymentModel deploymentModel = myBundleType.createNewDeploymentModel(myCommonModel, deploymentSource);

			deployItem.getEditor().applyTo(deploymentModel);

			deploymentSettings.addModel(deploymentModel);
		}
	}
}
