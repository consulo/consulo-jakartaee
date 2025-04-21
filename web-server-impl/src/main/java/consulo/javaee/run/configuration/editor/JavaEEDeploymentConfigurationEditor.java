package consulo.javaee.run.configuration.editor;

import consulo.compiler.artifact.Artifact;
import consulo.compiler.artifact.ArtifactManager;
import consulo.compiler.artifact.ArtifactPointer;
import consulo.compiler.artifact.execution.BuildArtifactsBeforeRunTaskHelper;
import consulo.compiler.artifact.ui.awt.ChooseArtifactsDialog;
import consulo.configurable.ConfigurationException;
import consulo.dataContext.DataContext;
import consulo.dataContext.DataManager;
import consulo.disposer.Disposer;
import consulo.execution.configuration.ui.SettingsEditor;
import consulo.jakartaee.webServer.impl.deployment.DeploymentModel;
import consulo.jakartaee.webServer.impl.run.configuration.CommonModel;
import consulo.javaee.artifact.ExplodedWarArtifactType;
import consulo.javaee.bundle.JavaEEServerBundleType;
import consulo.javaee.deployment.impl.JavaEEDeploymentSettingsImpl;
import consulo.javaee.run.configuration.JavaEEConfigurationImpl;
import consulo.localize.LocalizeValue;
import consulo.platform.base.icon.PlatformIconGroup;
import consulo.project.Project;
import consulo.remoteServer.configuration.deployment.ArtifactDeploymentSource;
import consulo.remoteServer.configuration.deployment.DeploymentSource;
import consulo.remoteServer.configuration.deployment.DeploymentSourceFactory;
import consulo.ui.annotation.RequiredUIAccess;
import consulo.ui.ex.SimpleTextAttributes;
import consulo.ui.ex.action.ActionToolbarPosition;
import consulo.ui.ex.awt.*;
import consulo.ui.ex.popup.*;
import consulo.ui.image.Image;

import jakarta.annotation.Nonnull;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author VISTALL
 * @since 2017-07-09
 */
public class JavaEEDeploymentConfigurationEditor extends SettingsEditor<JavaEEConfigurationImpl> {
    public static String ARTIFACT = "Artifact...";

    private final Project myProject;
    private final JavaEEServerBundleType myBundleType;
    private final CommonModel myCommonModel;

    private JBList<DeployItem> myDeploySourceList = new JBList<>(new DefaultListModel<>());

    private final BuildArtifactsBeforeRunTaskHelper myBuildArtifactsBeforeRunTaskHelper;

    public JavaEEDeploymentConfigurationEditor(Project project, JavaEEServerBundleType bundleType, CommonModel commonModel) {
        myProject = project;
        myBundleType = bundleType;
        myCommonModel = commonModel;
        myBuildArtifactsBeforeRunTaskHelper = project.getInstance(BuildArtifactsBeforeRunTaskHelper.class);
    }

    @Nonnull
    @Override
    protected JComponent createEditor() {
        DefaultListModel<DeployItem> model = (DefaultListModel<DeployItem>)myDeploySourceList.getModel();

        myDeploySourceList.setCellRenderer(new ColoredListCellRenderer<>() {
            @Override
            protected void customizeCellRenderer(
                @Nonnull JList<? extends DeployItem> list,
                DeployItem value,
                int index,
                boolean selected,
                boolean hasFocus
            ) {
                DeploymentSource deploymentSource = value.getDeploymentSource();
                if (deploymentSource instanceof ArtifactDeploymentSource artifactDeploymentSource) {
                    ArtifactPointer artifactPointer = artifactDeploymentSource.getArtifactPointer();
                    Artifact artifact = artifactPointer.get();
                    if (artifact != null) {
                        append(artifact.getName());
                        setIcon(artifact.getArtifactType().getIcon());
                    }
                    else {
                        append(artifactPointer.getName(), SimpleTextAttributes.ERROR_ATTRIBUTES);
                        setIcon(PlatformIconGroup.toolbarUnknown());
                    }
                }
                else {
                    throw new UnsupportedOperationException(deploymentSource.getClass().getName());
                }
            }
        });

        JPanel rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBorder(IdeBorderFactory.createTitledBorder("Deploy at server startup", false));

        JPanel mainPanel = new JPanel(new BorderLayout());

        ToolbarDecorator decorator = ToolbarDecorator.createDecorator(myDeploySourceList);
        decorator.setAddAction(anActionButton -> {
            ListPopupStep<String> step = new BaseListPopupStep<String>("Select source", ARTIFACT) {
                @Override
                public PopupStep onChosen(String selectedValue, boolean finalChoice) {
                    if (ARTIFACT.equals(selectedValue)) {
                        return doFinalStep(() -> selectArtifact(model));
                    }
                    return super.onChosen(selectedValue, finalChoice);
                }

                @Override
                public PopupStep doFinalStep(Runnable runnable) {
                    return super.doFinalStep(runnable);
                }

                @Override
                public Image getIconFor(String value) {
                    if (ARTIFACT.equals(value)) {
                        return PlatformIconGroup.nodesArtifact();
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
            if (selectedValue != null) {
                ArtifactPointer artifactPointer = selectedValue.getArtifactPointer();
                if (artifactPointer != null) {
                    Artifact artifact = artifactPointer.get();

                    if (artifact != null) {
                        DataContext context = DataManager.getInstance().getDataContext(myDeploySourceList);

                        myBuildArtifactsBeforeRunTaskHelper.setBuildArtifactBeforeRunOption(context, artifact, false);
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
            if (e.getValueIsAdjusting()) {
                return;
            }

            DeployItem selectedValue = myDeploySourceList.getSelectedValue();

            settingsPanel.removeAll();

            if (selectedValue == null) {
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

    @RequiredUIAccess
    private void selectArtifact(DefaultListModel<DeployItem> model) {
        Artifact[] artifacts = ArtifactManager.getInstance(myProject).getArtifacts();

        List<Artifact> listArtifacts = new ArrayList<>(artifacts.length);
        loop:
        for (Artifact artifact : artifacts) {
            if (artifact.getArtifactType() != ExplodedWarArtifactType.getInstance()) {
                continue;
            }

            Enumeration<DeployItem> elements = model.elements();
            while (elements.hasMoreElements()) {
                DeployItem item = elements.nextElement();

                Artifact tempArtifact = item.getArtifactPointer().get();
                if (artifact.equals(tempArtifact)) {
                    continue loop;
                }
            }

            listArtifacts.add(artifact);
        }
        ChooseArtifactsDialog dialog =
            new ChooseArtifactsDialog(myProject, listArtifacts, LocalizeValue.localizeTODO("Choose Artifact"), LocalizeValue.empty());
        dialog.show();

        if (dialog.isOK()) {
            DeploymentSourceFactory factory = myProject.getInstance(DeploymentSourceFactory.class);

            DataContext context = DataManager.getInstance().getDataContext(myDeploySourceList);
            for (Artifact artifact : dialog.getChosenElements()) {
                ArtifactDeploymentSource deploymentSource = factory.createArtifactDeploymentSource(artifact);

                DeployItem element = new DeployItem(myCommonModel, deploymentSource, myBundleType);
                model.addElement(element);
                myDeploySourceList.setSelectedValue(element, true);

                myBuildArtifactsBeforeRunTaskHelper.setBuildArtifactBeforeRunOption(context, artifact, true);
            }
        }
    }

    @Override
    protected void disposeEditor() {
        super.disposeEditor();

        DefaultListModel<DeployItem> model = (DefaultListModel<DeployItem>)myDeploySourceList.getModel();
        Enumeration<DeployItem> elements = model.elements();
        while (elements.hasMoreElements()) {
            Disposer.dispose(elements.nextElement());
        }
    }

    @Override
    protected void resetEditorFrom(JavaEEConfigurationImpl configuration) {
        DefaultListModel<DeployItem> model = (DefaultListModel<DeployItem>)myDeploySourceList.getModel();
        Enumeration<DeployItem> enumeration = model.elements();
        while (enumeration.hasMoreElements()) {
            DeployItem item = enumeration.nextElement();
            model.removeElement(item);
            Disposer.dispose(item);
        }

        List<DeploymentModel> deploymentModels = configuration.getDeploymentSettings().getDeploymentModels();
        for (DeploymentModel deploymentModel : deploymentModels) {
            DeploymentSource deploymentSource = deploymentModel.getDeploymentSource();

            DeployItem item = new DeployItem(myCommonModel, deploymentSource, myBundleType);
            item.getEditor().resetFrom(deploymentModel);
            try {
                item.getEditor().applyTo(item.getDeploymentModel());
            }
            catch (ConfigurationException ignored) {
            }

            model.addElement(item);
        }
    }

    @Override
    protected void applyEditorTo(JavaEEConfigurationImpl configuration) throws ConfigurationException {
        JavaEEDeploymentSettingsImpl deploymentSettings = (JavaEEDeploymentSettingsImpl)configuration.getDeploymentSettings();
        DefaultListModel<DeployItem> model = (DefaultListModel<DeployItem>)myDeploySourceList.getModel();

        deploymentSettings.removeAll();

        Enumeration<DeployItem> enumeration = model.elements();
        while (enumeration.hasMoreElements()) {
            DeployItem deployItem = enumeration.nextElement();

            DeploymentSource deploymentSource = deployItem.getDeploymentSource();

            DeploymentModel deploymentModel = myBundleType.createNewDeploymentModel(myCommonModel, deploymentSource);

            deployItem.getEditor().applyTo(deploymentModel);

            deploymentSettings.addModel(deploymentModel);
        }
    }
}
