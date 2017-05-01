package io.aranyia.doublefeature.api.feature;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(JUnit4.class)
public class FeatureVectorTest {

    @Test
    public void testVectorHashing() {
        final List<String> tags1 = Arrays.asList("A", "B", "C", "D");
        final List<String> tags2 = Arrays.asList("A", "B", "C", "D");

        final FeatureVector vector1 = createStringFeatureVector(tags1);
        final FeatureVector vector2 = createStringFeatureVector(tags2);

        Assert.assertArrayEquals(vector1.getPoint(), vector2.getPoint(), 0);
    }

    @Test
    public void testVectorHashingWithFixVectorSize() {
        final List<String> tags1 = Arrays.asList("A", "B", "C", "D");
        final List<String> tags2 = Arrays.asList("A", "B", "C", "D");
        final int vectorSize = 10;

        final FeatureVector vector1 = createStringFeatureVector(tags1, vectorSize);
        final FeatureVector vector2 = createStringFeatureVector(tags2, vectorSize);

        Assert.assertArrayEquals(vector1.getPoint(), vector2.getPoint(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVectorHashingInvalidVectorSize() {
        final List<String> tags = Arrays.asList("A", "B", "C", "D");

        final int vectorSize = tags.size() - 1;

        createStringFeatureVector(tags, vectorSize);
    }

    @Test
    public void testVectorToString() {
        final List<String> tags = Arrays.asList("A", "B", "C", "D");

        final FeatureVector vector = createStringFeatureVector(tags);

        Assert.assertThat(vector.toString(), CoreMatchers.notNullValue(String.class));
    }

    private FeatureVector<? extends Feature> createStringFeatureVector(Collection<String> elements) {
        return new FeatureVector<>(elements.stream().map(StringFeature::new).collect(Collectors.toList()));
    }

    private FeatureVector<? extends Feature> createStringFeatureVector(Collection<String> elements, int vectorSize) {
        return new FeatureVector<>(elements.stream().map(StringFeature::new).collect(Collectors.toList()), vectorSize);
    }


    private class StringFeature implements Feature {
        private final String value;

        StringFeature(String value) {
            this.value = value;
        }

        public int hashCode() {
            return value.hashCode();
        }
    }
}
