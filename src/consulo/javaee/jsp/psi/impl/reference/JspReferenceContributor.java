package consulo.javaee.jsp.psi.impl.reference;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.roots.ContentFolder;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.patterns.XmlPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceContributor;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.psi.PsiReferenceRegistrar;
import com.intellij.psi.impl.source.jsp.jspXml.JspTag;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceSet;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.util.ProcessingContext;
import consulo.roots.ContentFolderScopes;
import consulo.roots.impl.WebResourcesFolderTypeProvider;

/**
 * @author VISTALL
 * @since 20-Jun-17
 */
public class JspReferenceContributor extends PsiReferenceContributor
{
	@Override
	public void registerReferenceProviders(PsiReferenceRegistrar registrar)
	{
		registrar.registerReferenceProvider(XmlPatterns.xmlAttributeValue(), new PsiReferenceProvider()
		{
			@NotNull
			@Override
			public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context)
			{
				XmlAttribute xmlAttribute = PsiTreeUtil.getParentOfType(element, XmlAttribute.class);
				if(xmlAttribute == null)
				{
					return PsiReference.EMPTY_ARRAY;
				}
				JspTag jspTag = PsiTreeUtil.getParentOfType(element, JspTag.class);
				if(jspTag == null)
				{
					return PsiReference.EMPTY_ARRAY;
				}

				if("include".equals(jspTag.getName()) && "file".equals(xmlAttribute.getName()))
				{
					FileReferenceSet set = new FileReferenceSet(element);
					set.addCustomization(FileReferenceSet.DEFAULT_PATH_EVALUATOR_OPTION, file ->
					{
						Module module = ModuleUtilCore.findModuleForPsiElement(file);
						if(module == null)
						{
							return Collections.emptyList();
						}

						ContentFolder[] contentFolders = ModuleRootManager.getInstance(module).getContentFolders(ContentFolderScopes.of(WebResourcesFolderTypeProvider.getInstance()));
						PsiManager psiManager = PsiManager.getInstance(element.getProject());
						return Arrays.stream(contentFolders).map(contentFolder -> psiManager.findDirectory(contentFolder.getFile())).filter(Objects::nonNull).collect(Collectors.toList());
					});
					return set.getAllReferences();
				}
				return PsiReference.EMPTY_ARRAY;
			}
		});
	}
}
