package io.aranyia.doublefeature.core.cluster;

import io.aranyia.doublefeature.api.cluster.Cluster;
import io.aranyia.doublefeature.api.cluster.ClusterManager;
import io.aranyia.doublefeature.api.feature.FeatureVector;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.distance.EuclideanDistance;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GenericClusterManager<T extends FeatureVector> implements ClusterManager<T> {

    private final List<Cluster<T>> clusters = new LinkedList<>();

    private final DistanceMeasure distanceMeasure;

    public GenericClusterManager() {
        this(Collections.emptyList());
    }

    public GenericClusterManager(Collection<Cluster<T>> clusters) {
        this(clusters, new EuclideanDistance());
    }

    public GenericClusterManager(Collection<Cluster<T>> clusters, DistanceMeasure distanceMeasure) {
        addClusters(clusters);
        this.distanceMeasure = distanceMeasure;
    }

    public void addClusters(Collection<? extends Cluster<T>> clusters) {
        this.clusters.addAll(clusters);
    }

    @Override
    public Collection<Cluster<T>> getClusters() {
        return clusters;
    }

    @Override
    public Cluster<T> getNearestCluster(final T featureVector) {
        double minDistance = Double.MAX_VALUE;
        int clusterIndex = 0;
        int minCluster = 0;

        for (final Cluster<T> cluster : clusters) {
            final double distance = distanceMeasure.compute(featureVector.getPoint(), cluster.getCenter());
            if (distance < minDistance) {
                minDistance = distance;
                minCluster = clusterIndex;
            }
            clusterIndex++;
        }
        return clusters.get(minCluster);
    }
}
