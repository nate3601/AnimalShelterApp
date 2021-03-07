package persistence;

import model.Animal;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkAnimal(Animal animal,
                               String name,
                               String species,
                               String breed,
                               int age,
                               String medicationNames,
                               Animal.ActivityLevel activityLevel,
                               Animal.DietSize dietSize) {
        assertEquals(name, animal.getName());
        assertEquals(species, animal.getSpecies());
        assertEquals(breed, animal.getBreed());
        assertEquals(age, animal.getAge());
        assertEquals(medicationNames, animal.getMedicationNames());
        assertEquals(activityLevel, animal.getActivityLevel());
        assertEquals(dietSize, animal.getDietSize());
    }
}
