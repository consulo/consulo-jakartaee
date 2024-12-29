package consulo.jsp.impl.language.psi.java.psi.stub;

import com.intellij.java.language.impl.psi.impl.source.tree.ChildRole;
import com.intellij.java.language.impl.psi.impl.source.tree.JavaElementType;
import com.intellij.java.language.psi.PsiJavaFile;
import consulo.language.ast.ASTNode;
import consulo.language.ast.ChildRoleBase;
import consulo.language.ast.IElementType;
import consulo.language.ast.TokenType;
import consulo.language.impl.ast.FileElement;
import consulo.language.impl.ast.TreeElement;
import consulo.language.impl.psi.SourceTreeToPsiMap;
import consulo.logging.Logger;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * @author VISTALL
 * @since 15-Jul-17
 */
public class JspJavaFileElement extends FileElement
{
	private static final Logger LOG = Logger.getInstance(JspJavaFileElement.class);

	public JspJavaFileElement(IElementType elementType, CharSequence text)
	{
		super(elementType, text);
	}

	@Override
	public void deleteChildInternal(@Nonnull ASTNode child)
	{
		if(child.getElementType() == JavaElementType.CLASS)
		{
			PsiJavaFile file = SourceTreeToPsiMap.treeToPsiNotNull(this);
			if(file.getClasses().length == 1)
			{
				file.delete();
				return;
			}
		}
		super.deleteChildInternal(child);
	}

	@Override
	@Nullable
	public ASTNode findChildByRole(int role)
	{
		LOG.assertTrue(ChildRole.isUnique(role));
		switch(role)
		{
			default:
				return null;

			case ChildRole.PACKAGE_STATEMENT:
				return findChildByType(JavaElementType.PACKAGE_STATEMENT);

			case ChildRole.IMPORT_LIST:
				return findChildByType(JavaElementType.IMPORT_LIST);
		}
	}

	@Override
	public int getChildRole(ASTNode child)
	{
		LOG.assertTrue(child.getTreeParent() == this);
		IElementType i = child.getElementType();
		if(i == JavaElementType.PACKAGE_STATEMENT)
		{
			return ChildRole.PACKAGE_STATEMENT;
		}
		else if(i == JavaElementType.IMPORT_LIST)
		{
			return ChildRole.IMPORT_LIST;
		}
		else if(i == JavaElementType.CLASS)
		{
			return ChildRole.CLASS;
		}
		else
		{
			return ChildRoleBase.NONE;
		}
	}

	@Override
	public void replaceChildInternal(@Nonnull ASTNode child, @Nonnull TreeElement newElement)
	{
		if(newElement.getElementType() == JavaElementType.IMPORT_LIST)
		{
			LOG.assertTrue(child.getElementType() == JavaElementType.IMPORT_LIST);
			if(newElement.getFirstChildNode() == null)
			{ //empty import list
				ASTNode next = child.getTreeNext();
				if(next != null && next.getElementType() == TokenType.WHITE_SPACE)
				{
					removeChild(next);
				}
			}
		}
		super.replaceChildInternal(child, newElement);
	}
}