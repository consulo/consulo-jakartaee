package consulo.jakartaee.webServer.impl.run.localRun;

import consulo.process.ExecutionException;
import consulo.process.ProcessHandler;
import consulo.process.ProcessHandlerBuilder;
import consulo.process.cmd.GeneralCommandLine;
import consulo.process.cmd.ParametersList;
import consulo.util.collection.ArrayUtil;
import org.jetbrains.annotations.NonNls;

import jakarta.annotation.Nonnull;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author VISTALL
 * @since 03-Jul-17
 */
public abstract class ColoredCommandLineExecutableObject implements ExecutableObject
{
	private final String[] myParameters;
	@NonNls
	private static final String CLASSPATH_VAR_NAME = "CLASSPATH";

	public ColoredCommandLineExecutableObject(@NonNls String[] parameters, @NonNls String programParameters)
	{
		myParameters = getParameters(parameters, programParameters);
	}

	@Override
	public String getDisplayString()
	{
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < myParameters.length; i++)
		{
			if(i > 0)
			{
				result.append(" ");
			}
			String parameter = myParameters[i];
			result.append(parameter);
		}
		return result.toString();
	}

	protected GeneralCommandLine createCommandLine(final String[] parameters, final Map<String, String> envVariables)
	{
		return ScriptUtil.createCommandLine(parameters);
	}

	@Override
	public ProcessHandler createProcessHandler(String workingDirectory, Map<String, String> envVariables) throws ExecutionException
	{
		GeneralCommandLine commandLine = createCommandLine(myParameters, envVariables);
		if(workingDirectory == null && myParameters.length > 0)
		{
			File parentFile = new File(myParameters[0]).getParentFile();
			if(parentFile != null)
			{
				workingDirectory = parentFile.getAbsolutePath();
			}
		}
		commandLine.withWorkDirectory(workingDirectory);
		commandLine.withEnvironment(CLASSPATH_VAR_NAME, "");
		commandLine.withEnvironment(envVariables);
		return createProcessHandler(commandLine);
	}

	@Nonnull
	protected ProcessHandler createProcessHandler(GeneralCommandLine commandLine) throws ExecutionException
	{
		return ProcessHandlerBuilder.create(commandLine).build();
	}

	public String[] getParameters()
	{
		return myParameters;
	}

	private static String[] getParameters(String[] parameters, String programParameters)
	{
		List<String> result = new ArrayList<>();
		for(String parameter : parameters)
		{
			if(parameter != null && (parameter.trim().length() > 0))
			{
				result.add(parameter.trim());
			}
		}
		if(programParameters != null && (programParameters.trim().length() > 0))
		{
			String[] programParametersArray = ParametersList.parse(programParameters);
			for(String s : programParametersArray)
			{
				if(s != null && s.trim().length() > 0)
				{
					result.add(s.trim());
				}
			}

		}
		return ArrayUtil.toStringArray(result);
	}
}
