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
import consulo.application.Application;
import consulo.compiler.artifact.ArtifactTemplate;
import consulo.compiler.artifact.ArtifactType;
import consulo.compiler.artifact.element.ArtifactRootElementImpl;
import consulo.compiler.artifact.element.CompositePackagingElement;
import consulo.compiler.artifact.element.PackagingElementFactory;
import consulo.compiler.artifact.element.PackagingElementResolvingContext;
import consulo.jakarta.localize.JakartaLocalize;
import consulo.jakartaee.webServer.impl.ui.packaging.WebApplicationArtifactType;
import jakarta.annotation.Nonnull;

import java.util.Collections;
import java.util.List;

/**
 * @author VISTALL
 * @since 2013-11-07
 */
@ExtensionImpl
public class ExplodedWarArtifactType extends WebApplicationArtifactType {
    @Nonnull
    public static ExplodedWarArtifactType getInstance() {
        return Application.get().getExtensionPoint(ArtifactType.class).findExtensionOrFail(ExplodedWarArtifactType.class);
    }

    public ExplodedWarArtifactType() {
        super("exploded-war", JakartaLocalize.explodedWarArtifactName());
    }

    @Nonnull
    @Override
    public List<? extends ArtifactTemplate> getNewArtifactTemplates(@Nonnull PackagingElementResolvingContext context) {
        return Collections.singletonList(new ExplodedWarArtifactTemplate(context));
    }

    @Nonnull
    @Override
    public CompositePackagingElement<?> createRootElement(@Nonnull PackagingElementFactory factory, @Nonnull String s) {
        return new ArtifactRootElementImpl();
    }
}
