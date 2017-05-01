package io.aranyia.doublefeature.core.feature;

import io.aranyia.doublefeature.api.feature.Feature;

public class GenericFeature<T> implements Feature {

    private final T value;

    public GenericFeature(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof GenericFeature &&
                value.equals(((GenericFeature) obj).getValue());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + '{' + value + '}';
    }
}
