/*
 * Copyright 2013 must-be.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package consulo.javaee.artifact;

import java.util.ArrayList;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.roots.LibraryOrderEntry;
import com.intellij.openapi.roots.ModuleOrderEntry;
import com.intellij.openapi.roots.ModuleRootModel;
import com.intellij.openapi.roots.RootPolicy;
import com.intellij.openapi.roots.impl.ModuleLibraryTable;
import com.intellij.openapi.roots.impl.libraries.LibraryTableImplUtil;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.libraries.LibraryTable;
import com.intellij.openapi.roots.ui.configuration.ChooseModulesDialog;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.packaging.artifacts.ArtifactTemplate;
import com.intellij.packaging.elements.PackagingElementResolvingContext;
import com.intellij.packaging.impl.elements.DirectoryElementType;
import com.intellij.packaging.impl.elements.LibraryElementType;
import com.intellij.packaging.impl.elements.LibraryPackagingElement;
import com.intellij.packaging.impl.elements.moduleContent.ProductionModuleOutputElementType;
import com.intellij.util.containers.ArrayListSet;
import consulo.javaee.JavaWebConstants;
import consulo.javaee.module.extension.JavaWebModuleExtension;
import consulo.packaging.impl.elements.ZipArchiveElementType;
import consulo.packaging.impl.elements.ZipArchivePackagingElement;
import consulo.packaging.impl.elements.moduleContent.ProductionResourceModuleOutputElementType;
import consulo.roots.ContentFolderScopes;
import consulo.roots.impl.ProductionContentFolderTypeProvider;
import consulo.roots.impl.ProductionResourceContentFolderTypeProvider;
import consulo.roots.impl.WebResourcesFolderTypeProvider;
import lombok.val;

/**
 * @author VISTALL
 * @since 07.11.13.
 */
public class ExplodedWarArtifactTemplate extends ArtifactTemplate
{
	private final PackagingElementResolvingContext myContext;

	public ExplodedWarArtifactTemplate(PackagingElementResolvingContext context)
	{
		myContext = context;
	}

	@NotNull
	public static NewArtifactConfiguration doCreateArtifactTemplate(Module module, PackagingElementResolvingContext packagingElementResolvingContext)
	{
		ModulesProvider modulesProvider = packagingElementResolvingContext.getModulesProvider();

		val project = module.getProject();
		val root = ExplodedWarArtifactType.getInstance().createRootElement(module.getName());

		val webInfDir = DirectoryElementType.getInstance().createEmpty(project);
		webInfDir.setDirectoryName(JavaWebConstants.WEB_INF);
		root.addFirstChild(webInfDir);

		val libDir = DirectoryElementType.getInstance().createEmpty(project);
		libDir.setDirectoryName("lib");
		webInfDir.addFirstChild(libDir);

		Set<Library> libraries = new ArrayListSet<Library>();
		Set<Module> modules = new ArrayListSet<Module>();

		collectInfo(modules, libraries, modulesProvider, module);

		for(Module toAddModule : modules)
		{
			val pointer = ModuleUtilCore.createPointer(toAddModule);
			ModuleRootModel rootModel = modulesProvider.getRootModel(toAddModule);

			ZipArchivePackagingElement zipArchivePackagingElement = ZipArchiveElementType.getInstance().createEmpty(project);
			zipArchivePackagingElement.setArchiveFileName(toAddModule.getName() + ".jar");

			if(rootModel.getContentFolders(ContentFolderScopes.of(ProductionContentFolderTypeProvider.getInstance())).length > 0)
			{
				zipArchivePackagingElement.addFirstChild(ProductionModuleOutputElementType.getInstance().createElement(project, pointer));
			}

			if(rootModel.getContentFolders(ContentFolderScopes.of(ProductionResourceContentFolderTypeProvider.getInstance())).length > 0)
			{
				zipArchivePackagingElement.addFirstChild(ProductionResourceModuleOutputElementType.getInstance().createElement(project, pointer));
			}

			libDir.addFirstChild(zipArchivePackagingElement);

			if(rootModel.getContentFolders(ContentFolderScopes.of(WebResourcesFolderTypeProvider.getInstance())).length > 0)
			{
				root.addFirstChild(WebResourceModuleOutputElementType.getInstance().createElement(project, pointer));
			}
		}

		for(Library library : libraries)
		{
			LibraryPackagingElement libraryPackagingElement = LibraryElementType.getInstance().createEmpty(project);
			libraryPackagingElement.setLibraryName(library.getName());

			LibraryTable table = library.getTable();

			String tableLevel = table == null ? null : table.getTableLevel();
			libraryPackagingElement.setLevel(tableLevel);

			if(LibraryTableImplUtil.MODULE_LEVEL.equals(tableLevel))
			{
				libraryPackagingElement.setModuleName(((ModuleLibraryTable) table).getModule().getName());
			}

			libDir.addFirstChild(libraryPackagingElement);
		}

		return new NewArtifactConfiguration(root, ExplodedWarArtifactType.getInstance().getPresentableName() + ": " + module.getName(), ExplodedWarArtifactType.getInstance());
	}

	private static void collectInfo(final Set<Module> modules, final Set<Library> libraries, final ModulesProvider modulesProvider, Module module)
	{
		modules.add(module);

		ModuleRootModel rootModel = modulesProvider.getRootModel(module);

		rootModel.orderEntries().withoutSdk().runtimeOnly().process(new RootPolicy<Object>()
		{
			@Override
			public Object visitLibraryOrderEntry(LibraryOrderEntry libraryOrderEntry, Object value)
			{
				Library library = libraryOrderEntry.getLibrary();
				if(library == null)
				{
					return null;
				}
				libraries.add(library);
				return null;
			}

			@Override
			public Object visitModuleOrderEntry(ModuleOrderEntry moduleOrderEntry, Object value)
			{
				Module moduleDependency = moduleOrderEntry.getModule();
				if(moduleDependency == null)
				{
					return null;
				}
				if(!modules.contains(moduleDependency))
				{
					collectInfo(modules, libraries, modulesProvider, moduleDependency);
				}
				return null;
			}
		}, null);
	}

	@Nullable
	@Override
	public NewArtifactConfiguration createArtifact()
	{
		val modules = new ArrayList<Module>();
		for(Module module : myContext.getModulesProvider().getModules())
		{
			if(ModuleUtilCore.getExtension(module, JavaWebModuleExtension.class) != null)
			{
				modules.add(module);
			}
		}

		val dialog = new ChooseModulesDialog(myContext.getProject(), modules, "Choose Module", "Choose Module For Artifact Creation");
		dialog.setSingleSelectionMode();
		val selectedModules = dialog.showAndGetResult();
		if(selectedModules.size() != 1)
		{
			return null;
		}
		return doCreateArtifactTemplate(modules.get(0), myContext);
	}

	@Override
	public String getPresentableName()
	{
		return "From Module";
	}
}
