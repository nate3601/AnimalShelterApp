package persistence;

import exceptions.AnimalAlreadyRegisteredException;
import model.Animal;
import model.AnimalShelter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    Animal testAnimal;

    @BeforeEach
    public void setUp() {
        testAnimal = new Animal();
        testAnimal.setName("Tintin");
        testAnimal.setAge(11);
        testAnimal.setBreed("Black Lab");
        testAnimal.setSpecies(Animal.Species.DOG);
        testAnimal.setActivityLevel(Animal.ActivityLevel.HIGH_ENERGY);
        testAnimal.setDietSize(Animal.DietSize.LARGE_DIET);

        testAnimal.logMedication("testMedication1", 2, Animal.MedicationFrequency.ONCE_DAILY);
        testAnimal.logMedication("testMedication2", 5, Animal.MedicationFrequency.TWICE_DAILY);
    }
    @Test
    void testWriterInvalidFile() {
        try {
            AnimalShelter as = new AnimalShelter();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            AnimalShelter as = new AnimalShelter();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAnimalShelter.json");
            writer.open();
            writer.write(as);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAnimalShelter.json");
            as = reader.read();
            assertEquals(0, as.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        Animal testAnimal2 = new Animal();
        testAnimal2.setName("Marley");
        testAnimal2.setAge(12);
        testAnimal2.setBreed("idk");
        testAnimal2.setSpecies(Animal.Species.CAT);
        testAnimal2.setActivityLevel(Animal.ActivityLevel.IMMOBILIZED);
        testAnimal2.setDietSize(Animal.DietSize.SMALL_DIET);

        try {
            AnimalShelter as = new AnimalShelter();
            as.registerAnimal(testAnimal);
            as.registerAnimal(testAnimal2);
            JsonWriter writer = new JsonWriter("./data/testReaderGeneralAnimalShelter.json");
            writer.open();
            writer.write(as);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneralAnimalShelter.json");
            as = reader.read();
            List<Animal> residents = as.getResidents();
            assertEquals(2, residents.size());
            //check that animals are correct
            checkAnimal(residents.get(0),
                    "Tintin",
                    Animal.Species.DOG,
                    "Black Lab",
                    11,
                    "testMedication1, testMedication2, ",
                    Animal.ActivityLevel.HIGH_ENERGY,
                    Animal.DietSize.LARGE_DIET);
            checkAnimal(residents.get(1),
                    "Marley",
                    Animal.Species.CAT,
                    "idk",
                    12,
                    "",
                    Animal.ActivityLevel.IMMOBILIZED,
                    Animal.DietSize.SMALL_DIET);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (AnimalAlreadyRegisteredException e) {
            fail("Exception should not have been thrown");
        }
    }

}
