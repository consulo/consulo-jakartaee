package consulo.javaee.jsp.psi.impl.java.psi;

import java.util.Collection;
import java.util.List;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.Pair;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.*;
import com.intellij.psi.impl.PsiClassImplUtil;
import com.intellij.psi.impl.source.jsp.jspJava.JspClass;
import com.intellij.psi.impl.source.jsp.jspJava.JspHolderMethod;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.jsp.JspFile;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.util.PsiUtil;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public class JspClassImpl extends ASTWrapperPsiElement implements JspClass
{
	public JspClassImpl(@NotNull ASTNode node)
	{
		super(node);
	}

	@Override
	public JspHolderMethod getHolderMethod()
	{
		return findChildByClass(JspHolderMethod.class);
	}

	@Override
	public JspFile getJspxFile()
	{
		return (JspFile) getContainingFile();
	}

	@Nullable
	@Override
	public String getQualifiedName()
	{
		return null;
	}

	@Override
	public boolean isInterface()
	{
		return false;
	}

	@Override
	public boolean isAnnotationType()
	{
		return false;
	}

	@Override
	public boolean isEnum()
	{
		return false;
	}

	@Nullable
	@Override
	public PsiReferenceList getExtendsList()
	{
		return null;
	}

	@Nullable
	@Override
	public PsiReferenceList getImplementsList()
	{
		return null;
	}

	@NotNull
	@Override
	public PsiClassType[] getExtendsListTypes()
	{
		return new PsiClassType[0];
	}

	@NotNull
	@Override
	public PsiClassType[] getImplementsListTypes()
	{
		return new PsiClassType[0];
	}

	@Nullable
	@Override
	public PsiClass getSuperClass()
	{
		return null;
	}

	@Override
	public PsiClass[] getInterfaces()
	{
		return new PsiClass[0];
	}

	@NotNull
	@Override
	public PsiClass[] getSupers()
	{
		return new PsiClass[0];
	}

	@NotNull
	@Override
	public PsiClassType[] getSuperTypes()
	{
		return new PsiClassType[0];
	}

	@NotNull
	@Override
	public PsiField[] getFields()
	{
		return new PsiField[0];
	}

	@NotNull
	@Override
	public PsiMethod[] getMethods()
	{
		return findChildrenByClass(PsiMethod.class);
	}

	@NotNull
	@Override
	public PsiMethod[] getConstructors()
	{
		return new PsiMethod[0];
	}

	@NotNull
	@Override
	public PsiClass[] getInnerClasses()
	{
		return new PsiClass[0];
	}

	@NotNull
	@Override
	public PsiClassInitializer[] getInitializers()
	{
		return new PsiClassInitializer[0];
	}

	@NotNull
	@Override
	public PsiField[] getAllFields()
	{
		return new PsiField[0];
	}

	@NotNull
	@Override
	public PsiMethod[] getAllMethods()
	{
		return getMethods();
	}

	@NotNull
	@Override
	public PsiClass[] getAllInnerClasses()
	{
		return new PsiClass[0];
	}

	@Nullable
	@Override
	public PsiField findFieldByName(@NonNls String s, boolean b)
	{
		return null;
	}

	@Nullable
	@Override
	public PsiMethod findMethodBySignature(PsiMethod psiMethod, boolean b)
	{
		return null;
	}

	@NotNull
	@Override
	public PsiMethod[] findMethodsBySignature(PsiMethod psiMethod, boolean b)
	{
		return new PsiMethod[0];
	}

	@NotNull
	@Override
	public PsiMethod[] findMethodsByName(@NonNls String s, boolean b)
	{
		return new PsiMethod[0];
	}

	@NotNull
	@Override
	public List<Pair<PsiMethod, PsiSubstitutor>> findMethodsAndTheirSubstitutorsByName(@NonNls String s, boolean b)
	{
		return null;
	}

	@NotNull
	@Override
	public List<Pair<PsiMethod, PsiSubstitutor>> getAllMethodsAndTheirSubstitutors()
	{
		return null;
	}

	@Nullable
	@Override
	public PsiClass findInnerClassByName(@NonNls String s, boolean b)
	{
		return null;
	}

	@Nullable
	@Override
	public PsiElement getLBrace()
	{
		return null;
	}

	@Nullable
	@Override
	public PsiElement getRBrace()
	{
		return null;
	}

	@Nullable
	@Override
	public PsiIdentifier getNameIdentifier()
	{
		return null;
	}

	@Override
	public PsiElement getScope()
	{
		return getContainingClass();
	}

	@Override
	public boolean isInheritor(@NotNull PsiClass psiClass, boolean b)
	{
		return false;
	}

	@Override
	public boolean isInheritorDeep(PsiClass psiClass, @Nullable PsiClass psiClass1)
	{
		return false;
	}

	@Nullable
	@Override
	public PsiClass getContainingClass()
	{
		return null;
	}

	@NotNull
	@Override
	public Collection<HierarchicalMethodSignature> getVisibleSignatures()
	{
		return null;
	}

	@Override
	public PsiElement setName(@NonNls @NotNull String s)
	{
		return null;
	}

	@Override
	public boolean isDeprecated()
	{
		return false;
	}

	@Nullable
	@Override
	public PsiDocComment getDocComment()
	{
		return null;
	}

	@Override
	public boolean hasTypeParameters()
	{
		return false;
	}

	@Nullable
	@Override
	public PsiTypeParameterList getTypeParameterList()
	{
		return null;
	}

	@NotNull
	@Override
	public PsiTypeParameter[] getTypeParameters()
	{
		return new PsiTypeParameter[0];
	}

	@Nullable
	@Override
	public PsiModifierList getModifierList()
	{
		return null;
	}

	@Override
	public boolean hasModifierProperty(@PsiModifier.ModifierConstant @NonNls @NotNull String s)
	{
		return PsiModifier.STATIC.equals(s);
	}

	@Override
	public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement place)
	{
		LanguageLevel level = PsiUtil.getLanguageLevel(place);
		return PsiClassImplUtil.processDeclarationsInClass(this, processor, state, null, lastParent, place, level, false);
	}
}
