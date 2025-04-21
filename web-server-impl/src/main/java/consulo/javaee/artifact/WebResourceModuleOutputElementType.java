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

import consulo.annotation.component.ExtensionImpl;
import consulo.compiler.artifact.element.ModuleOutputElementTypeBase;
import consulo.compiler.artifact.element.ModuleOutputPackagingElementImpl;
import consulo.component.util.pointer.NamedPointer;
import consulo.content.base.WebResourcesFolderTypeProvider;
import consulo.module.Module;
import consulo.project.Project;

import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 2013-11-07
 */
@ExtensionImpl
public class WebResourceModuleOutputElementType extends ModuleOutputElementTypeBase {
    public static WebResourceModuleOutputElementType getInstance() {
        return getInstance(WebResourceModuleOutputElementType.class);
    }

    public WebResourceModuleOutputElementType() {
        super("java-web-resource", WebResourcesFolderTypeProvider.getInstance());
    }

    @Override
    public ModuleOutputPackagingElementImpl createElement(@Nonnull Project project, @Nonnull NamedPointer<Module> pointer) {
        return new WebResourceModuleOutputPackagingElement(this, project, pointer, myContentFolderTypeProvider);
    }

    @Nonnull
    @Override
    public ModuleOutputPackagingElementImpl createEmpty(@Nonnull Project project) {
        return new WebResourceModuleOutputPackagingElement(this, project, myContentFolderTypeProvider);
    }
}
