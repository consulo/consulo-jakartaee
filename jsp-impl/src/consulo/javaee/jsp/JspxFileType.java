package consulo.javaee.jsp;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.ide.highlighter.XmlLikeFileType;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.openapi.fileTypes.TemplateLanguageFileType;
import consulo.javaee.JavaWebIcons;

/**
 * @author VISTALL
 * @since 03-Jul-17
 */
public class JspxFileType  extends XmlLikeFileType implements TemplateLanguageFileType
{
	public static final JspFileType INSTANCE = new JspFileType();

	protected JspxFileType()
	{
		super(XMLLanguage.INSTANCE);
	}

	@NotNull
	@Override
	public String getId()
	{
		return "JSPX";
	}

	@NotNull
	@Override
	public String getDescription()
	{
		return "JSPX files";
	}

	@NotNull
	@Override
	public String getDefaultExtension()
	{
		return "jspx";
	}

	@Nullable
	@Override
	public Icon getIcon()
	{
		return JavaWebIcons.Jspx;
	}
}
