package io.aranyia.doublefeature.core.feature;

import io.aranyia.doublefeature.api.feature.UniqueFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class EntityFeatureTest {

    @Test
    public void testFeatureHashCode() {
        final String id = "id_1";

        final UniqueFeature<String> feature = new EntityFeature<>(id);

        assertThat(feature.hashCode(), notNullValue());
    }

    @Test
    public void testFeatureToString() {
        final String id = "id_1";

        final UniqueFeature<String> feature = new EntityFeature<>(id);

        assertThat(feature.toString(), notNullValue(String.class));
    }

    @Test
    public void testEqualsFunction() {
        final String id1 = "id_1";
        final String id2 = "id_1";
        final String id3 = "id_3";

        final UniqueFeature<String> feature1 = new EntityFeature<>(id1);
        final UniqueFeature<String> feature2 = new EntityFeature<>(id2);
        final UniqueFeature<String> feature3 = new EntityFeature<>(id3);

        assertThat(feature1, equalTo(feature2));
        assertThat(feature1, not(equalTo(feature3)));
    }
}
