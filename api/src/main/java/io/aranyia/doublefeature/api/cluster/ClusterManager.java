package io.aranyia.doublefeature.api.cluster;

import io.aranyia.doublefeature.api.feature.FeatureVector;

import java.util.Collection;

public interface ClusterManager<T extends FeatureVector> {

    void addClusters(Collection<? extends Cluster<T>> clusters);

    Collection<Cluster<T>> getClusters();

    Cluster<T> getNearestCluster(T featureVector);

}
