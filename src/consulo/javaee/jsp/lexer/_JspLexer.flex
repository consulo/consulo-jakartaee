package consulo.javaee.jsp.lexer;

import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
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
%state COMMENT

%%

<YYINITIAL>
{
   "<%--"  { yybegin(COMMENT);  return XmlTokenType.XML_COMMENT_START; }

   "<%"    { yybegin(TAG);  return XmlTokenType.XML_START_TAG_START; }

   "<%@"   { yybegin(TAG);  return XmlTokenType.XML_START_TAG_START; }

   "<%="   { yybegin(TAG);  return XmlTokenType.XML_START_TAG_START; }

   "<${"   { yybegin(TAG);  return XmlTokenType.XML_START_TAG_START; }

    "%>"   { yybegin(YYINITIAL);  return XmlTokenType.XML_END_TAG_START; }

    "@%>"  { yybegin(YYINITIAL);  return XmlTokenType.XML_END_TAG_START; }

    "=%>"  { yybegin(YYINITIAL);  return XmlTokenType.XML_END_TAG_START; }

    "}$>"  { yybegin(YYINITIAL);  return XmlTokenType.XML_END_TAG_START; }

   {WHITESPACE} { return XmlTokenType.XML_WHITE_SPACE; }

   [^]      { return XmlTokenType.XML_DATA_CHARACTERS; }
}

<TAG>
{
    "%>"   { yybegin(YYINITIAL);  return XmlTokenType.XML_END_TAG_START; }

    "@%>"  { yybegin(YYINITIAL);  return XmlTokenType.XML_END_TAG_START; }

    "=%>"  { yybegin(YYINITIAL);  return XmlTokenType.XML_END_TAG_START; }

    "}$>"  { yybegin(YYINITIAL);  return XmlTokenType.XML_END_TAG_START; }

   {WHITESPACE} { return XmlTokenType.XML_WHITE_SPACE; }

   [^] { return TokenType.BAD_CHARACTER; }
}


<COMMENT>
{
    "--%>"             { yybegin(YYINITIAL); return XmlTokenType.XML_COMMENT_END; }

	[^]                { return XmlTokenType.XML_COMMENT_CHARACTERS; }
}