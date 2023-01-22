package consulo.jsp.impl.language.psi;

import consulo.annotation.component.ExtensionImpl;
import consulo.language.psi.stub.ObjectStubSerializerProvider;
import consulo.language.psi.stub.StubElementTypeHolder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author VISTALL
 * @since 22/01/2023
 */
@ExtensionImpl
public class JspStubElementHolder extends StubElementTypeHolder<JspStubElements>
{
	@Nullable
	@Override
	public String getExternalIdPrefix()
	{
		return null;
	}

	@Nonnull
	@Override
	public List<ObjectStubSerializerProvider> loadSerializers()
	{
		return allFromStaticFields(JspStubElements.class, Field::get);
	}
}
