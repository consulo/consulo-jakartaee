package consulo.jsp.impl.action;

import consulo.annotation.component.ExtensionImpl;
import consulo.fileTemplate.FileTemplateContributor;
import consulo.fileTemplate.FileTemplateRegistrator;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 22/01/2023
 */
@ExtensionImpl
public class JSPFileTemplateContributor implements FileTemplateContributor
{
	@Override
	public void register(@Nonnull FileTemplateRegistrator registrator)
	{
		registrator.registerInternalTemplate("JSP File");
		registrator.registerInternalTemplate("JSPX File");
	}
}
