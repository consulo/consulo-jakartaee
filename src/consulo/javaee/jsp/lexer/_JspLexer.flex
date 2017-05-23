package consulo.javaee.jsp.lexer;

import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import consulo.javaee.jsp.psi.JspTokens;
import com.intellij.psi.xml.XmlTokenType;
%%

%public
%class _JspLexer
%extends LexerBase
%public
%unicode
%public

%function advanceImpl
%type IElementType

%eof{ return;
%eof}

WHITESPACE=[ \n\r\t]+

%state TAG
%state JAVA
%state COMMENT

%%

<YYINITIAL>
{
    // comment
   "<%--"  { yybegin(COMMENT);  return XmlTokenType.XML_COMMENT_START; }

    // code frament
   "<%"    { yybegin(JAVA);  return XmlTokenType.XML_START_TAG_START; }

    // declaration
   "<%!"   { yybegin(JAVA);  return XmlTokenType.XML_START_TAG_START; }

    // tag
   "<%@"   { yybegin(TAG);  return XmlTokenType.XML_START_TAG_START; }

    // expression
   "<%="   { yybegin(JAVA);  return XmlTokenType.XML_START_TAG_START; }

   //"<${"   { yybegin(TAG);  return XmlTokenType.XML_START_TAG_START; }

   {WHITESPACE} { return XmlTokenType.XML_WHITE_SPACE; }

   [^]      { return XmlTokenType.XML_DATA_CHARACTERS; }
}

<TAG>
{
    "%>"   { yybegin(YYINITIAL);  return XmlTokenType.XML_END_TAG_START; }

    //"}$>"  { yybegin(YYINITIAL);  return XmlTokenType.XML_END_TAG_START; }

   {WHITESPACE} { return XmlTokenType.XML_WHITE_SPACE; }

   [^] { return TokenType.BAD_CHARACTER; }
}

<JAVA>
{
    "%>"   { yybegin(YYINITIAL);  return XmlTokenType.XML_END_TAG_START; }

    [^]    { return JspTokens.JAVA_FRAGMENT; }
}

<COMMENT>
{
    "--%>"             { yybegin(YYINITIAL); return XmlTokenType.XML_COMMENT_END; }

	[^]                { return XmlTokenType.XML_COMMENT_CHARACTERS; }
}