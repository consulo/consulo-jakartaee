package consulo.javaee.jsp.lexer;

import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import consulo.javaee.jsp.psi.JspTokens;
import com.intellij.psi.jsp.JspTokenType;
%%

%public
%class _JspHighlightLexer
%extends LexerBase
%public
%unicode
%public

%function advanceImpl
%type IElementType

%eof{ return;
%eof}

WHITESPACE=[ \n\r\t]+

%state COMMENT
%state DIRECTIVE
%state SCRIPTLET
%state EXPRESSION
%state DECLARATION

%%

<YYINITIAL>
{
   "<%--"  { yybegin(COMMENT);  return JspTokenType.JSP_COMMENT; }

   "<%"    { yybegin(SCRIPTLET);  return JspTokenType.JSP_SCRIPTLET_START; }

   "<%!"   { yybegin(DECLARATION);  return JspTokenType.JSP_DECLARATION_START; }

   "<%@"   { yybegin(DIRECTIVE); return JspTokenType.JSP_DIRECTIVE_START; }

   "<%="   { yybegin(EXPRESSION); return JspTokenType.JSP_EXPRESSION_START; }


   {WHITESPACE} { return JspTokens.HTML_FRAGMENT; }

   . { return JspTokens.HTML_FRAGMENT; }
}

<DIRECTIVE>
{
	"%>"              { yybegin(YYINITIAL); return JspTokenType.JSP_DIRECTIVE_END; }

	[^]                { return JspTokens.JSP_FRAGMENT; }
}

<DECLARATION>
{
	"%>"              { yybegin(YYINITIAL); return JspTokenType.JSP_DECLARATION_END; }

	[^]                { return JspTokens.JAVA_FRAGMENT; }
}

<SCRIPTLET>
{
	"%>"              { yybegin(YYINITIAL); return JspTokenType.JSP_SCRIPTLET_END; }

	[^]                { return JspTokens.JAVA_FRAGMENT; }
}

<EXPRESSION>
{
	"%>"              { yybegin(YYINITIAL); return JspTokenType.JSP_EXPRESSION_END; }

	[^]                { return JspTokens.JAVA_FRAGMENT; }
}

<COMMENT>
{
	"--%>"              { yybegin(YYINITIAL); return JspTokenType.JSP_COMMENT; }

	[^]                { return JspTokenType.JSP_COMMENT; }
}