package io.aranyia.doublefeature.core.cluster;

import io.aranyia.doublefeature.api.cluster.Cluster;
import io.aranyia.doublefeature.api.cluster.Clusterer;
import io.aranyia.doublefeature.api.feature.Feature;
import io.aranyia.doublefeature.api.feature.FeatureVector;
import io.aranyia.doublefeature.core.feature.StringFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class KMeansClustererTest {

    @Test
    public void testClustering() {
        final int clusterTargetCount = 4;
        final int vectorSize = 10;

        final Clusterer<FeatureVector> clusterer = new KMeansClusterer<>(clusterTargetCount);

        final List<String> tags1 = Arrays.asList("A", "B", "C", "D");
        final List<String> tags2 = Arrays.asList("A", "B");
        final List<String> tags3 = Arrays.asList("A", "C");
        final List<String> tags4 = Arrays.asList("A", "D");
        final List<String> tags5 = Arrays.asList("B", "C", "D");
        final List<String> tags6 = Arrays.asList("B", "D");

        final List<FeatureVector> vectors = Arrays.asList(tags1, tags2, tags3, tags4, tags5, tags6).stream()
                .map(s -> createStringFeatureVector(s, vectorSize)).collect(Collectors.toList());

        final Collection<? extends Cluster<FeatureVector>> clusters = clusterer.cluster(vectors);

        clusters.forEach(System.out::println);

        assertThat(clusters.size(), is(clusterTargetCount));
    }

    private FeatureVector<? extends Feature> createStringFeatureVector(Collection<String> elements, int vectorSize) {
        return new FeatureVector<>(elements.stream().map(StringFeature::new).collect(Collectors.toList()), vectorSize);
    }
}
