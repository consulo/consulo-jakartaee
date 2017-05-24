package consulo.javaee.jsp.psi.impl.java.psi;

import gnu.trove.THashSet;

import java.util.Collections;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiJavaToken;
import com.intellij.psi.PsiLocalVariable;
import com.intellij.psi.PsiStatement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.impl.source.jsp.jspJava.JspCodeBlock;
import com.intellij.psi.scope.BaseScopeProcessor;
import com.intellij.psi.scope.ElementClassHint;
import com.intellij.psi.scope.NameHint;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.scope.util.PsiScopesUtil;

/**
 * @author VISTALL
 * @since 24-May-17
 */
public class JspCodeBlockImpl extends ASTWrapperPsiElement implements JspCodeBlock
{
	public JspCodeBlockImpl(@NotNull ASTNode node)
	{
		super(node);
	}

	@Override
	public PsiElement[] getLocalDeclarations()
	{
		return new PsiElement[0];
	}

	@NotNull
	@Override
	public PsiStatement[] getStatements()
	{
		return findChildrenByClass(PsiStatement.class);
	}

	@Nullable
	@Override
	public PsiElement getFirstBodyElement()
	{
		return null;
	}

	@Nullable
	@Override
	public PsiElement getLastBodyElement()
	{
		return null;
	}

	@Nullable
	@Override
	public PsiJavaToken getLBrace()
	{
		return null;
	}

	@Nullable
	@Override
	public PsiJavaToken getRBrace()
	{
		return null;
	}

	@Override
	public boolean shouldChangeModificationCount(PsiElement place)
	{
		return false;
	}

	private volatile Set<String> myVariablesSet = null;
	private volatile Set<String> myClassesSet = null;
	private volatile boolean myConflict = false;

	// return Pair(classes, locals) or null if there was conflict
	@Nullable
	private Pair<Set<String>, Set<String>> buildMaps()
	{
		Set<String> set1 = myClassesSet;
		Set<String> set2 = myVariablesSet;
		boolean wasConflict = myConflict;
		if(set1 == null || set2 == null)
		{
			final Set<String> localsSet = new THashSet<>();
			final Set<String> classesSet = new THashSet<>();
			final Ref<Boolean> conflict = new Ref<>(Boolean.FALSE);
			PsiScopesUtil.walkChildrenScopes(this, new BaseScopeProcessor()
			{
				@Override
				public boolean execute(@NotNull PsiElement element, ResolveState state)
				{
					if(element instanceof PsiLocalVariable)
					{
						final PsiLocalVariable variable = (PsiLocalVariable) element;
						final String name = variable.getName();
						if(!localsSet.add(name))
						{
							conflict.set(Boolean.TRUE);
							localsSet.clear();
							classesSet.clear();
						}
					}
					else if(element instanceof PsiClass)
					{
						final PsiClass psiClass = (PsiClass) element;
						final String name = psiClass.getName();
						if(!classesSet.add(name))
						{
							conflict.set(Boolean.TRUE);
							localsSet.clear();
							classesSet.clear();
						}
					}
					return !conflict.get();
				}
			}, ResolveState.initial(), this, this);

			myClassesSet = set1 = classesSet.isEmpty() ? Collections.<String>emptySet() : classesSet;
			myVariablesSet = set2 = localsSet.isEmpty() ? Collections.<String>emptySet() : localsSet;
			myConflict = wasConflict = conflict.get();
		}
		return wasConflict ? null : Pair.create(set1, set2);
	}

	@Override
	public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement place)
	{
		processor.handleEvent(PsiScopeProcessor.Event.SET_DECLARATION_HOLDER, this);
		if(lastParent == null)
		{
			// Parent element should not see our vars
			return true;
		}
		Pair<Set<String>, Set<String>> pair = buildMaps();
		boolean conflict = pair == null;
		final Set<String> classesSet = conflict ? null : pair.getFirst();
		final Set<String> variablesSet = conflict ? null : pair.getSecond();
		final NameHint hint = processor.getHint(NameHint.KEY);
		if(hint != null && !conflict)
		{
			final ElementClassHint elementClassHint = processor.getHint(ElementClassHint.KEY);
			final String name = hint.getName(state);
			if((elementClassHint == null || elementClassHint.shouldProcess(ElementClassHint.DeclarationKind.CLASS)) && classesSet.contains(name))
			{
				return PsiScopesUtil.walkChildrenScopes(this, processor, state, lastParent, place);
			}
			if((elementClassHint == null || elementClassHint.shouldProcess(ElementClassHint.DeclarationKind.VARIABLE)) && variablesSet.contains(name))
			{
				return PsiScopesUtil.walkChildrenScopes(this, processor, state, lastParent, place);
			}
		}
		else
		{
			return PsiScopesUtil.walkChildrenScopes(this, processor, state, lastParent, place);
		}
		return true;
	}

}
