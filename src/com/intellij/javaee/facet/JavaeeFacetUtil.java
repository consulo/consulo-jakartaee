package com.intellij.javaee.facet;

import org.jetbrains.annotations.Nullable;
import com.intellij.jam.model.common.CommonModelElement;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.util.xml.ConvertContext;
import consulo.javaee.module.extension.JavaEEModuleExtension;

/**
 * @author VISTALL
 * @since 02-Jul-17
 */
public class JavaeeFacetUtil
{
	private static final JavaeeFacetUtil ourInstance = new JavaeeFacetUtil();

	public static JavaeeFacetUtil getInstance()
	{
		return ourInstance;
	}

	@Nullable
	public JavaEEModuleExtension<?> getJavaeeFacet(ConvertContext context)
	{
		return ModuleUtilCore.getExtension(context.getFile(), JavaEEModuleExtension.class);
	}

	@Nullable
	public <T extends JavaEEModuleExtension<T>> T getJavaeeFacet(PsiElement element, Class<T> clazz)
	{
		return ModuleUtilCore.getExtension(element, clazz);
	}

	@Nullable
	public <T extends JavaEEModuleExtension<T>> T getJavaeeFacet(CommonModelElement element, Class<T> clazz)
	{
		Module module = element.getModule();
		if(module == null)
		{
			return null;
		}
		return ModuleUtilCore.getExtension(module, clazz);
	}

	@Nullable
	public <T extends JavaEEModuleExtension<T>> T getJavaeeFacet(ConvertContext context, Class<T> clazz)
	{
		Module module = context.getModule();
		if(module == null)
		{
			return null;
		}
		return ModuleUtilCore.getExtension(module, clazz);
	}

	@Nullable
	public <T extends JavaEEModuleExtension<T>> T getJavaeeFacet(VirtualFile virtualFile, Class<T> clazz, Project project)
	{
		return ModuleUtilCore.getExtension(project, virtualFile, clazz);
	}
}
