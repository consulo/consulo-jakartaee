package com.intellij.javaee.ejb;

import com.intellij.java.language.psi.PsiClass;
import com.intellij.java.language.psi.PsiMethod;
import com.intellij.javaee.model.common.ejb.SecurityGroup;
import com.intellij.javaee.model.enums.TransAttribute;
import consulo.xml.util.xml.GenericValue;
import consulo.xml.util.xml.PrimaryKey;

import java.util.List;

/**
 * @author peter
 */
public interface EjbMethod extends SecurityGroup
{
	GenericValue<String> getInit();

	GenericValue<Boolean> isRetainIfException();

	GenericValue<Boolean> isRemoveMethod();

	GenericValue<TransAttribute> getTransactionAttribute();

	GenericValue<Boolean> isExcludeClassInterceptors();

	GenericValue<Boolean> isExcludeDefaultInterceptors();

	List<? extends GenericValue<PsiClass>> getMethodInterceptors();

	GenericValue<Boolean> isTimeoutMethod();

	GenericValue<Boolean> isExcluded();

	@PrimaryKey
	PsiMethod getPsiMethod();
}
