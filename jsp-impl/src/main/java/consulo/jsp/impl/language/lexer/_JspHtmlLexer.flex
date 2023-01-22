package consulo.jsp.impl.language.lexer;

import consulo.jsp.impl.language.psi.JspTokens;
import consulo.language.ast.IElementType;
import consulo.language.lexer.LexerBase;
%%

%public
%class _JspHtmlLexer
%extends LexerBase
%public
%unicode
%public

%function advanceImpl
%type IElementType

WHITESPACE=[ \n\r\t]+

%state JSP_FRAGMENT

%%

<YYINITIAL>
{
   "<%--"  { yybegin(JSP_FRAGMENT);  return JspTokens.JSP_FRAGMENT; }

   "<%"    { yybegin(JSP_FRAGMENT);  return JspTokens.JSP_FRAGMENT; }

   "<%@"   { yybegin(JSP_FRAGMENT); return JspTokens.JSP_FRAGMENT; }

   "<%="   { yybegin(JSP_FRAGMENT); return JspTokens.JSP_FRAGMENT; }

   "<${"   { yybegin(JSP_FRAGMENT); return JspTokens.JSP_FRAGMENT; }

   {WHITESPACE} { return JspTokens.HTML_FRAGMENT; }

   . { return JspTokens.HTML_FRAGMENT; }
}

<JSP_FRAGMENT>
{
	"%>"               { yybegin(YYINITIAL); return JspTokens.JSP_FRAGMENT; }

	"--%>"             { yybegin(YYINITIAL); return JspTokens.JSP_FRAGMENT; }

	"@%>"              { yybegin(YYINITIAL); return JspTokens.JSP_FRAGMENT; }

	"=%>"              { yybegin(YYINITIAL); return JspTokens.JSP_FRAGMENT; }

	"}$>"              { yybegin(YYINITIAL); return JspTokens.JSP_FRAGMENT; }

	[^]                { return JspTokens.JSP_FRAGMENT; }
}