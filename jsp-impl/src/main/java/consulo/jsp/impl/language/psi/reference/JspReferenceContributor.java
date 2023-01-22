package consulo.jsp.impl.language.psi.reference;

import consulo.annotation.component.ExtensionImpl;
import consulo.content.base.WebResourcesFolderTypeProvider;
import consulo.jsp.language.JspLanguage;
import consulo.jsp.language.psi.xml.JspTag;
import consulo.language.Language;
import consulo.language.content.LanguageContentFolderScopes;
import consulo.language.psi.*;
import consulo.language.psi.path.FileReferenceSet;
import consulo.language.psi.util.PsiTreeUtil;
import consulo.language.util.ModuleUtilCore;
import consulo.language.util.ProcessingContext;
import consulo.module.Module;
import consulo.module.content.ModuleRootManager;
import consulo.module.content.layer.ContentFolder;
import consulo.xml.patterns.XmlPatterns;
import consulo.xml.psi.xml.XmlAttribute;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author VISTALL
 * @since 20-Jun-17
 */
@ExtensionImpl
public class JspReferenceContributor extends PsiReferenceContributor
{
	@Override
	public void registerReferenceProviders(PsiReferenceRegistrar registrar)
	{
		registrar.registerReferenceProvider(XmlPatterns.xmlAttributeValue(), new PsiReferenceProvider()
		{
			@Nonnull
			@Override
			public PsiReference[] getReferencesByElement(@Nonnull PsiElement element, @Nonnull ProcessingContext context)
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

						ContentFolder[] contentFolders = ModuleRootManager.getInstance(module).getContentFolders(LanguageContentFolderScopes.of(WebResourcesFolderTypeProvider.getInstance()));
						PsiManager psiManager = PsiManager.getInstance(element.getProject());
						return Arrays.stream(contentFolders).map(contentFolder -> psiManager.findDirectory(contentFolder.getFile())).filter(Objects::nonNull).collect(Collectors.toList());
					});
					return set.getAllReferences();
				}
				return PsiReference.EMPTY_ARRAY;
			}
		});
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return JspLanguage.INSTANCE;
	}
}
