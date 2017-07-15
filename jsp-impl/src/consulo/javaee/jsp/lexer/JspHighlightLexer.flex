package consulo.javaee.jsp.lexer;

import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import consulo.javaee.jsp.psi.JspTokens;
import com.intellij.psi.jsp.JspTokenType;
import com.intellij.psi.xml.XmlTokenType;
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

ALPHA=[:letter:]
DIGIT=[0-9]
NAME=({ALPHA}|"_")({ALPHA}|{DIGIT}|"_"|"."|"-")*(":"({ALPHA}|"_")?({ALPHA}|{DIGIT}|"_"|"."|"-")*)?

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
    {NAME}        { yybegin(ATTR_LIST);  return XmlTokenType.XML_TAG_NAME; }

    "%>"          { yybegin(YYINITIAL);  return JspTokenType.JSP_DIRECTIVE_END; }

   {WHITESPACE}   { return XmlTokenType.XML_WHITE_SPACE; }

   [^]            { return XmlTokenType.XML_BAD_CHARACTER; }
}

<ATTR_LIST>
{
    {NAME}        { yybegin(ATTR);  return XmlTokenType.XML_NAME; }

   "%>"           { yybegin(DIRECTIVE); yypushback(yylength()); }

   {WHITESPACE}   { return XmlTokenType.XML_WHITE_SPACE; }

   [^]            { return XmlTokenType.XML_BAD_CHARACTER; }
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

	[^]                { return JspTokenType.JAVA_CODE; }
}

<SCRIPTLET>
{
	"%>"              { yybegin(YYINITIAL); return JspTokenType.JSP_SCRIPTLET_END; }

	[^]                { return JspTokenType.JAVA_CODE; }
}

<EXPRESSION>
{
	"%>"              { yybegin(YYINITIAL); return JspTokenType.JSP_EXPRESSION_END; }

	[^]                { return JspTokenType.JAVA_CODE; }
}

<COMMENT>
{
	"--%>"              { yybegin(YYINITIAL); return JspTokenType.JSP_COMMENT; }

	[^]                { return JspTokenType.JSP_COMMENT; }
}