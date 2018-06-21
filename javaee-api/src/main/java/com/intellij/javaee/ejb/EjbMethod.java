package com.intellij.javaee.ejb;

import java.util.List;

import com.intellij.javaee.model.common.ejb.SecurityGroup;
import com.intellij.javaee.model.enums.TransAttribute;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.util.xml.GenericValue;
import com.intellij.util.xml.PrimaryKey;

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
