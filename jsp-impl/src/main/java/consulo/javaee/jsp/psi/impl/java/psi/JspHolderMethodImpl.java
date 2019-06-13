package consulo.javaee.jsp.psi.impl.java.psi;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.*;
import com.intellij.psi.impl.PsiImplUtil;
import com.intellij.psi.impl.PsiSuperMethodImplUtil;
import com.intellij.psi.impl.light.LightModifierList;
import com.intellij.psi.impl.light.LightParameterListBuilder;
import com.intellij.psi.impl.light.LightReferenceListBuilder;
import com.intellij.psi.impl.source.jsp.jspJava.JspClass;
import com.intellij.psi.impl.source.jsp.jspJava.JspHolderMethod;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.stubs.EmptyStub;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.util.MethodSignature;
import com.intellij.psi.util.MethodSignatureBackedByPsiMethod;
import consulo.annotations.RequiredReadAction;
import consulo.java.module.util.JavaClassNames;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public class JspHolderMethodImpl extends StubBasedPsiElementBase<EmptyStub> implements JspHolderMethod
{
	public JspHolderMethodImpl(@Nonnull ASTNode node)
	{
		super(node);
	}

	public JspHolderMethodImpl(@Nonnull EmptyStub stub, @Nonnull IStubElementType nodeType)
	{
		super(stub, nodeType);
	}

	@Override
	public PsiElement getParent()
	{
		return getParentByStub();
	}

	@RequiredReadAction
	@Override
	public String getName()
	{
		return "jsp_holder_method";
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

	@Nonnull
	@Override
	public PsiParameterList getParameterList()
	{
		return new LightParameterListBuilder(PsiManager.getInstance(getProject()), getLanguage());
	}

	@Nonnull
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

	@Nonnull
	@Override
	public MethodSignature getSignature(@Nonnull PsiSubstitutor psiSubstitutor)
	{
		return MethodSignatureBackedByPsiMethod.create(this, psiSubstitutor);
	}

	@Nullable
	@Override
	public PsiIdentifier getNameIdentifier()
	{
		return null;
	}

	@Nonnull
	@Override
	public PsiMethod[] findSuperMethods()
	{
		return new PsiMethod[0];
	}

	@Nonnull
	@Override
	public PsiMethod[] findSuperMethods(boolean b)
	{
		return new PsiMethod[0];
	}

	@Nonnull
	@Override
	public PsiMethod[] findSuperMethods(PsiClass psiClass)
	{
		return new PsiMethod[0];
	}

	@Nonnull
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

	@Nonnull
	@Override
	public PsiMethod[] findDeepestSuperMethods()
	{
		return new PsiMethod[0];
	}

	@Nonnull
	@Override
	public PsiModifierList getModifierList()
	{
		return new LightModifierList(getManager());
	}

	@Override
	public boolean hasModifierProperty(@PsiModifier.ModifierConstant @NonNls @Nonnull String s)
	{
		return false;
	}

	@Override
	public PsiElement setName(@NonNls @Nonnull String s)
	{
		return null;
	}

	@Nonnull
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

	@Nonnull
	@Override
	public PsiTypeParameter[] getTypeParameters()
	{
		return PsiTypeParameter.EMPTY_ARRAY;
	}

	@Nullable
	@Override
	public PsiClass getContainingClass()
	{
		return getStubOrPsiParentOfType(JspClass.class);
	}

	@Override
	public boolean processDeclarations(@Nonnull PsiScopeProcessor processor, @Nonnull ResolveState state, PsiElement lastParent, @Nonnull PsiElement place)
	{
		return PsiImplUtil.processDeclarationsInMethod(this, processor, state, lastParent, place);
	}
}
