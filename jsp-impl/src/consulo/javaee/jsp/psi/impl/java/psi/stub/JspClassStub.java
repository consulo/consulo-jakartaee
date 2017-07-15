package consulo.javaee.jsp.psi.impl.java.psi.stub;

import org.jetbrains.annotations.Nullable;
import com.intellij.pom.java.LanguageLevel;
import com.intellij.psi.impl.java.stubs.PsiClassStub;
import com.intellij.psi.impl.source.jsp.jspJava.JspClass;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;

/**
 * @author VISTALL
 * @since 22-Jun-17
 */
public class JspClassStub extends StubBase<JspClass> implements PsiClassStub<JspClass>
{
	public JspClassStub(StubElement parent, IStubElementType elementType)
	{
		super(parent, elementType);
	}

	@Nullable
	@Override
	public String getQualifiedName()
	{
		return null;
	}

	@Nullable
	@Override
	public String getBaseClassReferenceText()
	{
		return null;
	}

	@Override
	public boolean isDeprecated()
	{
		return false;
	}

	@Override
	public boolean hasDeprecatedAnnotation()
	{
		return false;
	}

	@Override
	public boolean isInterface()
	{
		return false;
	}

	@Override
	public boolean isEnum()
	{
		return false;
	}

	@Override
	public boolean isEnumConstantInitializer()
	{
		return false;
	}

	@Override
	public boolean isAnonymous()
	{
		return false;
	}

	@Override
	public boolean isAnonymousInQualifiedNew()
	{
		return false;
	}

	@Override
	public boolean isAnnotationType()
	{
		return false;
	}

	@Override
	public LanguageLevel getLanguageLevel()
	{
		return null;
	}

	@Override
	public String getSourceFileName()
	{
		return null;
	}

	@Nullable
	@Override
	public String getName()
	{
		return null;
	}
}
