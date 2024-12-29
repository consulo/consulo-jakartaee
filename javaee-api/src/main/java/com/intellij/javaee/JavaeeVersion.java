package com.intellij.javaee;

import jakarta.annotation.Nonnull;

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

	JavaeeVersion(@Nonnull String id, @Nonnull String presentableName)
	{
		myId = id;
		myPresentableName = presentableName;
	}

	@Nonnull
	public String getId()
	{
		return myId;
	}

	@Nonnull
	public String getPresentableName()
	{
		return myPresentableName;
	}

	public String getVersionNumber()
	{
		return getId();
	}
}
