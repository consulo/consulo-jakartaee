package consulo.javaee.jsp.psi.impl.java.psi;

import java.util.List;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.*;
import com.intellij.psi.impl.PsiSuperMethodImplUtil;
import com.intellij.psi.impl.light.LightModifierList;
import com.intellij.psi.impl.light.LightParameterListBuilder;
import com.intellij.psi.impl.light.LightReferenceListBuilder;
import com.intellij.psi.impl.source.jsp.jspJava.JspClass;
import com.intellij.psi.impl.source.jsp.jspJava.JspHolderMethod;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.util.MethodSignature;
import com.intellij.psi.util.MethodSignatureBackedByPsiMethod;
import consulo.java.module.util.JavaClassNames;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public class JspHolderMethodImpl extends ASTWrapperPsiElement implements JspHolderMethod
{
	public JspHolderMethodImpl(@NotNull ASTNode node)
	{
		super(node);
	}

	@Override
	public JspClass getJspClass()
	{
		return (JspClass) getParent();
	}

	@Nullable
	@Override
	public PsiType getReturnType()
	{
		return PsiType.VOID;
	}

	@Nullable
	@Override
	public PsiTypeElement getReturnTypeElement()
	{
		return null;
	}

	@NotNull
	@Override
	public PsiParameterList getParameterList()
	{
		return new LightParameterListBuilder(PsiManager.getInstance(getProject()), getLanguage());
	}

	@NotNull
	@Override
	public PsiReferenceList getThrowsList()
	{
		LightReferenceListBuilder builder = new LightReferenceListBuilder(getManager(), PsiReferenceList.Role.THROWS_LIST);
		builder.addReference(JavaClassNames.JAVA_LANG_THROWABLE);
		return builder;
	}

	@Nullable
	@Override
	public PsiCodeBlock getBody()
	{
		return findNotNullChildByClass(PsiCodeBlock.class);
	}

	@Override
	public boolean isConstructor()
	{
		return false;
	}

	@Override
	public boolean isVarArgs()
	{
		return false;
	}

	@NotNull
	@Override
	public MethodSignature getSignature(@NotNull PsiSubstitutor psiSubstitutor)
	{
		return MethodSignatureBackedByPsiMethod.create(this, psiSubstitutor);
	}

	@Nullable
	@Override
	public PsiIdentifier getNameIdentifier()
	{
		return null;
	}

	@NotNull
	@Override
	public PsiMethod[] findSuperMethods()
	{
		return new PsiMethod[0];
	}

	@NotNull
	@Override
	public PsiMethod[] findSuperMethods(boolean b)
	{
		return new PsiMethod[0];
	}

	@NotNull
	@Override
	public PsiMethod[] findSuperMethods(PsiClass psiClass)
	{
		return new PsiMethod[0];
	}

	@NotNull
	@Override
	public List<MethodSignatureBackedByPsiMethod> findSuperMethodSignaturesIncludingStatic(boolean checkAccess)
	{
		return PsiSuperMethodImplUtil.findSuperMethodSignaturesIncludingStatic(this, checkAccess);
	}

	@Nullable
	@Override
	public PsiMethod findDeepestSuperMethod()
	{
		return null;
	}

	@NotNull
	@Override
	public PsiMethod[] findDeepestSuperMethods()
	{
		return new PsiMethod[0];
	}

	@NotNull
	@Override
	public PsiModifierList getModifierList()
	{
		return new LightModifierList(getManager());
	}

	@Override
	public boolean hasModifierProperty(@PsiModifier.ModifierConstant @NonNls @NotNull String s)
	{
		return false;
	}

	@Override
	public PsiElement setName(@NonNls @NotNull String s)
	{
		return null;
	}

	@NotNull
	@Override
	public HierarchicalMethodSignature getHierarchicalMethodSignature()
	{
		return PsiSuperMethodImplUtil.getHierarchicalMethodSignature(this);
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
	public PsiClass getContainingClass()
	{
		return null;
	}
}
