package consulo.javaee.jsp.lexer;

import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import consulo.javaee.jsp.psi.JspTokens;
import com.intellij.psi.jsp.JspTokenType;
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

ALPHA=[:letter:]
DIGIT=[0-9]
NAME=({ALPHA}|"_")({ALPHA}|{DIGIT}|"_"|"."|"-")*(":"({ALPHA}|"_")?({ALPHA}|{DIGIT}|"_"|"."|"-")*)?

WHITESPACE=[ \n\r\t]+

%state COMMENT
%state DIRECTIVE
%state SCRIPTLET
%state EXPRESSION
%state DECLARATION

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
   "<%"    { yybegin(SCRIPTLET);  return JspTokenType.JSP_SCRIPTLET_START; }

    // declaration
   "<%!"   { yybegin(DECLARATION);  return JspTokenType.JSP_DECLARATION_START; }

    // tag
   "<%@"   { yybegin(DIRECTIVE);  return XmlTokenType.XML_START_TAG_START; }

    // expression
   "<%="   { yybegin(EXPRESSION);  return JspTokenType.JSP_EXPRESSION_START; }

   //"<${"   { yybegin(TAG);  return XmlTokenType.XML_START_TAG_START; }

   {WHITESPACE} { return XmlTokenType.XML_WHITE_SPACE; }

   [^]      { return XmlTokenType.XML_DATA_CHARACTERS; }
}

<DIRECTIVE>
{
    {NAME}        { yybegin(ATTR_LIST);  return XmlTokenType.XML_TAG_NAME; }

    "%>"          { yybegin(YYINITIAL);  return XmlTokenType.XML_EMPTY_ELEMENT_END; }

   {WHITESPACE}   { return XmlTokenType.XML_WHITE_SPACE; }

   [^]            { return TokenType.BAD_CHARACTER; }
}

<ATTR_LIST>
{
    {NAME}        { yybegin(ATTR);  return XmlTokenType.XML_NAME; }

   "%>"           { yybegin(DIRECTIVE); yypushback(yylength()); }

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
    "--%>"             { yybegin(YYINITIAL); return XmlTokenType.XML_COMMENT_END; }

	[^]                { return XmlTokenType.XML_COMMENT_CHARACTERS; }
}