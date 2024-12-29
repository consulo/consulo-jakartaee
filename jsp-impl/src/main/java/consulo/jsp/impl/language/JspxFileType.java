package consulo.jsp.impl.language;

import consulo.javaee.JavaEEIcons;
import consulo.language.template.TemplateLanguageFileType;
import consulo.localize.LocalizeValue;
import consulo.ui.image.Image;
import consulo.xml.ide.highlighter.XmlLikeFileType;
import consulo.xml.lang.xml.XMLLanguage;

import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 03-Jul-17
 */
public class JspxFileType  extends XmlLikeFileType implements TemplateLanguageFileType
{
	public static final JspxFileType INSTANCE = new JspxFileType();

	private JspxFileType()
	{
		super(XMLLanguage.INSTANCE);
	}

	@Nonnull
	@Override
	public String getId()
	{
		return "JSPX";
	}

	@Nonnull
	@Override
	public LocalizeValue getDescription()
	{
		return LocalizeValue.localizeTODO("JSPX files");
	}

	@Nonnull
	@Override
	public String getDefaultExtension()
	{
		return "jspx";
	}

	@Nonnull
	@Override
	public Image getIcon()
	{
		return JavaEEIcons.Jspx;
	}
}
