/*
 * Copyright (c) 2004 - 2009 by Fuhrer Engineering AG, CH-2504 Biel/Bienne, Switzerland
 */

package consulo.jakartaee.webServer.impl.oss.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.annotation.Nullable;

public abstract class FilteringIterator<T> implements Iterator<T> {

    private final Iterator<T> outer;

    private T next;

    protected FilteringIterator(Iterator<T> outer) {
        this.outer = outer;
        next = getNext();
    }

    public boolean hasNext() {
        return next != null;
    }

    public T next() {
        if (next == null) {
            throw new NoSuchElementException();
        }
        T element = next;
        next = getNext();
        return element;
    }

    public void remove() {
        outer.remove();
    }

    protected abstract boolean matches(T element);

    @Nullable
    private T getNext() {
        while (outer.hasNext()) {
            T element = outer.next();
            if (matches(element)) {
                return element;
            }
        }
        return null;
    }
}
