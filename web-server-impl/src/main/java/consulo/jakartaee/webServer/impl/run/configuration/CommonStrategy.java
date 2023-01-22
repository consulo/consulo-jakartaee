package consulo.jakartaee.webServer.impl.run.configuration;

/**
 * @author VISTALL
 * @since 03-Jul-17
 */
public interface CommonStrategy extends CommonModel, Cloneable
{
	public static class SettingsBean
	{
		public String COMMON_VM_ARGUMENTS;
	}

	public SettingsBean getSettingsBean();
}
