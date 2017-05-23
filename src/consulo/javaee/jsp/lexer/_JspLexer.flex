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

ALPHA=[:letter:]
DIGIT=[0-9]
NAME=({ALPHA}|"_")({ALPHA}|{DIGIT}|"_"|"."|"-")*(":"({ALPHA}|"_")?({ALPHA}|{DIGIT}|"_"|"."|"-")*)?

WHITESPACE=[ \n\r\t]+

%state TAG
%state JAVA
%state COMMENT

// xml part
%state ATTR_LIST
%state ATTR
%state ATTR_VALUE_DQ
%state ATTR_VALUE_SQ

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
    {NAME}        { yybegin(ATTR_LIST);  return XmlTokenType.XML_TAG_NAME;      }

    "%>"          { yybegin(YYINITIAL);       return XmlTokenType.XML_END_TAG_START; }

   {WHITESPACE}   { return XmlTokenType.XML_WHITE_SPACE; }

   [^]            { return TokenType.BAD_CHARACTER; }
}

<ATTR_LIST>
{
    {NAME}        { yybegin(ATTR);  return XmlTokenType.XML_NAME; }

   "%>"           { yybegin(TAG); yypushback(yylength()); }

   {WHITESPACE}   { return XmlTokenType.XML_WHITE_SPACE; }

   [^]            { return TokenType.BAD_CHARACTER; }
}

<ATTR> "=" { return XmlTokenType.XML_EQ;}
<ATTR> "'" { yybegin(ATTR_VALUE_SQ); return XmlTokenType.XML_ATTRIBUTE_VALUE_START_DELIMITER;}
<ATTR> "\"" { yybegin(ATTR_VALUE_DQ); return XmlTokenType.XML_ATTRIBUTE_VALUE_START_DELIMITER;}
<ATTR> [^\ \n\r\t\f] {yybegin(ATTR_LIST); yypushback(yylength()); }

<ATTR_VALUE_DQ>{
  "\"" { yybegin(ATTR_LIST); return XmlTokenType.XML_ATTRIBUTE_VALUE_END_DELIMITER;}
  "&" { return XmlTokenType.XML_BAD_CHARACTER; }
  [^] { return XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN;}
}

<ATTR_VALUE_SQ>{
  "&" { return XmlTokenType.XML_BAD_CHARACTER; }
  "'" { yybegin(ATTR_LIST); return XmlTokenType.XML_ATTRIBUTE_VALUE_END_DELIMITER;}
  [^] { return XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN;}
}


<ATTR_LIST,ATTR> {WHITESPACE} { return XmlTokenType.XML_WHITE_SPACE; }

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