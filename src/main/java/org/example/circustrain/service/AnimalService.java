package org.example.circustrain.service;

import org.example.circustrain.model.Animal;
import org.example.circustrain.model.AnimalWeightType;
import org.example.circustrain.model.Wagon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Service class for methods related to {@link Animal} classes
 */
public class AnimalService {

    /**
     * Sorts the given amount of animals into a list of wagons.
     *
     * @param smallCarnivores The amount of small carnivores to be added to the wagons
     * @param mediumCarnivores The amount of medium carnivores to be added to the wagons
     * @param largeCarnivores The amount of large carnivores to be added to the wagons
     * @param smallHerbivores The amount of small herbivores to be added to the wagons
     * @param mediumHerbivores The amount of medium herbivores to be added to the wagons
     * @param largeHerbivores The amount of large herbivores to be added to the wagons
     * @return A {@link List} containing {@link Wagon} with the animals sorted within
     */
    public List<Wagon> sortAnimals(int smallCarnivores, int mediumCarnivores, int largeCarnivores, int smallHerbivores, int mediumHerbivores, int largeHerbivores) {
        ArrayList<Wagon> wagons = new ArrayList<>();

        ArrayList<Animal> carnivores = new ArrayList<>();
        carnivores.addAll(createAnimals(AnimalWeightType.SMALL, true, smallCarnivores));
        carnivores.addAll(createAnimals(AnimalWeightType.MEDIUM, true, mediumCarnivores));
        carnivores.addAll(createAnimals(AnimalWeightType.LARGE, true, largeCarnivores));
        Collections.sort(carnivores);

        ArrayList<Animal> herbivores = new ArrayList<>();
        herbivores.addAll(createAnimals(AnimalWeightType.SMALL, false, smallHerbivores));
        herbivores.addAll(createAnimals(AnimalWeightType.MEDIUM, false, mediumHerbivores));
        herbivores.addAll(createAnimals(AnimalWeightType.LARGE, false, largeHerbivores));
        Collections.sort(herbivores);

        carnivores.addAll(herbivores);
        carnivores.forEach(animal -> putAnimalInWagon(animal, wagons));

        wagons.forEach(wagon -> System.out.println(wagon.toString()));
        return wagons;
    }

    /**
     * Loop through the given list of wagons and place the an animal in an appropriate wagon.
     * When no appropriate wagon can be found a new wagon will be added to the list.
     *
     * @param animal The {@link Animal} to be added
     * @param wagons The {@link List} of {@link Wagon} where the {@link Animal} will be added to
     */
    private void putAnimalInWagon(Animal animal, List<Wagon> wagons) {
        if (wagons.isEmpty()) {
           addNewWagon(wagons);
        }

        Optional<Wagon> foundWagon = wagons.stream().filter(wagon -> wagon.fitsInWagon(animal)).findFirst();

        if (foundWagon.isPresent()) {
            foundWagon.get().putAnimalInWagon(animal);
        } else {
            Wagon newWagon = addNewWagon(wagons);
            newWagon.putAnimalInWagon(animal);
        }
    }

    /**
     * Add a new wagon to the list of wagons
     *
     * @param wagons The {@link List} of wagons to be added to
     * @return The wagon that is added
     */
    private Wagon addNewWagon(List<Wagon> wagons) {
        Wagon wagon = new Wagon();
        wagons.add(wagon);
        return wagon;
    }

    /**
     * Creates a specified amount of new animals of the given weight type
     *
     * @param weightType The {@link AnimalWeightType} to be given to the generated animals
     * @param carnivore Boolean determining whether the created animals are herbivores or carnivores
     * @param amount The amount of animals to be created
     * @return {@link ArrayList} containing the newly created animals
     */
    private ArrayList<Animal> createAnimals(AnimalWeightType weightType, boolean carnivore, int amount) {
        ArrayList<Animal> animals = new ArrayList<>();
        for (int i=0; i<amount; i++) {
            animals.add(new Animal(carnivore, weightType));
        }
        return animals;
    }
}
