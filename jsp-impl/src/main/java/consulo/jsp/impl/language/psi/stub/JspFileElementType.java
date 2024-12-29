package consulo.jsp.impl.language.psi.stub;

import consulo.language.Language;
import consulo.language.psi.stub.IStubFileElementType;
import consulo.language.psi.stub.PsiFileStub;

import jakarta.annotation.Nonnull;

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

	@Override
	public int getStubVersion()
	{
		return super.getStubVersion() + 3;
	}

	@Nonnull
	@Override
	public String getExternalId()
	{
		return "jsp.file";
	}
}
