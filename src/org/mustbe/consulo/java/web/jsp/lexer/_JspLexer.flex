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

WHITESPACE=[ \n\r\t]+

%state FRAGMENT

%%

<YYINITIAL>
{
   "<%" { yybegin(FRAGMENT); return JspTokens.FRAGMENT_OPEN; }

   "<%@" { return JspTokens.DIRECTIVE_OPEN; }

   {WHITESPACE} { return JspTokens.HTML_TEXT; }

   . { return JspTokens.HTML_TEXT; }
}

<FRAGMENT>
{
   "%>" { yybegin(YYINITIAL); return JspTokens.FRAGMENT_CLOSE; }

   {WHITESPACE} { return JspTokens.JAVA_TEXT; }

   . { return JspTokens.JAVA_TEXT; }
}