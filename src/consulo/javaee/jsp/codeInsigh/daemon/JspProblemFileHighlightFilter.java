package consulo.javaee.jsp.codeInsigh.daemon;

import com.intellij.openapi.util.Condition;
import com.intellij.openapi.vfs.VirtualFile;
import consulo.javaee.jsp.JspFileType;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public class JspProblemFileHighlightFilter implements Condition<VirtualFile>
{
	@Override
	public boolean value(VirtualFile virtualFile)
	{
		return virtualFile.getFileType() == JspFileType.INSTANCE;
	}
}
