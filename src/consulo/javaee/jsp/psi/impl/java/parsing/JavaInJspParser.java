package consulo.javaee.jsp.psi.impl.java.parsing;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilderFactory;
import com.intellij.lang.PsiParser;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.lang.java.parser.JavaParser;
import com.intellij.lang.java.parser.JavaParserUtil;
import com.intellij.lang.java.parser.StatementParser;
import com.intellij.openapi.project.Project;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.ElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import consulo.javaee.jsp.lexer.JspJavaLexer;
import consulo.javaee.jsp.lexer._JspJavaLexer;
import consulo.javaee.jsp.psi.impl.java.JspJavaElements;
import consulo.lang.LanguageVersion;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public class JavaInJspParser implements PsiParser
{
	public static final IElementType JAVA_IN_JSP_FILE_TYPE = new IFileElementType("JAVA_JSP_FILE", JavaLanguage.INSTANCE)
	{
		@Override
		protected ASTNode doParseContents(@NotNull ASTNode chameleon, @NotNull PsiElement psi)
		{
			Project project = psi.getProject();
			Language languageForParser = this.getLanguageForParser(psi);
			LanguageVersion tempLanguageVersion = chameleon.getUserData(LanguageVersion.KEY);
			LanguageVersion languageVersion = tempLanguageVersion == null ? psi.getLanguageVersion() : tempLanguageVersion;

			LanguageLevel languageLevel = LanguageLevel.HIGHEST;
			PsiBuilder builder = PsiBuilderFactory.getInstance().createBuilder(project, chameleon, new JspJavaLexer(languageLevel), languageForParser, languageVersion, chameleon.getChars());
			//FIXME [VISTALL] we need this? maybe another way for skip it
			builder.enforceCommentTokens(TokenSet.orSet(ElementType.JAVA_COMMENT_BIT_SET, TokenSet.create(_JspJavaLexer.JSP_IN_JAVA)));
			JavaParserUtil.setLanguageLevel(builder, languageLevel);
			PsiParser parser = new JavaInJspParser();
			return parser.parse(this, builder, languageVersion).getFirstChildNode();
		}
	};

	@NotNull
	@Override
	public ASTNode parse(@NotNull IElementType root, @NotNull PsiBuilder builder, @NotNull LanguageVersion languageVersion)
	{
		PsiBuilder.Marker mark = builder.mark();

		PsiBuilder.Marker classMarker = builder.mark();
		PsiBuilder.Marker methodMarker = builder.mark();

		JavaParser parser = new JavaParser();
		StatementParser statementParser = parser.getStatementParser();

		PsiBuilder.Marker bodyMarker = builder.mark();

		statementParser.parseStatements(builder);

		while(!builder.eof())
		{
			builder.advanceLexer();
			builder.error("unexpected token");
		}
		bodyMarker.done(JspJavaElements.JSP_CODE_BLOCK);

		methodMarker.done(JspJavaElements.JSP_HOLDER_METHOD);
		classMarker.done(JspJavaElements.JSP_CLASS);

		mark.done(root);
		return builder.getTreeBuilt();
	}
}
