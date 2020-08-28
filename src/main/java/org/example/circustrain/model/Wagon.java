package org.example.circustrain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class for a Wagon which contains {@link Animal}
 */
public class Wagon {

    private static final int MAX_WEIGHT = 10;

    private int currentWeight = 0;
    private boolean cartIsFull = false;
    private final List<Animal> animals;

    public Wagon() {
        animals = new ArrayList<>();
    }

    public int getMaxWeight() {
        return MAX_WEIGHT;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public boolean isCartIsFull() {
        return cartIsFull;
    }

    /**
     * Determine whether an animal fits into this wagon.
     * <p>
     * An animal does not fit when:
     * 1. the cart is already full
     * 2. The added weight of the animal would exceed the maximum weight limit of the cart
     * 3. The weight type of the animal is equal or smaller then a carivore that is already in the wagon
     * </p>
     *
     * @param animal The {@link Animal} to be checked
     * @return True if the animal fits otherwise false
     */
    public boolean fitsInWagon(Animal animal) {
        return !cartIsFull &&
                animal.getWeightType().getWeight() + currentWeight <= MAX_WEIGHT &&
                animals.stream().noneMatch(animalInCart -> animalInCart.isCarnivore() && animalInCart.getWeightType().getWeight() >= animal.getWeightType().getWeight());
    }

    /**
     * Put an animal in the wagon and add its weight to the cart.
     *
     * @param animal The {@link Animal} to be added to the cart
     */
    public boolean putAnimalInWagon(Animal animal) {
        if (fitsInWagon(animal)) {
            animals.add(animal);
            currentWeight += animal.getWeightType().getWeight();
            if (currentWeight >= MAX_WEIGHT) {
                cartIsFull = true;
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Wagon")
                .append(" weight: ")
                .append(currentWeight)
                .append(cartIsFull ? " cart is full " : " cart is not full ")
                .append("Contains animals: ");
        animals.forEach(animal -> {
            stringBuilder.append(animal.getWeightType());
            stringBuilder.append(animal.isCarnivore() ? " carnivore" : " herbivore");
            stringBuilder.append(" ");
        });
        return stringBuilder.toString();
    }
}
