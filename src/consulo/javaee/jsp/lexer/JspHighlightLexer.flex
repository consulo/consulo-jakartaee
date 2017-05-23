package consulo.javaee.jsp.lexer;

import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import consulo.javaee.jsp.psi.JspTokens;
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

%state JSP_FRAGMENT

%state COMMENT
%state JAVA_FRAGMENT

%%

<YYINITIAL>
{
   "<%--"  { yybegin(COMMENT);  return JspTokens.COMMENT; }

   "<%"    { yybegin(JAVA_FRAGMENT);  return JspTokens.JSP_FRAGMENT; }

   "<%@"   { yybegin(JSP_FRAGMENT); return JspTokens.JAVA_FRAGMENT; }

   "<%="   { yybegin(JAVA_FRAGMENT); return JspTokens.JAVA_FRAGMENT; }


   {WHITESPACE} { return JspTokens.HTML_FRAGMENT; }

   . { return JspTokens.HTML_FRAGMENT; }
}

<JSP_FRAGMENT>
{
	"%>"              { yybegin(YYINITIAL); return JspTokens.JAVA_FRAGMENT; }

	[^]                { return JspTokens.JSP_FRAGMENT; }
}

<JAVA_FRAGMENT>
{
	"%>"              { yybegin(YYINITIAL); return JspTokens.JAVA_FRAGMENT; }

	[^]                { return JspTokens.JAVA_FRAGMENT; }
}

<COMMENT>
{
	"--%>"              { yybegin(YYINITIAL); return JspTokens.COMMENT; }

	[^]                { return JspTokens.COMMENT; }
}