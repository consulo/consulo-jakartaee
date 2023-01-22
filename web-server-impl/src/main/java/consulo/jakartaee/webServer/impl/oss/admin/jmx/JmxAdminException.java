package consulo.jakartaee.webServer.impl.oss.admin.jmx;

/**
 * @author VISTALL
 * @since 03-Jul-17
 */
public class JmxAdminException extends Exception
{
	public JmxAdminException()
	{
	}

	public JmxAdminException(String message)
	{
		super(message);
	}

	public JmxAdminException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public JmxAdminException(Throwable cause)
	{
		super(cause);
	}

	public JmxAdminException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
