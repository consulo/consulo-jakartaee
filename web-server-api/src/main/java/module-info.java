/**
 * @author VISTALL
 * @since 22/01/2023
 */
module consulo.jakartaee.web.api
{
	requires transitive consulo.jakartaee.api;
	requires transitive consulo.java.jam.api;
	requires transitive consulo.jsp.language.api;
	requires consulo.java;

	requires com.intellij.xml;
	exports com.intellij.javaee.model.xml.web;
	exports com.intellij.javaee.model.xml.web.converters;
	exports com.intellij.javaee.web;
	exports consulo.jakartaee.web;
	exports consulo.jakartaee.web.module.extension;
}