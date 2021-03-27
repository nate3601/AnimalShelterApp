package persistence;

import model.Animal;
import model.AnimalShelter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            AnimalShelter as = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAnimalShelter() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAnimalShelter.json");
        try {
            AnimalShelter as = reader.read();
            assertEquals(0, as.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAnimalShelter() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralAnimalShelter.json");
        try {
            AnimalShelter as = reader.read();
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
                    Animal.Species.DOG,
                    "idk",
                    12,
                    "",
                    Animal.ActivityLevel.IMMOBILIZED,
                    Animal.DietSize.LARGE_DIET);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
