/**
 * @author VISTALL
 * @since 22/01/2023
 */
module consulo.jakartaee.web.impl
{
	requires java.management;

	requires consulo.jakartaee.web.api;

	requires consulo.java.execution.impl;

	requires consulo.java;
	requires com.intellij.xml;

	// TODO remove in future
	requires java.desktop;
	requires consulo.ide.impl;

	exports consulo.jakartaee.webServer.impl;
	exports consulo.jakartaee.webServer.impl.appServerIntegrations;
	exports consulo.jakartaee.webServer.impl.context;
	exports consulo.jakartaee.webServer.impl.dataSource;
	exports consulo.jakartaee.webServer.impl.deployment;
	exports consulo.jakartaee.webServer.impl.facet;
	exports consulo.jakartaee.webServer.impl.model.xml.web.actions;
	exports consulo.jakartaee.webServer.impl.oss;
	exports consulo.jakartaee.webServer.impl.oss.admin;
	exports consulo.jakartaee.webServer.impl.oss.admin.jmx;
	exports consulo.jakartaee.webServer.impl.oss.agent;
	exports consulo.jakartaee.webServer.impl.oss.converter;
	exports consulo.jakartaee.webServer.impl.oss.descriptor;
	exports consulo.jakartaee.webServer.impl.oss.editor;
	exports consulo.jakartaee.webServer.impl.oss.server;
	exports consulo.jakartaee.webServer.impl.oss.transport;
	exports consulo.jakartaee.webServer.impl.oss.util;
	exports consulo.jakartaee.webServer.impl.run.configuration;
	exports consulo.jakartaee.webServer.impl.run.execution;
	exports consulo.jakartaee.webServer.impl.run.localRun;
	exports consulo.jakartaee.webServer.impl.serverInstances;
	exports consulo.jakartaee.webServer.impl.ui.packaging;
	exports consulo.jakartaee.webServer.impl.web;
	exports consulo.jakartaee.webServer.impl.web.artifact;
	exports consulo.javaee.artifact;
	exports consulo.javaee.bundle;
	exports consulo.javaee.deployment.impl;
	exports consulo.javaee.dom.web;
	exports consulo.javaee.module;
	exports consulo.javaee.run.configuration;
	exports consulo.javaee.run.configuration.editor;
	exports consulo.javaee.run.configuration.state;
	exports consulo.jakartaee.webServer.impl.localize;
	exports consulo.javaee.run.configuration.state.view;
}