package io.aranyia.doublefeature.api.feature;

public interface Feature {

    double DEFAULT_WEIGHT = 1.0;

    default double getWeight() {
        return DEFAULT_WEIGHT;
    }

}
