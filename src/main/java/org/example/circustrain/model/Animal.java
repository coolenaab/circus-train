package org.example.circustrain.model;

/**
 * Animal model class
 *
 * @author Coolenaab
 */
public class Animal implements Comparable<Animal>{

    private final boolean carnivore;
    private final AnimalWeightType weightType;

    public Animal(boolean carnivore, AnimalWeightType weightType) {
        this.carnivore = carnivore;
        this.weightType = weightType;
    }

    public boolean isCarnivore() {
        return carnivore;
    }

    public AnimalWeightType getWeightType() {
        return weightType;
    }

    public boolean isLarge() {
        return getWeightType() == AnimalWeightType.LARGE;
    }

    /**
     * Override compareTo to specify when another animal is equal, smaller or larger than this animal.
     *
     * @param o The {@link Animal} to be compared against this animal.
     * @return 0 if the weight is equal, 1 if this animals weight is larger, -1 if the other animals weight is larger
     */
    @Override
    public int compareTo(Animal o) {
        if (this.weightType.getWeight() == o.getWeightType().getWeight()) {
            return 0;
        } else if (this.getWeightType().getWeight() < o.getWeightType().getWeight()) {
          return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "This is a " +
                (carnivore ? "carnivore " : "herbivore ") +
                "of weight type " + weightType;
    }
}
