package io.aranyia.doublefeature.core.cluster;

import io.aranyia.doublefeature.api.cluster.Cluster;
import io.aranyia.doublefeature.api.cluster.Clusterer;
import io.aranyia.doublefeature.api.feature.FeatureVector;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class KMeansClusterer<T extends FeatureVector> implements Clusterer<T> {

    private final org.apache.commons.math3.ml.clustering.Clusterer<T> clusterer;

    public KMeansClusterer(int k) {
        this.clusterer = new KMeansPlusPlusClusterer<>(k);
    }

    @Override
    public List<? extends Cluster<T>> cluster(Collection<T> featureVectors) {
        final List<? extends org.apache.commons.math3.ml.clustering.Cluster<T>> clusters =
                clusterer.cluster(featureVectors);

        return clusters.stream().map(org.apache.commons.math3.ml.clustering.Cluster::getPoints)
                .map(GenericCluster::new)
                .collect(Collectors.toList());
    }
}
