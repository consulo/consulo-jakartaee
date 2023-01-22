/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.converter;

import com.intellij.jam.model.common.CommonModelManager;
import com.intellij.javaee.model.xml.ejb.CmpField;
import com.intellij.javaee.model.xml.ejb.EntityBean;
import consulo.util.collection.ContainerUtil;
import consulo.xml.util.xml.ConvertContext;
import consulo.xml.util.xml.ElementPresentationManager;
import consulo.xml.util.xml.ResolvingConverter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

public abstract class CmpFieldConverter extends ResolvingConverter<CmpField>
{

    @Override
    @Nullable
    public CmpField fromString(String value, ConvertContext context) {
        EntityBean bean = getEntityBean(context);
        return (bean != null) ? ElementPresentationManager.findByName(bean.getCmpFields(), value) : null;
    }

    @Override
    @Nullable
    public String toString(CmpField value, ConvertContext context) {
        return (value != null) ? value.getFieldName().getValue() : null;
    }

    @Override
    @Nonnull
    public Collection<CmpField> getVariants(ConvertContext context) {
        EntityBean bean = getEntityBean(context);
        if (bean != null) {
            final CommonModelManager manager = CommonModelManager.getInstance();
            return ContainerUtil.map2List(bean.getCmpFields(), (Function<com.intellij.javaee.model.common.ejb.CmpField, CmpField>) field -> manager.getDomElement(field));
        }
        return Collections.emptyList();
    }

    @Nullable
    protected abstract EntityBean getEntityBean(ConvertContext context);
}
