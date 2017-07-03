package consulo.javaee.jsp.lexer;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.tree.IElementType;

/**
 * @author VISTALL
 * @since 23-May-17
 */
public class T
{
	public static void main(String[] args)
	{
		String a = "<%\n" + "                Iterator<Cliente> iterator = lista.iterator();\n" + "                while (iterator.hasNext()) {\n" + "                    Cliente c = iterator.next();\n" + "            %>\n" + "            <tr class=\"even\">\n" + "                <td><%=c.getNome()%></td>\n" + "                <td><%=c.getEnderecoCidade()%></td>\n" + "                <td><%=c.getEnderecoEstado()%></td>\n" + "                <td><%=c.getEnderecoRua()%></td>\n" + "                <td><%=c.getEnderecoCep()%></td>\n" + "                <td><%=c.getTelefone()%></td>\n" + "           <!--<td><img src=<%=request.getContextPath()%>\"/daniel/images/excluir.gif\" alt=\"Excluir\" onclick=\"excluir()\"/></td>-->\n" + "                <!--<td><input type='image' src='<%=request.getContextPath()%>/daniel/images/excluir.gif'> </td>-->\n" + "                <!--<td><img src=<%=request.getContextPath()%>\"/daniel/images/excluir.gif\" alt=\"Excluir\" onclick=\"excluir()\"/></td> -->\n" + "            </tr>\n" + "            <%\n" + "                }\n" + "            %>";

		Lexer lexer = new JspJavaLexer(LanguageLevel.HIGHEST);

		lexer.start(a);


		IElementType elementType = null;
		while((elementType = lexer.getTokenType()) != null)
		{
			System.out.println(elementType + "='" + StringUtil.escapeLineBreak(lexer.getTokenText()) + "'");
			lexer.advance();
		}
	}
}
