/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.editor;

import consulo.ui.ex.awt.Splitter;
import consulo.ui.ex.awt.UIUtil;
import consulo.util.collection.ArrayUtil;
import consulo.xml.util.xml.ui.CommittablePanel;
import consulo.xml.util.xml.ui.CompositeCommittable;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public abstract class JavaeeBaseEditor extends CompositeCommittable implements CommittablePanel
{
	@NonNls
	private static final String ROOT = "root";

	protected final void markAsRoot(JComponent component)
	{
		component.putClientProperty(ROOT, ROOT);
	}

	protected final void enableChildren(JComponent container, boolean enabled)
	{
		UIUtil.setEnabled(container, enabled, true);
		final Set<JComponent> excluded = new HashSet<JComponent>();
		iterateChildren(container, component -> {
			if((component instanceof JComponent) && (((JComponent) component).getClientProperty(ROOT) != null))
			{
				excluded.add((JComponent) component);
			}
		});
		excluded.remove(container);
		enableChildren(container, enabled, excluded.toArray(new JComponent[excluded.size()]));
	}

	public static void enableChildren(Component container, final boolean enabled, JComponent... excludeComponents)
	{
		iterateChildren(container, t -> enableComponent(t, enabled), excludeComponents);
	}

	private static void enableComponent(Component component, boolean enabled)
	{
		if(component.isEnabled() == enabled)
			return;
		component.setEnabled(enabled);
	}

	public static void iterateChildren(Component container, Consumer<Component> consumer, JComponent... excludeComponents)
	{
		if(excludeComponents != null && ArrayUtil.find(excludeComponents, container) != -1)
			return;
		consumer.accept(container);
		if(container instanceof Container)
		{
			final Component[] components = ((Container) container).getComponents();
			for(Component child : components)
			{
				iterateChildren(child, consumer, excludeComponents);
			}
		}
	}

	protected final void addContent(JPanel panel, Component content)
	{
		panel.setLayout(new BorderLayout());
		panel.add(content, BorderLayout.CENTER);
	}

	protected final void addContent(JPanel panel, CommittablePanel content)
	{
		panel.setLayout(new BorderLayout());
		panel.add(content.getComponent(), BorderLayout.CENTER);
		addComponent(content);
	}

	protected final void addContent(JTabbedPane pane, CommittablePanel content, String title)
	{
		pane.add(title, content.getComponent());
		addComponent(content);
	}

	protected final void addContent(JTabbedPane pane, Component content, String title)
	{
		pane.add(title, content);
	}

	protected final Splitter createSplitter(CommittablePanel first, CommittablePanel second, boolean vertical)
	{
		Splitter splitter = createSplitter(vertical);
		setFirstPanel(splitter, first);
		setSecondPanel(splitter, second);
		return splitter;
	}

	protected final Splitter createSplitter(CommittablePanel first, JComponent second, boolean vertical)
	{
		Splitter splitter = createSplitter(vertical);
		setFirstPanel(splitter, first);
		splitter.setSecondComponent(second);
		return splitter;
	}

	protected final Splitter createSplitter(JComponent first, CommittablePanel second, boolean vertical)
	{
		Splitter splitter = createSplitter(vertical);
		splitter.setFirstComponent(first);
		setSecondPanel(splitter, second);
		return splitter;
	}

	protected final Splitter createSplitter(JComponent first, JComponent second, boolean vertical)
	{
		Splitter splitter = createSplitter(vertical);
		splitter.setFirstComponent(first);
		splitter.setSecondComponent(second);
		return splitter;
	}

	private Splitter createSplitter(boolean vertical)
	{
		Splitter splitter = new Splitter(vertical);
		splitter.setShowDividerControls(true);
		return splitter;
	}

	private void setFirstPanel(Splitter splitter, CommittablePanel panel)
	{
		splitter.setFirstComponent(panel.getComponent());
		addComponent(panel);
	}

	private void setSecondPanel(Splitter splitter, CommittablePanel panel)
	{
		splitter.setSecondComponent(panel.getComponent());
		addComponent(panel);
	}
}
