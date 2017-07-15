package consulo.javaee.jsp.psi.impl.java.psi.stub.elementType;

import java.io.IOException;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.lang.LighterASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilderFactory;
import com.intellij.lang.PsiParser;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.lang.java.parser.JavaParserUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.java.stubs.PsiJavaFileStub;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import com.intellij.psi.tree.ILightStubFileElementType;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import com.intellij.util.io.StringRef;
import consulo.javaee.jsp.lexer.JspJavaLexer;
import consulo.javaee.jsp.psi.impl.java.parsing.JavaInJspParser;
import consulo.javaee.jsp.psi.impl.java.psi.stub.JspJavaFileElement;
import consulo.javaee.jsp.psi.impl.java.psi.stub.JspJavaFileStub;
import consulo.lang.LanguageVersion;

/**
 * @author VISTALL
 * @since 15-Jul-17
 */
public class JspJavaFileElementType extends ILightStubFileElementType<PsiJavaFileStub>
{
	public static final int STUB_VERSION = 7;

	public JspJavaFileElementType()
	{
		super("jsp.java.file", JavaLanguage.INSTANCE);
	}

	@Override
	public int getStubVersion()
	{
		return STUB_VERSION;
	}

	@Override
	public boolean shouldBuildStubFor(final VirtualFile file)
	{
		return isInSourceContent(file);
	}

	public static boolean isInSourceContent(@NotNull VirtualFile file)
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
	protected ASTNode doParseContents(@NotNull ASTNode chameleon, @NotNull PsiElement psi)
	{
		Project project = psi.getProject();
		LanguageVersion tempLanguageVersion = chameleon.getUserData(LanguageVersion.KEY);
		LanguageVersion languageVersion = tempLanguageVersion == null ? psi.getLanguageVersion() : tempLanguageVersion;

		return doParse(chameleon, project, languageVersion).getTreeBuilt().getFirstChildNode();
	}

	@NotNull
	private PsiBuilder doParse(@NotNull ASTNode chameleon, Project project, LanguageVersion languageVersion)
	{
		LanguageLevel languageLevel = LanguageLevel.HIGHEST;
		PsiBuilder builder = PsiBuilderFactory.getInstance().createBuilder(project, chameleon, new JspJavaLexer(languageLevel), JavaLanguage.INSTANCE, languageVersion, chameleon.getChars());
		JavaParserUtil.setLanguageLevel(builder, languageLevel);
		PsiParser parser = new JavaInJspParser();
		parser.parse(this, builder, languageVersion);
		return builder;
	}

	@NotNull
	@Override
	public String getExternalId()
	{
		return "jsp.java.file";
	}

	@Override
	public void serialize(@NotNull PsiJavaFileStub stub, @NotNull StubOutputStream dataStream) throws IOException
	{
		LanguageLevel level = stub.getLanguageLevel();
		dataStream.writeByte(level != null ? level.ordinal() : -1);
		dataStream.writeName(stub.getPackageName());
	}

	@NotNull
	@Override
	public PsiJavaFileStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException
	{
		int level = dataStream.readByte();
		StringRef packageName = dataStream.readName();
		return new JspJavaFileStub(null, StringRef.toString(packageName), level >= 0 ? LanguageLevel.values()[level] : null);
	}
}