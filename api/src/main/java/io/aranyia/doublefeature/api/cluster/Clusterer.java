package io.aranyia.doublefeature.api.cluster;

import io.aranyia.doublefeature.api.feature.FeatureVector;

import java.util.Collection;

public interface Clusterer<T extends FeatureVector> {

    Collection<? extends Cluster<T>> cluster(Collection<T> featureVectors);

}
