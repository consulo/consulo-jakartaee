package consulo.javaee.jsp.psi.impl.java.psi;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.ItemPresentationProviders;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.*;
import com.intellij.psi.impl.InheritanceImplUtil;
import com.intellij.psi.impl.PsiClassImplUtil;
import com.intellij.psi.impl.java.stubs.PsiClassStub;
import com.intellij.psi.impl.light.LightFieldBuilder;
import com.intellij.psi.impl.light.LightMethodBuilder;
import com.intellij.psi.impl.source.ClassInnerStuffCache;
import com.intellij.psi.impl.source.PsiExtensibleClass;
import com.intellij.psi.impl.source.jsp.jspJava.JspClass;
import com.intellij.psi.impl.source.jsp.jspJava.JspClassLevelDeclarationStatement;
import com.intellij.psi.impl.source.jsp.jspJava.JspHolderMethod;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.jsp.JspFile;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.util.PsiUtil;
import consulo.annotation.access.RequiredReadAction;
import consulo.javaee.jsp.JspLanguage;
import consulo.javaee.jsp.ServletApiClassNames;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public class JspClassImpl extends StubBasedPsiElementBase<PsiClassStub<JspClass>> implements JspClass, PsiExtensibleClass, StubBasedPsiElement<PsiClassStub<JspClass>>
{
	private final ClassInnerStuffCache myInnersCache = new ClassInnerStuffCache(this);

	public JspClassImpl(@Nonnull ASTNode node)
	{
		super(node);
	}

	public JspClassImpl(@Nonnull PsiClassStub<JspClass> stub, @Nonnull IStubElementType nodeType)
	{
		super(stub, nodeType);
	}

	@Nonnull
	@RequiredReadAction
	private List<PsiField> buildImplicitFields()
	{
		List<PsiField> fields = new ArrayList<>();
		addField(fields, JspHolderMethod.OUT_VAR_NAME, PrintWriter.class.getName());
		addField(fields, JspHolderMethod.RESPONSE_VAR_NAME, ServletApiClassNames.HttpServletResponse);
		addField(fields, JspHolderMethod.REQUEST_VAR_NAME, ServletApiClassNames.HttpServletRequest);
		addField(fields, JspHolderMethod.SESSION_VAR_NAME, ServletApiClassNames.HttpSession);
		addField(fields, JspHolderMethod.PAGE_VAR_NAME, Object.class.getName());
		addField(fields, JspHolderMethod.APPLICATION_VAR_NAME, ServletApiClassNames.ServletContext);
		addField(fields, JspHolderMethod.CONFIG_VAR_NAME, ServletApiClassNames.ServletConfig);
		addField(fields, JspHolderMethod.CONTEXT_VAR_NAME, ServletApiClassNames.PageContext);

		JspFile jspxFile = getJspxFile();
		if(jspxFile.isErrorPage())
		{
			addField(fields, JspHolderMethod.EXCEPTION_VAR_NAME, Throwable.class.getName());
		}
		return fields;
	}

	private void addField(List<PsiField> fields, String name, String type)
	{
		PsiClassType classType = JavaPsiFacade.getElementFactory(getProject()).createTypeByFQClassName(type, getResolveScope());

		LightFieldBuilder builder = new LightFieldBuilder(getManager(), name, classType);
		builder.setModifiers(PsiModifier.PRIVATE, PsiModifier.FINAL);
		builder.setContainingClass(this);
		fields.add(builder);
	}

	@RequiredReadAction
	@Override
	public String getName()
	{
		PsiFile containingFile = getContainingFile();
		return buildName(containingFile);
	}

	@Nonnull
	public static String buildName(@Nonnull PsiFile containingFile)
	{
		return FileUtil.getNameWithoutExtension(containingFile.getName()) + "_jsp";
	}

	@Override
	@Nonnull
	public JspHolderMethod getHolderMethod()
	{
		return findNotNullChildByClass(JspHolderMethod.class);
	}

	@Override
	public JspFile getJspxFile()
	{
		return (JspFile) getContainingFile().getViewProvider().getPsi(JspLanguage.INSTANCE);
	}

	@Nullable
	@RequiredReadAction
	@Override
	public String getQualifiedName()
	{
		PsiClassStub<JspClass> stub = getGreenStub();
		if(stub != null)
		{
			return stub.getQualifiedName();
		}

		JspJavaFileImpl file = (JspJavaFileImpl) getContainingFile();
		return StringUtil.getQualifiedName(file.getPackageName(), getName());
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

	@Nonnull
	@Override
	public PsiClassType[] getExtendsListTypes()
	{
		return PsiClassImplUtil.getExtendsListTypes(this);
	}

	@Nonnull
	@Override
	public PsiClassType[] getImplementsListTypes()
	{
		return PsiClassImplUtil.getImplementsListTypes(this);
	}

	@Nullable
	@Override
	public PsiClass getSuperClass()
	{
		return PsiClassImplUtil.getSuperClass(this);
	}

	@Override
	public PsiClass[] getInterfaces()
	{
		return PsiClassImplUtil.getInterfaces(this);
	}

	@Nonnull
	@Override
	public PsiClass[] getSupers()
	{
		return PsiClassImplUtil.getSupers(this);
	}

	@Nonnull
	@Override
	public PsiClassType[] getSuperTypes()
	{
		return PsiClassImplUtil.getSuperTypes(this);
	}

	@Nonnull
	@Override
	public PsiField[] getFields()
	{
		return myInnersCache.getFields();
	}

	@Nonnull
	@Override
	public PsiMethod[] getMethods()
	{
		return myInnersCache.getMethods();
	}

	@Nonnull
	@Override
	public List<PsiMethod> getOwnMethods()
	{
		List<PsiMethod> methods = new ArrayList<>();
		PsiStatement[] statements = getHolderMethod().getBody().getStatements();
		for(PsiStatement statement : statements)
		{
			if(statement instanceof JspClassLevelDeclarationStatement)
			{
				statement.acceptChildren(new JavaElementVisitor()
				{
					@Override
					public void visitMethod(PsiMethod method)
					{
						methods.add(method);
					}
				});
			}
		}
		return methods;
	}

	@Nonnull
	@Override
	@RequiredReadAction
	public List<PsiField> getOwnFields()
	{
		List<PsiField> fields = buildImplicitFields();
		PsiStatement[] statements = getHolderMethod().getBody().getStatements();
		for(PsiStatement statement : statements)
		{
			if(statement instanceof JspClassLevelDeclarationStatement)
			{
				statement.acceptChildren(new JavaElementVisitor()
				{
					@Override
					public void visitField(PsiField field)
					{
						fields.add(field);
					}
				});
			}
		}
		return fields;
	}

	@Nonnull
	@Override
	@RequiredReadAction
	public List<PsiClass> getOwnInnerClasses()
	{
		List<PsiClass> classes = new ArrayList<>();

		PsiStatement[] statements = getHolderMethod().getBody().getStatements();
		for(PsiStatement statement : statements)
		{
			if(statement instanceof JspClassLevelDeclarationStatement)
			{
				statement.acceptChildren(new JavaElementVisitor()
				{
					@Override
					public void visitClass(PsiClass aClass)
					{
						classes.add(aClass);
					}
				});
			}
		}
		return classes;
	}

	@Nonnull
	@Override
	public PsiMethod[] getConstructors()
	{
		return new PsiMethod[]{new LightMethodBuilder(this, JavaLanguage.INSTANCE).setConstructor(true)};
	}

	@Nonnull
	@Override
	public PsiClass[] getInnerClasses()
	{
		return myInnersCache.getInnerClasses();
	}

	@Nonnull
	@Override
	public PsiClassInitializer[] getInitializers()
	{
		return PsiClassInitializer.EMPTY_ARRAY;
	}

	@Nonnull
	@Override
	public PsiField[] getAllFields()
	{
		return PsiClassImplUtil.getAllFields(this);
	}

	@Nonnull
	@Override
	public PsiMethod[] getAllMethods()
	{
		return PsiClassImplUtil.getAllMethods(this);
	}

	@Nonnull
	@Override
	public PsiClass[] getAllInnerClasses()
	{
		return PsiClassImplUtil.getAllInnerClasses(this);
	}

	@Nullable
	@Override
	public PsiField findFieldByName(String name, boolean checkBases)
	{
		return myInnersCache.findFieldByName(name, checkBases);
	}

	@Nullable
	@Override
	public PsiMethod findMethodBySignature(PsiMethod psiMethod, boolean checkBases)
	{
		return null;
	}

	@Nonnull
	@Override
	public PsiMethod[] findMethodsBySignature(PsiMethod psiMethod, boolean checkBases)
	{
		return PsiMethod.EMPTY_ARRAY;
	}

	@Nonnull
	@Override
	public PsiMethod[] findMethodsByName(String name, boolean checkBases)
	{
		return myInnersCache.findMethodsByName(name, checkBases);
	}

	@Nonnull
	@Override
	public List<Pair<PsiMethod, PsiSubstitutor>> findMethodsAndTheirSubstitutorsByName(String s, boolean b)
	{
		return Collections.emptyList();
	}

	@Nonnull
	@Override
	public List<Pair<PsiMethod, PsiSubstitutor>> getAllMethodsAndTheirSubstitutors()
	{
		return Collections.emptyList();
	}

	@Nullable
	@Override
	public PsiClass findInnerClassByName(@NonNls String name, boolean checkBases)
	{
		return myInnersCache.findInnerClassByName(name, checkBases);
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
		ASTNode treeElement = getNode();

		for(ASTNode parent = treeElement.getTreeParent(); parent != null; parent = parent.getTreeParent())
		{
			if(parent.getElementType() instanceof IStubElementType)
			{
				return parent.getPsi();
			}
		}

		return this.getContainingFile();
	}

	@Override
	public boolean isInheritor(@Nonnull PsiClass baseClass, boolean checkDeep)
	{
		return InheritanceImplUtil.isInheritor(this, baseClass, checkDeep);
	}

	@Override
	public boolean isInheritorDeep(PsiClass baseClass, PsiClass classToByPass)
	{
		return InheritanceImplUtil.isInheritorDeep(this, baseClass, classToByPass);
	}

	@Nullable
	@Override
	public PsiClass getContainingClass()
	{
		return null;
	}

	@Nonnull
	@Override
	public Collection<HierarchicalMethodSignature> getVisibleSignatures()
	{
		return Collections.emptyList();
	}

	@Override
	public PsiElement setName(@NonNls @Nonnull String s)
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

	@Nonnull
	@Override
	public PsiTypeParameter[] getTypeParameters()
	{
		return PsiTypeParameter.EMPTY_ARRAY;
	}

	@Nullable
	@Override
	public PsiModifierList getModifierList()
	{
		return null;
	}

	@Override
	public boolean hasModifierProperty(@PsiModifier.ModifierConstant @NonNls @Nonnull String modifier)
	{
		return PsiModifier.STATIC.equals(modifier);
	}

	@Override
	public ItemPresentation getPresentation()
	{
		return ItemPresentationProviders.getItemPresentation(this);
	}

	@Override
	public boolean isEquivalentTo(final PsiElement another)
	{
		return Comparing.equal(another.getContainingFile(), getContainingFile());
	}

	@Override
	@Nonnull
	public SearchScope getUseScope()
	{
		return GlobalSearchScope.fileScope(getContainingFile());
	}

	@Override
	@RequiredReadAction
	public boolean processDeclarations(@Nonnull PsiScopeProcessor processor, @Nonnull ResolveState state, PsiElement lastParent, @Nonnull PsiElement place)
	{
		LanguageLevel level = PsiUtil.getLanguageLevel(place);
		return PsiClassImplUtil.processDeclarationsInClass(this, processor, state, null, lastParent, place, level, false);
	}

	@Override
	public void accept(@Nonnull PsiElementVisitor visitor)
	{
		if(visitor instanceof JavaElementVisitor)
		{
			((JavaElementVisitor) visitor).visitClass(this);
		}
		else
		{
			visitor.visitElement(this);
		}
	}
}
