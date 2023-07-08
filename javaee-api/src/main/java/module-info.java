/**
 * @author VISTALL
 * @since 22/01/2023
 */
module consulo.jakartaee.api
{
	requires transitive consulo.ide.api;

	requires com.intellij.xml;
	requires consulo.java;

	exports com.intellij.javaee;
	exports com.intellij.javaee.artifact;
	exports com.intellij.javaee.ejb;
	exports com.intellij.javaee.ejb.role;
	exports com.intellij.javaee.facet;
	exports com.intellij.javaee.model;
	exports com.intellij.javaee.model.annotations;
	exports com.intellij.javaee.model.common;
	exports com.intellij.javaee.model.common.ejb;
	exports com.intellij.javaee.model.common.managedbean;
	exports com.intellij.javaee.model.enums;
	exports com.intellij.javaee.model.xml;
	exports com.intellij.javaee.model.xml.application;
	exports com.intellij.javaee.model.xml.client;
	exports com.intellij.javaee.model.xml.compatibility;
	exports com.intellij.javaee.model.xml.converters;
	exports com.intellij.javaee.model.xml.ejb;
	exports consulo.javaee;
	exports consulo.javaee.icon;
	exports consulo.jakarta.localize;
	exports consulo.javaee.module.extension;
}