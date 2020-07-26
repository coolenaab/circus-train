package org.example.circustrain.model;

/**
 * Enum for the weight type of an animal.
 */
public enum AnimalWeightType {

    SMALL(1),
    MEDIUM(3),
    LARGE(5);

    private final int weight;

    AnimalWeightType(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
