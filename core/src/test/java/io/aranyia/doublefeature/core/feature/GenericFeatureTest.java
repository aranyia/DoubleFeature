package io.aranyia.doublefeature.core.feature;

import io.aranyia.doublefeature.api.feature.Feature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class GenericFeatureTest {

    @Test
    public void testFeatureHashCode() {
        final String tag = "A";

        final Feature feature = new GenericFeature<>(tag);

        assertThat(feature.hashCode(), notNullValue());
    }

    @Test
    public void testFeatureToString() {
        final String tag = "A";

        final Feature feature = new GenericFeature<>(tag);

        assertThat(feature.toString(), notNullValue(String.class));
    }

    @Test
    public void testEqualsFunction() {
        final String tag1 = "A";
        final String tag2 = "A";
        final String tag3 = "B";

        final Feature feature1 = new GenericFeature<>(tag1);
        final Feature feature2 = new GenericFeature<>(tag2);
        final Feature feature3 = new GenericFeature<>(tag3);

        assertThat(feature1, equalTo(feature2));
        assertThat(feature1, not(equalTo(feature3)));
    }
}
