package com.intellij.javaee.facet;

import com.intellij.jam.model.common.CommonModelElement;
import consulo.javaee.module.extension.JavaEEModuleExtension;
import consulo.language.psi.PsiElement;
import consulo.language.util.ModuleUtilCore;
import consulo.module.Module;
import consulo.project.Project;
import consulo.virtualFileSystem.VirtualFile;
import consulo.xml.util.xml.ConvertContext;

import jakarta.annotation.Nullable;

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
