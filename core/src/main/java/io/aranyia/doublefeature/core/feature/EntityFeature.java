package io.aranyia.doublefeature.core.feature;

import io.aranyia.doublefeature.api.feature.UniqueFeature;

public class EntityFeature<ID> implements UniqueFeature<ID> {

    private final ID id;

    public EntityFeature(final ID id) {
        this.id = id;
    }

    @Override
    public ID getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UniqueFeature &&
                id.equals(((UniqueFeature) obj).getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{#" + id + '}';
    }
}
