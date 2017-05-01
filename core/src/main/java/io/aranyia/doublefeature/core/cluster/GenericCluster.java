package io.aranyia.doublefeature.core.cluster;

import io.aranyia.doublefeature.api.cluster.Cluster;
import io.aranyia.doublefeature.api.feature.FeatureVector;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class GenericCluster<T extends FeatureVector> implements Cluster<T> {

    private Collection<T> featureVectors;

    public GenericCluster() {
    }

    public GenericCluster(final Collection<T> featureVectors) {
        this.featureVectors = featureVectors;
    }

    @Override
    public double[] getCenter() {
        return calculateCenter(featureVectors);
    }

    @Override
    public void addFeatureVectors(Collection<T> featureVectors) {
        this.featureVectors = featureVectors;
    }

    @Override
    public Collection<T> getFeatureVectors() {
        return featureVectors;
    }

    @Override
    public String toString() {
        return "{" + featureVectors.stream().map(FeatureVector::toString).collect(joining("\n ")) + "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final GenericCluster<?> that = (GenericCluster<?>) o;

        return Objects.equals(featureVectors, that.featureVectors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(featureVectors);
    }

    protected double[] calculateCenter(final Collection<T> vectors) {
        if (vectors == null || vectors.isEmpty()) {
            return new double[0];
        }

        RealVector sumRealVector = new ArrayRealVector(vectors.iterator().next().getPoint().length);

        final List<RealVector> realVectors = vectors.stream()
                .map(T::getPoint)
                .map(ArrayRealVector::new).collect(Collectors.toList());

        for (final RealVector realVector : realVectors) {
            sumRealVector = sumRealVector.add(realVector);
        }

        return sumRealVector.mapDivide(vectors.size()).toArray();
    }
}
