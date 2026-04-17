/**
 * @author VISTALL
 * @since 22/01/2023
 */
module consulo.jakartaee
{
	requires consulo.ide.api;
	requires consulo.compiler.artifact.api;
	requires com.intellij.xml;

	exports consulo.jakartaee.impl.artifact;
}