package consulo.javaee.jsp;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.ide.highlighter.XmlLikeFileType;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.openapi.fileTypes.TemplateLanguageFileType;
import consulo.javaee.JavaEEIcons;
import consulo.ui.image.Image;

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
	public String getDescription()
	{
		return "JSPX files";
	}

	@Nonnull
	@Override
	public String getDefaultExtension()
	{
		return "jspx";
	}

	@Nullable
	@Override
	public Image getIcon()
	{
		return JavaEEIcons.Jspx;
	}
}
