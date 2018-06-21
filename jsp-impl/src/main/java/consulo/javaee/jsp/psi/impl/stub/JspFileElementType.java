package consulo.javaee.jsp.psi.impl.stub;

import javax.annotation.Nonnull;
import com.intellij.lang.Language;
import com.intellij.psi.stubs.PsiFileStub;
import com.intellij.psi.tree.IStubFileElementType;

/**
 * @author VISTALL
 * @since 15-Jul-17
 */
public class JspFileElementType extends IStubFileElementType<PsiFileStub>
{
	public JspFileElementType(Language language)
	{
		super("jsp.file", language);
	}

	@Nonnull
	@Override
	public String getExternalId()
	{
		return "jsp.file";
	}
}
