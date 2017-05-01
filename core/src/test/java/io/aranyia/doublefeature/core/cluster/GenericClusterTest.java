package io.aranyia.doublefeature.core.cluster;

import io.aranyia.doublefeature.api.cluster.Cluster;
import io.aranyia.doublefeature.api.feature.Feature;
import io.aranyia.doublefeature.api.feature.FeatureVector;
import io.aranyia.doublefeature.core.feature.StringFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class GenericClusterTest {

    private Cluster<FeatureVector> cluster;

    @Before
    public void init() {
        this.cluster = new GenericCluster<>();
    }

    @Test
    public void testManipulatingFeatureVectors() {
        final Collection<FeatureVector> vectors = Collections.emptyList();
        cluster.addFeatureVectors(vectors);

        final Collection<FeatureVector> clusterVectors = cluster.getFeatureVectors();

        assertThat(clusterVectors, is(vectors));
    }

    @Test
    public void testCenterCalculation() {
        final int vectorSize = 10;
        final List<String> tags1 = Arrays.asList("A", "B", "C", "D");
        final List<String> tags2 = Arrays.asList("A", "B");
        final List<String> tags3 = Arrays.asList("A", "C");
        final List<String> tags4 = Arrays.asList("A", "D");
        final List<String> tags5 = Arrays.asList("B", "C", "D");
        final List<String> tags6 = Arrays.asList("B", "D");

        final List<FeatureVector> vectors = Arrays.asList(tags1, tags2, tags3, tags4, tags5, tags6).stream()
                .map(s -> createStringFeatureVector(s, vectorSize)).collect(Collectors.toList());
        cluster.addFeatureVectors(vectors);

        final double[] clusterCenter = cluster.getCenter();

        assertThat(clusterCenter, notNullValue());
        assertThat(clusterCenter.length, is(vectorSize));
    }

    @Test
    public void testCenterCalculationWhenEmpty() {
        final Collection<FeatureVector> vectors = Collections.emptyList();
        cluster.addFeatureVectors(vectors);

        final double[] clusterCenter = cluster.getCenter();

        assertThat(clusterCenter, is(new double[0]));
    }

    @Test
    public void testClusterToString() {
        final Collection<FeatureVector> vectors = Collections.emptyList();
        cluster.addFeatureVectors(vectors);

        assertThat(cluster.toString(), notNullValue(String.class));
    }

    @Test
    public void testEqualsFunction() {
        final Collection<FeatureVector> vectors = Collections.emptyList();
        cluster.addFeatureVectors(vectors);

        final Cluster<FeatureVector> cluster2 = new GenericCluster<>(vectors);

        assertThat(cluster, equalTo(cluster2));
    }

    private FeatureVector<? extends Feature> createStringFeatureVector(Collection<String> elements, int vectorSize) {
        return new FeatureVector<>(elements.stream().map(StringFeature::new).collect(Collectors.toList()), vectorSize);
    }
}
