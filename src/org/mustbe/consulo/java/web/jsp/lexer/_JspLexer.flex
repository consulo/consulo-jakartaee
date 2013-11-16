package org.mustbe.consulo.java.web.jsp.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.mustbe.consulo.java.web.jsp.psi.JspTokens;
%%

%class _JspLexer
%implements FlexLexer
%public
%unicode
%public

%function advance
%type IElementType

%eof{ return;
%eof}

COMMENT="<%--"[^]*"--%>"
WHITESPACE=[ \n\r\t]+

IDENTIFIER=[:jletter:] [:jletterdigit:]*

//CHARACTER_LITERAL="'"([^\\\'\r\n]|{ESCAPE_SEQUENCE})*("'"|\\)?
STRING_LITERAL=\"([^\\\"\r\n]|{ESCAPE_SEQUENCE})*(\"|\\)?
ESCAPE_SEQUENCE=\\[^\r\n]

%state DIRECTIVE
%state FRAGMENT
%state LINE_FRAGMENT
%state EXPRESSION

%%

<YYINITIAL>
{
   {COMMENT} { return JspTokens.COMMENT;}

   "<%"  { yybegin(FRAGMENT);  return JspTokens.FRAGMENT_OPEN; }

   "<%@" { yybegin(DIRECTIVE); return JspTokens.DIRECTIVE_OPEN; }

   "<%=" { yybegin(LINE_FRAGMENT); return JspTokens.LINE_FRAGMENT_OPEN; }

   "<${" { yybegin(EXPRESSION); return JspTokens.EXPRESSION_OPEN; }

   {WHITESPACE} { return JspTokens.HTML_TEXT; }

   . { return JspTokens.HTML_TEXT; }
}

<DIRECTIVE>
{
	{STRING_LITERAL}   {return JspTokens.STRING_LITERAL; }

	{IDENTIFIER}       {return JspTokens.IDENTIFIER; }

	{WHITESPACE}       {return JspTokens.WHITE_SPACE; }

	"="                { return JspTokens.EQ; }

	"%>"               { yybegin(YYINITIAL); return JspTokens.DIRECTIVE_CLOSE; }

	.                  { yybegin(YYINITIAL); return JspTokens.BAD_CHARACTER; }
}

<FRAGMENT>
{
   "%>" { yybegin(YYINITIAL); return JspTokens.FRAGMENT_CLOSE; }

   {WHITESPACE} { return JspTokens.JAVA_TEXT; }

   . { return JspTokens.JAVA_TEXT; }
}

<LINE_FRAGMENT>
{
   "%>" { yybegin(YYINITIAL); return JspTokens.LINE_FRAGMENT_CLOSE; }

   {WHITESPACE} { return JspTokens.JAVA_TEXT; }

   . { return JspTokens.JAVA_TEXT; }
}

<EXPRESSION>
{
   "}" { yybegin(YYINITIAL); return JspTokens.EXPRESSION_CLOSE; }

   {WHITESPACE} { return JspTokens.JAVA_TEXT; }

   . { return JspTokens.JAVA_TEXT; }
}