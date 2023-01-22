/**
 * @author VISTALL
 * @since 22/01/2023
 */
module consulo.jsp.impl
{
	requires consulo.ide.api;
	requires consulo.jsp.language.api;
	requires consulo.java.language.impl;
	requires consulo.java;
	requires consulo.jakartaee.api;
	requires consulo.jakartaee.web.api;

	exports consulo.jsp.impl.action;
	exports consulo.jsp.impl.editor;
	exports consulo.jsp.impl.editor.codeInsight.daemon;
	exports consulo.jsp.impl.editor.codeInsight.daemon.analysis;
	exports consulo.jsp.impl.editor.codeInspection;
	exports consulo.jsp.impl.editor.formatting;
	exports consulo.jsp.impl.editor.formatting.copy;
	exports consulo.jsp.impl.editor.highlight;
	exports consulo.jsp.impl.language;
	exports consulo.jsp.impl.language.file;
	exports consulo.jsp.impl.language.lexer;
	exports consulo.jsp.impl.language.parser;
	exports consulo.jsp.impl.language.psi;
	exports consulo.jsp.impl.language.psi.descriptor;
	exports consulo.jsp.impl.language.psi.java;
	exports consulo.jsp.impl.language.psi.java.parsing;
	exports consulo.jsp.impl.language.psi.java.psi;
	exports consulo.jsp.impl.language.psi.java.psi.stub;
	exports consulo.jsp.impl.language.psi.java.psi.stub.elementType;
	exports consulo.jsp.impl.language.psi.reference;
	exports consulo.jsp.impl.language.psi.stub;

	opens consulo.jsp.impl.language.psi.java.psi to consulo.application.impl;
}