package consulo.jsp.impl.language.psi.java.psi.stub.elementType;

import com.intellij.java.language.JavaLanguage;
import com.intellij.java.language.LanguageLevel;
import com.intellij.java.language.impl.parser.JavaParserUtil;
import com.intellij.java.language.impl.psi.impl.java.stubs.PsiJavaFileStub;
import consulo.jsp.impl.language.lexer.JspJavaLexer;
import consulo.jsp.impl.language.psi.java.parsing.JavaInJspParser;
import consulo.jsp.impl.language.psi.java.psi.stub.JspJavaFileElement;
import consulo.jsp.impl.language.psi.java.psi.stub.JspJavaFileStub;
import consulo.language.ast.ASTNode;
import consulo.language.ast.LighterASTNode;
import consulo.language.parser.PsiBuilder;
import consulo.language.parser.PsiBuilderFactory;
import consulo.language.parser.PsiParser;
import consulo.language.psi.PsiElement;
import consulo.language.psi.stub.ILightStubFileElementType;
import consulo.language.psi.stub.StubElement;
import consulo.language.psi.stub.StubInputStream;
import consulo.language.psi.stub.StubOutputStream;
import consulo.language.util.FlyweightCapableTreeStructure;
import consulo.language.version.LanguageVersion;
import consulo.project.Project;
import consulo.virtualFileSystem.VirtualFile;

import jakarta.annotation.Nonnull;
import java.io.IOException;

/**
 * @author VISTALL
 * @since 15-Jul-17
 */
public class JspJavaFileElementType extends ILightStubFileElementType<PsiJavaFileStub>
{
	public static final int STUB_VERSION = 9;

	public JspJavaFileElementType()
	{
		super("jsp.java.file", JavaLanguage.INSTANCE);
	}

	@Override
	public int getStubVersion()
	{
		return super.getStubVersion() + STUB_VERSION;
	}

	@Override
	public boolean shouldBuildStubFor(final VirtualFile file)
	{
		return isInSourceContent(file);
	}

	public static boolean isInSourceContent(@Nonnull VirtualFile file)
	{
		final VirtualFile dir = file.getParent();
		return dir == null || dir.getUserData(LanguageLevel.KEY) != null;
	}

	@Override
	public ASTNode createNode(final CharSequence text)
	{
		return new JspJavaFileElement(this, text);
	}

	@Override
	public FlyweightCapableTreeStructure<LighterASTNode> parseContentsLight(final ASTNode chameleon)
	{
		final PsiElement psi = chameleon.getPsi();
		assert psi != null : "Bad chameleon: " + chameleon;

		final Project project = psi.getProject();
		final LanguageVersion languageVersion = psi.getLanguageVersion();

		return doParse(chameleon, project, languageVersion).getLightTree();
	}

	@Override
	protected ASTNode doParseContents(@Nonnull ASTNode chameleon, @Nonnull PsiElement psi)
	{
		Project project = psi.getProject();
		LanguageVersion tempLanguageVersion = chameleon.getUserData(LanguageVersion.KEY);
		LanguageVersion languageVersion = tempLanguageVersion == null ? psi.getLanguageVersion() : tempLanguageVersion;

		return doParse(chameleon, project, languageVersion).getTreeBuilt().getFirstChildNode();
	}

	@Nonnull
	private PsiBuilder doParse(@Nonnull ASTNode chameleon, Project project, LanguageVersion languageVersion)
	{
		LanguageLevel languageLevel = LanguageLevel.HIGHEST;
		PsiBuilder builder = PsiBuilderFactory.getInstance().createBuilder(project, chameleon, new JspJavaLexer(languageLevel), JavaLanguage.INSTANCE, languageVersion, chameleon.getChars());
		JavaParserUtil.setLanguageLevel(builder, languageLevel);
		PsiParser parser = new JavaInJspParser();
		parser.parse(this, builder, languageVersion);
		return builder;
	}

	@Nonnull
	@Override
	public String getExternalId()
	{
		return "jsp.java.file";
	}

	@Override
	public void serialize(@Nonnull PsiJavaFileStub stub, @Nonnull StubOutputStream dataStream) throws IOException
	{
		LanguageLevel level = stub.getLanguageLevel();
		dataStream.writeByte(level != null ? level.ordinal() : -1);
	}

	@Nonnull
	@Override
	public PsiJavaFileStub deserialize(@Nonnull StubInputStream dataStream, StubElement parentStub) throws IOException
	{
		int level = dataStream.readByte();
		return new JspJavaFileStub(null, level >= 0 ? LanguageLevel.values()[level] : null);
	}
}