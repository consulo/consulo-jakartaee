/*
 * Copyright 2000-2007 JetBrains s.r.o.
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
package com.intellij.javaee.model;

import com.intellij.java.language.psi.*;
import com.intellij.javaee.model.xml.JavaeeDomModelElement;
import com.intellij.javaee.model.xml.ejb.MethodParams;
import consulo.application.util.function.CommonProcessors;
import consulo.application.util.function.Processor;
import consulo.ide.IdeBundle;
import consulo.language.editor.CodeInsightBundle;
import consulo.language.psi.PsiElement;
import consulo.util.collection.ContainerUtil;
import consulo.util.lang.Comparing;
import consulo.util.lang.function.Condition;
import consulo.util.lang.ref.Ref;
import consulo.xml.util.xml.ConvertContext;
import consulo.xml.util.xml.GenericDomValue;
import consulo.xml.util.xml.ResolvingConverter;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.*;
import java.util.function.Function;

/**
 * @author peter
 * @deprecated use consulo.xml.util.xml.converters.AbstractMethodResolveConverter
 */
@Deprecated
public abstract class AbstractMethodResolveConverter<T extends JavaeeDomModelElement> extends ResolvingConverter<PsiMethod>
{
	public static final String ALL_METHODS = "*";
	private final Class<T> myDomMethodClass;

	protected AbstractMethodResolveConverter(final Class<T> domMethodClass)
	{
		myDomMethodClass = domMethodClass;
	}

	@Nonnull
	protected abstract Collection<PsiClass> getPsiClasses(final T parent, final ConvertContext context);

	@Nullable
	protected abstract MethodParams getMethodParams(@Nonnull T parent);

	public void bindReference(final GenericDomValue<PsiMethod> genericValue, final ConvertContext context, final PsiElement element)
	{
		assert element instanceof PsiMethod : "PsiMethod expected";
		final PsiMethod psiMethod = (PsiMethod) element;
		final T parent = getParent(context);
		final MethodParams methodParams = getMethodParams(parent);
		genericValue.setStringValue(psiMethod.getName());
		if(methodParams != null)
		{
			methodParams.undefine();
			for(PsiParameter parameter : psiMethod.getParameterList().getParameters())
			{
				methodParams.addMethodParam().setValue(parameter.getType());
			}
		}
	}

	public String getErrorMessage(final String s, final ConvertContext context)
	{
		final T t = getParent(context);
		return CodeInsightBundle.message("error.cannot.resolve.0.1", IdeBundle.message("element.method"), getReferenceCanonicalText(s, getMethodParams(t)));
	}

	@Nonnull
	protected final T getParent(final ConvertContext context)
	{
		return context.getInvocationElement().getParentOfType(myDomMethodClass, true);
	}

	public boolean isReferenceTo(@Nonnull final PsiElement element, final String stringValue, final PsiMethod resolveResult,
								 final ConvertContext context)
	{
		if(super.isReferenceTo(element, stringValue, resolveResult, context))
		{
			return true;
		}

		final Ref<Boolean> result = new Ref<Boolean>(Boolean.FALSE);
		processMethods(context, new Processor<PsiMethod>()
		{
			public boolean process(final PsiMethod method)
			{
				if(method.equals(element))
				{
					result.set(Boolean.TRUE);
					return false;
				}
				return true;
			}
		}, s -> s.findMethodsByName(stringValue, true));

		return result.get();
	}

	protected void processMethods(final ConvertContext context, Processor<PsiMethod> processor, Function<PsiClass, PsiMethod[]> methodGetter)
	{
		for(PsiClass psiClass : getPsiClasses(getParent(context), context))
		{
			if(psiClass != null)
			{
				for(PsiMethod psiMethod : methodGetter.apply(psiClass))
				{
					if(!processor.process(psiMethod))
					{
						return;
					}
				}
			}
		}
	}

	@Nonnull
	public Collection<? extends PsiMethod> getVariants(final ConvertContext context)
	{
		LinkedHashSet<PsiMethod> methodList = new LinkedHashSet<PsiMethod>();
		processMethods(context, new CommonProcessors.CollectProcessor<PsiMethod>(methodList), s -> {
			final List<PsiMethod> list = ContainerUtil.findAll(AbstractMethodResolveConverter.this.getVariants(s), new Condition<PsiMethod>()
			{
				public boolean value(final PsiMethod object)
				{
					return methodSuits(object);
				}
			});
			return list.toArray(new PsiMethod[list.size()]);
		});
		return methodList;
	}

	protected Collection<PsiMethod> getVariants(final PsiClass s)
	{
		return Arrays.asList(s.getAllMethods());
	}

	public static boolean methodSuits(final PsiMethod psiMethod)
	{
		if(psiMethod.isConstructor())
		{
			return false;
		}
		return psiMethod.getContainingClass().isInterface() || (psiMethod.hasModifierProperty(PsiModifier.PUBLIC) &&
				!psiMethod.hasModifierProperty(PsiModifier.STATIC));
	}

	public Set<String> getAdditionalVariants()
	{
		return Collections.singleton(ALL_METHODS);
	}

	public PsiMethod fromString(final String methodName, final ConvertContext context)
	{
		final CommonProcessors.FindFirstProcessor<PsiMethod> processor = new CommonProcessors.FindFirstProcessor<PsiMethod>();
		processMethods(context, processor, s ->
		{
			final PsiMethod method = findMethod(s, methodName, getMethodParams(getParent(context)));
			if(method != null && methodSuits(method))
			{
				return new PsiMethod[]{method};
			}
			return PsiMethod.EMPTY_ARRAY;
		});
		if(processor.isFound())
		{
			return processor.getFoundValue();
		}

		processMethods(context, processor, s -> s.findMethodsByName(methodName, true));
		return processor.getFoundValue();
	}

	public String toString(final PsiMethod method, final ConvertContext context)
	{
		return method.getName();
	}

	public static String getReferenceCanonicalText(final String name, @Nullable final MethodParams methodParams)
	{
		StringBuilder sb = new StringBuilder(name);
		if(methodParams == null)
		{
			sb.append("()");
		}
		else if(methodParams.getXmlTag() != null)
		{
			sb.append("(");
			final List<GenericDomValue<PsiType>> list = methodParams.getMethodParams();
			boolean first = true;
			for(GenericDomValue<PsiType> value : list)
			{
				if(first)
				{
					first = false;
				}
				else
				{
					sb.append(", ");
				}
				sb.append(value.getStringValue());
			}
			sb.append(")");
		}
		return sb.toString();
	}

	@Nullable
	public static PsiMethod findMethod(final PsiClass psiClass, final String methodName, @Nullable final MethodParams methodParameters)
	{
		if(psiClass == null || methodName == null)
		{
			return null;
		}
		return ContainerUtil.find(psiClass.findMethodsByName(methodName, true), new Condition<PsiMethod>()
		{
			public boolean value(final PsiMethod object)
			{
				return methodParamsMatchSignature(methodParameters, object);
			}
		});
	}

	public static boolean methodParamsMatchSignature(@Nullable final MethodParams params, final PsiMethod psiMethod)
	{
		if(params != null && params.getXmlTag() == null)
		{
			return true;
		}

		PsiParameter[] parameters = psiMethod.getParameterList().getParameters();
		if(params == null)
		{
			return parameters.length == 0;
		}

		List<GenericDomValue<PsiType>> methodParams = params.getMethodParams();
		if(methodParams.size() != parameters.length)
		{
			return false;
		}
		for(int i = 0; i < parameters.length; i++)
		{
			if(!Comparing.equal(parameters[i].getType(), methodParams.get(i).getValue()))
			{
				return false;
			}
		}
		return true;
	}

}
