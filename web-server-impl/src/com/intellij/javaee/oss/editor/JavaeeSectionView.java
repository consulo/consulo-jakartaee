/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package com.intellij.javaee.oss.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.UIUtil;
import com.intellij.util.xml.DomElement;
import com.intellij.util.xml.ui.AbstractTableView;
import com.intellij.util.xml.ui.CommittablePanel;
import com.intellij.util.xml.ui.StripeTableCellRenderer;

public class JavaeeSectionView implements CommittablePanel {

    private final SectionTableView view;

    private final JavaeeSection<?>[] sections;

    private final ColumnInfo<RowElement, ?>[] columns;

    private final List<RowElement> items = new ArrayList<RowElement>();

    private final Set<JavaeeSection<?>> expanded = new HashSet<JavaeeSection<?>>();

    private final Map<JavaeeSection<?>, JavaeeSectionInfo<DomElement>[]> mapped = new HashMap<JavaeeSection<?>, JavaeeSectionInfo<DomElement>[]>();

    private static final Component EXPANDED_ICON = createLabel(UIUtil.getTreeExpandedIcon());

    private static final Component COLLAPSED_ICON = createLabel(UIUtil.getTreeCollapsedIcon());

    private static final JavaeeSectionInfo<DomElement> EMPTY_COLUMN = new JavaeeSectionInfo<DomElement>(null) {
        private final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();

        @Override
        public String valueOf(DomElement item) {
            return "";
        }

        @Override
        public TableCellRenderer getRenderer(DomElement item) {
            return renderer;
        }
    };

    public JavaeeSectionView(Project project, String empty, JavaeeSection<?>... sections) {
        this.sections = sections.clone();
        view = new SectionTableView(project, empty);
        JTable table = view.getTable();
        table.setRowHeight(AllIcons.Nodes.Class.getIconHeight());
        table.setShowVerticalLines(false);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.addMouseListener(createMouseListener(table));
        table.addKeyListener(createKeyListener(table));
        table.getTableHeader().setDefaultRenderer(createHeaderRenderer());
        expanded.addAll(Arrays.asList(sections));
        columns = createColumnInfos();
        view.initialize();
        reset();
    }

    public void dispose() {
    }

    public void commit() {
    }

    public void reset() {
        items.clear();
        for (JavaeeSection<?> section : sections) {
            List<? extends DomElement> values = section.getValues();
            if (!values.isEmpty()) {
                items.add(new SectionRowElement(section));
                if (expanded.contains(section)) {
                    for (DomElement value : values) {
                        items.add(new DomRowElement(section, value));
                    }
                }
            }
        }
        view.reset(columns, items);
    }

    public JComponent getComponent() {
        return view;
    }

    @NotNull
    private JavaeeSectionInfo<DomElement> getColumnInfo(JavaeeSection<?> section, int column) {
        JavaeeSectionInfo<DomElement>[] infos = mapped.get(section);
        return (column <= infos.length) ? infos[column] : EMPTY_COLUMN;
    }

    private MouseAdapter createMouseListener(final JTable table) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                Point point = new Point(event.getPoint());
                int row = table.rowAtPoint(point);
                int col = table.columnAtPoint(point);
                if ((row >= 0) && (col >= 0)) {
                    JComponent component = (JComponent) table.prepareRenderer(table.getCellRenderer(row, col), row, col);
                    Rectangle rect = table.getCellRect(row, col, true);
                    component.setSize(rect.getSize());
                    component.doLayout();
                    point.translate(-rect.x, -rect.y);
                    if ((event.getClickCount() >= 2) || (component.findComponentAt(point) instanceof IconLabel)) {
                        items.get(row).handleDoubleClick();
                    }
                }
            }
        };
    }

    private KeyAdapter createKeyListener(final JTable table) {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                int row = table.getSelectedRow();
                if ((row >= 0) && ((event.getKeyCode() == KeyEvent.VK_LEFT) || (event.getKeyCode() == KeyEvent.VK_RIGHT))) {
                    items.get(row).handleLeftRight(event.getKeyCode() == KeyEvent.VK_RIGHT);
                }
            }
        };
    }

    private DefaultTableCellRenderer createHeaderRenderer() {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focus, int row, int col) {
                super.getTableCellRendererComponent(table, value, selected, focus, row, col);
                setBorder(null);
                return this;
            }
        };
    }

    @SuppressWarnings({"unchecked"})
    private ColumnInfo<RowElement, ?>[] createColumnInfos() {
        mapped.clear();
        int num = 0;
        for (JavaeeSection<?> section : sections) {
            JavaeeSectionInfo<DomElement>[] infos = (JavaeeSectionInfo<DomElement>[]) section.createColumnInfos();
            mapped.put(section, infos);
            num = Math.max(num, infos.length);
        }
        ColumnInfo<RowElement, ?>[] infos = new ColumnInfo[num + 1];
        infos[0] = createColumn();
        for (int i = 0; i < num; i++) {
            infos[i + 1] = createColumn(i);
        }
        return infos;
    }

    private ColumnInfo<RowElement, Object> createColumn() {
        return new ColumnInfo<RowElement, Object>(null) {
            @Override
            @Nullable
            public Object valueOf(RowElement item) {
                return item;
            }

            @Override
            public TableCellRenderer getRenderer(final RowElement item) {
                return new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focus, int row, int col) {
                        return item.getFirstComponent(table, selected, focus);
                    }
                };
            }
        };
    }

    private ColumnInfo<RowElement, String> createColumn(final int index) {
        return new ColumnInfo<RowElement, String>(null) {
            @Override
            @Nullable
            public String valueOf(RowElement item) {
                return item.getValue(index);
            }

            @Override
            public void setValue(RowElement item, String value) {
                item.setValue(index, value);
            }

            @Override
            public boolean isCellEditable(RowElement item) {
                return item.isEditable(index);
            }

            @Override
            @Nullable
            public TableCellRenderer getRenderer(RowElement item) {
                return item.getRenderer(index);
            }

            @Override
            @Nullable
            public TableCellEditor getEditor(RowElement item) {
                return item.getEditor(index);
            }
        };
    }

    private static Component createLabel(Icon icon) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new IconLabel(icon));
        return panel;
    }

    private class SectionTableView extends AbstractTableView<RowElement> {

        SectionTableView(Project project, String empty) {
            super(project, empty, null);
        }

        @Override
        protected void adjustColumnWidths() {
            super.adjustColumnWidths();
            TableColumnModel model = getTable().getColumnModel();
            if (model.getColumnCount() > 0) {
                TableColumn column = model.getColumn(0);
                column.setMinWidth(column.getPreferredWidth());
                column.setMaxWidth(column.getPreferredWidth());
            }
        }

        @Override
        protected void wrapValueSetting(@NotNull RowElement item, Runnable setter) {
            Object value = item.getElement();
            if ((value instanceof DomElement) && ((DomElement) value).isValid()) {
                setter.run();
                fireChanged();
            }
        }

        @Override
        protected TableCellRenderer getTableCellRenderer(int row, int col, final TableCellRenderer renderer, Object value) {
            return new TableCellRenderer() {
                public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focus, int row, int col) {
                    Component component = renderer.getTableCellRendererComponent(table, value, selected, focus, row, col);
                    return items.get(row).decorate((JComponent) component, table, col, selected);
                }
            };
        }

        void initialize() {
            initializeTable();
        }
    }

    private interface RowElement {

        @NotNull
        JavaeeSection<?> getSection();

        @Nullable
        Object getElement();

        void handleDoubleClick();

        void handleLeftRight(boolean right);

        Component getFirstComponent(JTable table, boolean selected, boolean focus);

        @Nullable
        String getValue(int column);

        void setValue(int column, String value);

        boolean isEditable(int column);

        @Nullable
        TableCellRenderer getRenderer(int column);

        @Nullable
        TableCellEditor getEditor(int column);

        @NotNull
        JComponent decorate(JComponent component, JTable table, int column, boolean selected);
    }

    private class SectionRowElement implements RowElement {

        private final JavaeeSection<?> section;

        private SectionRowElement(JavaeeSection<?> section) {
            this.section = section;
        }

        @NotNull
        public JavaeeSection<?> getSection() {
            return section;
        }

        @Nullable
        public Object getElement() {
            return null;
        }

        public void handleDoubleClick() {
            if (expanded.remove(section) || expanded.add(section)) {
                reset();
            }
        }

        public void handleLeftRight(boolean right) {
            if (right && expanded.add(section)) {
                reset();
            } else if (!right && expanded.remove(section)) {
                reset();
            }
        }

        public Component getFirstComponent(JTable table, boolean selected, boolean focus) {
            return expanded.contains(section) ? EXPANDED_ICON : COLLAPSED_ICON;
        }

        @Nullable
        public String getValue(int column) {
            return ((column == 0) || expanded.contains(section)) ? getColumnInfo(section, column).getName() : null;
        }

        public void setValue(int column, String value) {
        }

        public boolean isEditable(int column) {
            return false;
        }

        @Nullable
        public TableCellRenderer getRenderer(int column) {
            return null;
        }

        @Nullable
        public TableCellEditor getEditor(int column) {
            return null;
        }

        @NotNull
        public JComponent decorate(JComponent component, JTable table, int column, boolean selected) {
            Border border = BorderFactory.createEmptyBorder(0, 5, 0, ((column == 0) || (column == 2)) ? 5 : 0);
            Color background = selected ? StripeTableCellRenderer.darken(table.getSelectionBackground()) : new Color(table.getTableHeader().getBackground().getRGB());
            component.setBackground(background);
            border = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, background.darker()), border);
            if (expanded.contains(section)) {
                if (column > 1) {
                    border = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, background.brighter()), border);
                }
                if (column > 0) {
                    border = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, table.getGridColor()), border);
                }
            }
            component.setFont(component.getFont().deriveFont(Font.BOLD));
            component.setBorder(border);
            return component;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof SectionRowElement) {
                SectionRowElement other = (SectionRowElement) obj;
                return section.equals(other.getSection());
            }
            return false;
        }

        @Override
        public int hashCode() {
            return section.hashCode();
        }
    }

    private class DomRowElement implements RowElement {

        private final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();

        private final JavaeeSection<?> section;

        private final DomElement element;

        private DomRowElement(JavaeeSection<?> section, DomElement element) {
            this.section = section;
            this.element = element;
        }

        @NotNull
        public JavaeeSection<?> getSection() {
            return section;
        }

        @Nullable
        public Object getElement() {
            return element;
        }

        public void handleDoubleClick() {
        }

        public void handleLeftRight(boolean right) {
        }

        public Component getFirstComponent(JTable table, boolean selected, boolean focus) {
            return renderer.getTableCellRendererComponent(table, null, selected, focus, 0, 0);
        }

        @Nullable
        public String getValue(int column) {
            return getColumnInfo(section, column).valueOf(element);
        }

        public void setValue(int column, String value) {
            getColumnInfo(section, column).setValue(element, value);
        }

        public boolean isEditable(int column) {
            return getColumnInfo(section, column).isCellEditable(element);
        }

        @Nullable
        public TableCellRenderer getRenderer(int column) {
            return getColumnInfo(section, column).getRenderer(element);
        }

        @Nullable
        public TableCellEditor getEditor(int column) {
            return getColumnInfo(section, column).getEditor(element);
        }

        @NotNull
        public JComponent decorate(JComponent component, JTable table, int column, boolean selected) {
            Border border = BorderFactory.createEmptyBorder(0, 5, 0, ((column == 0) || (column == 2)) ? 5 : 0);
            if (column > 0) {
                border = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, table.getGridColor()), border);
            }
            component.setBorder(border);
            return component;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof DomRowElement) {
                DomRowElement other = (DomRowElement) obj;
                return section.equals(other.getSection()) && element.equals(other.getElement());
            }
            return false;
        }

        @Override
        public int hashCode() {
            return (31 * section.hashCode()) + element.hashCode();
        }
    }

    private static class IconLabel extends JLabel {

        private IconLabel(Icon icon) {
            super(icon);
        }
    }
}
