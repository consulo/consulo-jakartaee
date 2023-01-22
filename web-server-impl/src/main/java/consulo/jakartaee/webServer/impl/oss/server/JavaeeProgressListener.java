/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.server;

class JavaeeProgressListener //implements ProgressListener
{

	//private final ProgressObject progress;

	private boolean running;

	@SuppressWarnings({"FieldAccessedSynchronizedAndUnsynchronized"})
	private String errors;

	/*JavaeeProgressListener(ProgressObject progress)
	{
		this.progress = progress;
		running = progress.getDeploymentStatus().isRunning();
	}

	public void handleProgressEvent(ProgressEvent event)
	{
		synchronized(progress)
		{
			running = event.getDeploymentStatus().isRunning();
			if(event.getDeploymentStatus().isFailed())
			{
				errors = event.getDeploymentStatus().getMessage();
			}
			progress.notifyAll();
		}
	}  */

	/*void waitForCompletion() throws Exception
	{
		try
		{
			synchronized(progress)
			{
				while(running)
				{
					progress.wait(20000);
				}
			}
		}
		catch(InterruptedException ignore)
		{
		}
		if(errors != null)
		{
			throw new Exception(errors);
		}
	}   */
}
