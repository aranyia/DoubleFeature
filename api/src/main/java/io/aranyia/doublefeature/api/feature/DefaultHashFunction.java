package io.aranyia.doublefeature.api.feature;

import java.util.function.Function;

final class DefaultHashFunction<E> implements Function<E, Integer> {

    private final int vectorSize;

    DefaultHashFunction(final int vectorSize) {
        this.vectorSize = vectorSize;
    }

    @Override
    public Integer apply(E e) {
        return e.hashCode() % vectorSize;
    }
}