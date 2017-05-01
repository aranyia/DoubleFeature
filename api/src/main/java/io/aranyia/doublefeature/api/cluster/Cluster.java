package io.aranyia.doublefeature.api.cluster;

import io.aranyia.doublefeature.api.feature.FeatureVector;

import java.util.Collection;

public interface Cluster<T extends FeatureVector> {

    double[] getCenter();

    void addFeatureVectors(Collection<T> featureVectors);

    Collection<T> getFeatureVectors();

}
