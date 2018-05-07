package consulo.javaee.jsp.psi.impl.descriptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Element;
import javax.annotation.Nonnull;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.JDOMUtil;

/**
 * @author VISTALL
 * @since 22-Jun-17
 */
class JspSchemeLoader
{
	private static final Logger LOGGER = Logger.getInstance(JspSchemeLoader.class);

	public static class Directive
	{
		final String name;
		final List<Attribute> attributes = new ArrayList<>();

		Directive(String name)
		{
			this.name = name;
		}
	}

	public static class Attribute
	{
		final String name;

		Attribute(String name)
		{
			this.name = name;
		}
	}

	private static Map<String, Directive> ourDirectives = new HashMap<>();

	static
	{
		try
		{
			Element element = JDOMUtil.load(JspSchemeLoader.class.getResourceAsStream("/jsp/scheme.xml"));
			for(Element directiveElement : element.getChildren())
			{
				Directive directive = new Directive(directiveElement.getAttributeValue("name"));
				ourDirectives.put(directive.name, directive);

				for(Element attributeElement : directiveElement.getChildren())
				{
					Attribute attribute = new Attribute(attributeElement.getAttributeValue("name"));
					directive.attributes.add(attribute);
				}
			}
		}
		catch(Exception e)
		{
			LOGGER.error(e);
		}
	}

	@Nonnull
	public static Map<String, Directive> getDirectives()
	{
		return ourDirectives;
	}
}
