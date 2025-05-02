package consulo.javaee.run.configuration.editor;

import consulo.application.Application;
import consulo.configurable.ConfigurationException;
import consulo.execution.configuration.ui.SettingsEditor;
import consulo.execution.executor.Executor;
import consulo.jakarta.localize.JakartaLocalize;
import consulo.javaee.run.configuration.JavaEEConfigurationImpl;
import consulo.ui.ex.awt.*;
import consulo.ui.ex.awt.table.ListTableModel;
import consulo.ui.ex.awt.table.TableView;
import consulo.util.lang.Couple;
import jakarta.annotation.Nonnull;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author VISTALL
 * @since 2017-07-11
 */
public class JavaEEStartupConfigurationEditor extends SettingsEditor<JavaEEConfigurationImpl> {
    @Nonnull
    @Override
    protected JComponent createEditor() {
        JPanel panel = new JPanel(new VerticalFlowLayout());
        JBList<Executor> executorList = new JBList<>(new CollectionListModel<>(Application.get().getExtensionList(Executor.class)));
        executorList.setCellRenderer(new ColoredListCellRenderer<>() {
            @Override
            protected void customizeCellRenderer(
                @Nonnull JList<? extends Executor> list,
                Executor value,
                int index,
                boolean selected,
                boolean hasFocus
            ) {
                setIcon(value.getIcon());
                append(value.getActionName().get());
            }
        });
        JScrollPane scrollPane = ScrollPaneFactory.createScrollPane(executorList);
        scrollPane.setPreferredSize(JBUI.size(-1, 90));
        panel.add(scrollPane);

        JBTextField startupScript = new JBTextField();
        JBTextField shutdownScript = new JBTextField();

        JBCheckBox startScriptDefault = new JBCheckBox(JakartaLocalize.checkboxEditScriptPropertiesUseDefault().get());
        JBCheckBox shutdownScriptDefault = new JBCheckBox(JakartaLocalize.checkboxEditScriptPropertiesUseDefault().get());

        JPanel scriptPanel = new JPanel(new GridLayout(2, 3));
        panel.add(scriptPanel);
        scriptPanel.add(new JBLabel(JakartaLocalize.labelRunConfigurationEditorStartupScript().get()));
        scriptPanel.add(startupScript);
        scriptPanel.add(startScriptDefault);
        scriptPanel.add(new JBLabel(JakartaLocalize.labelRunConfigurationEditorShutdownScript().get()));
        scriptPanel.add(shutdownScript);
        scriptPanel.add(shutdownScriptDefault);

        JPanel envPanel = new JPanel(new BorderLayout());
        envPanel.setBorder(IdeBorderFactory.createTitledBorder(
            JakartaLocalize.borderRunConfigurationEditorEnvironmentVariables().get(),
            false
        ));
        JBCheckBox passEnvVariables = new JBCheckBox(JakartaLocalize.checkboxRunConfigurationEditorPassEnvironmentVariables().get());
        envPanel.add(passEnvVariables, BorderLayout.NORTH);

        java.util.List<Couple<String>> env = new ArrayList<>();
        ColumnInfo[] columnInfos = {
            new ColumnInfo.StringColumn("Name"),
            new ColumnInfo.StringColumn("Value")
        };
        TableView<Couple<String>> tableView = new TableView<>(new ListTableModel<>(columnInfos, env));
        ToolbarDecorator decorator = ToolbarDecorator.createDecorator(tableView);
        decorator.disableUpDownActions();

        JPanel comp = decorator.createPanel();
        comp.setPreferredSize(JBUI.size(-1, 100));
        envPanel.add(comp, BorderLayout.CENTER);

        panel.add(envPanel);
        return ScrollPaneFactory.createScrollPane(panel);
    }

    @Override
    protected void resetEditorFrom(JavaEEConfigurationImpl configuration) {
    }

    @Override
    protected void applyEditorTo(JavaEEConfigurationImpl configuration) throws ConfigurationException {
    }
}
