package io.aranyia.doublefeature.api.feature;

import org.apache.commons.math3.ml.clustering.Clusterable;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

public class FeatureVector<E extends Feature> implements Clusterable {

    private final double[] vector;

    private final Function<E, Integer> hashFunction;

    public FeatureVector(final Collection<E> features) {
        this(features, features.size());
    }

    public FeatureVector(final Collection<E> features, final int vectorSize) {
        this(features, vectorSize, new DefaultHashFunction<>(vectorSize));
    }

    public FeatureVector(final Collection<E> features, final int vectorSize, final Function<E, Integer> hashFunction) {
        if (features.size() > vectorSize) {
            throw new IllegalArgumentException("cannot have more features than the vector's size");
        }
        this.hashFunction = hashFunction;

        this.vector = new double[vectorSize];

        features.forEach(this::addFeature);
    }

    public void addFeature(E feature) {
        vector[hashFunction.apply(feature)] = feature.getWeight();
    }

    @Override
    public double[] getPoint() {
        return vector;
    }

    @Override
    public String toString() {
        return Arrays.toString(vector);
    }
}
