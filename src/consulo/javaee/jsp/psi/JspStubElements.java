package consulo.javaee.jsp.psi;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.lang.LighterASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilderFactory;
import com.intellij.lang.PsiParser;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.lang.java.parser.JavaParserUtil;
import com.intellij.openapi.project.Project;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.ILightStubFileElementType;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import consulo.javaee.jsp.lexer.JspJavaLexer;
import consulo.javaee.jsp.psi.impl.java.parsing.JavaInJspParser;
import consulo.lang.LanguageVersion;

/**
 * @author VISTALL
 * @since 21-Jun-17
 */
public interface JspStubElements
{
	IElementType JAVA_IN_JSP_FILE_TYPE = new ILightStubFileElementType("JAVA_JSP_FILE", JavaLanguage.INSTANCE)
	{
		public FlyweightCapableTreeStructure<LighterASTNode> parseContentsLight(final ASTNode chameleon)
		{
			final PsiElement psi = chameleon.getPsi();
			assert psi != null : "Bad chameleon: " + chameleon;

			final Project project = psi.getProject();
			final LanguageVersion languageVersion = psi.getLanguageVersion();

			return doParse(chameleon, project, languageVersion).getLightTree();
		}

		@NotNull
		@Override
		public String getExternalId()
		{
			return "jsp.java.file";
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

		@Override
		public int getStubVersion()
		{
			return 2;
		}
	};

}
