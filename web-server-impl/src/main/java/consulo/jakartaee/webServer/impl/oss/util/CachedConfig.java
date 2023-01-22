/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.util;

import consulo.util.lang.StringUtil;
import org.jdom.Element;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class CachedConfig<D> {

    private long stamp;

    @Nullable
    protected static <D, C extends CachedConfig<? super D>> C get(Map<Key, C> cache, Factory<D, C> factory, D data) {
        Key key = factory.createKey(data);
        if (key.isValid()) {
            C config = cache.get(key);
            if (config == null) {
                config = factory.createConfig(data);
                cache.put(key, config);
            }
            config.check(data);
            return config;
        }
        return null;
    }

    protected Element getChild(Element parent, @NonNls String tag) {
        return parent.getChild(tag, parent.getNamespace());
    }

    @Nullable
    protected Element getChild(Element parent, @NonNls String tag, @NonNls String name, @NonNls String value) {
        for (Object o : parent.getChildren(tag, parent.getNamespace())) {
            Element element = (Element) o;
            if (element.getAttributeValue(name).matches(value)) {
                return element;
            }
        }
        return null;
    }

    @SuppressWarnings({"unchecked"})
    protected List<Element> getChildren(Element parent, @NonNls String tag) {
        return (List<Element>) parent.getChildren(tag, parent.getNamespace());
    }

    protected Iterable<Element> getChildren(final Element parent, @NonNls final String tag, @NonNls final String name, @NonNls final String value) {
        return new Iterable<Element>() {
            public Iterator<Element> iterator() {
                return new FilteringIterator<Element>(getChildren(parent, tag).iterator()) {
                    @Override
                    protected boolean matches(Element element) {
                        return element.getAttributeValue(name).matches(value);
                    }
                };
            }
        };
    }

    protected long getStamp(File file) {
        return (file != null) && file.exists() ? file.lastModified() ^ file.length() : 0;
    }

    protected long getStamp(String str) {
        return str != null ? str.hashCode() : 0;
    }

    protected abstract long getStamp(D data);

    protected abstract void update(D data);

    public void check(D data) {
        if (stamp != getStamp(data)) {
            update(data);
            stamp = getStamp(data);
        }
    }

    @SuppressWarnings({"ProtectedInnerClass"})
    protected static class Key {

        private final String[] parts;

        @SuppressWarnings({"PublicConstructorInNonPublicClass", "AssignmentToCollectionOrArrayFieldFromParameter"})
        public Key(@Nonnull String... parts) {
            this.parts = parts;
        }

        public boolean isValid() {
            for (String part : parts) {
                if (StringUtil.isEmpty(part)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        @SuppressWarnings({"AccessingNonPublicFieldOfAnotherObject"})
        public boolean equals(Object other) {
            return (other instanceof Key) && Arrays.equals(parts, ((Key) other).parts);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(parts);
        }
    }

    @SuppressWarnings({"ProtectedInnerClass"})
    protected interface Factory<D, C extends CachedConfig<? super D>> {

        @Nonnull
        Key createKey(D data);

        @Nonnull
        C createConfig(D data);
    }
}
