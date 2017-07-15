package com.intellij.javaee.oss.descriptor;

import com.intellij.ide.presentation.PresentationProvider;
import com.intellij.javaee.oss.server.JavaeeIntegration;

/**
 * @author VISTALL
 * @since 03-Jul-17
 */
public abstract class JavaeePresentationProvider extends PresentationProvider
{
	protected abstract JavaeeIntegration getIntegration();
}
