package com.intellij.javaee;

import org.jetbrains.annotations.NotNull;

/**
 * @author Gregory.Shrago
 */
public enum JavaeeVersion
{
	J2EE_1_4("1.4", "J2EE 1.4"),
	JAVAEE_5("5", "Java EE 5"),
	JAVAEE_6("6", "Java EE 6"),
	JAVAEE_7("7", "Java EE 7");
	private final String myId;
	private final String myPresentableName;

	JavaeeVersion(@NotNull String id, @NotNull String presentableName)
	{
		myId = id;
		myPresentableName = presentableName;
	}

	@NotNull
	public String getId()
	{
		return myId;
	}

	@NotNull
	public String getPresentableName()
	{
		return myPresentableName;
	}

	public String getVersionNumber()
	{
		return getId();
	}
}
