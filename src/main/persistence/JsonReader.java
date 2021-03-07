package persistence;

import model.Animal;
import model.AnimalShelter;
import model.Medication;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// CITATION: got code from JsonSerializationDemo repository
// Represents a reader that reads animal shelter from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads animal shelter from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AnimalShelter read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAnimalShelter(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses shelter from JSON object and returns it
    private AnimalShelter parseAnimalShelter(JSONObject jsonObject) {
        AnimalShelter as = new AnimalShelter();
        addResidents(as, jsonObject);
        return as;
    }

    // MODIFIES: wr
    // EFFECTS: parses animals from JSON object and adds them to shelter
    private void addResidents(AnimalShelter as, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("residents");
        for (Object json : jsonArray) {
            JSONObject nextAnimal = (JSONObject) json;
            addAnimal(as, nextAnimal);
        }
    }


    // MODIFIES: as
    // EFFECTS: parses animal from JSON object and adds it to animal shelter
    private void addAnimal(AnimalShelter as, JSONObject jsonObject) {
        Animal animal = new Animal();
        animal.setName(jsonObject.getString("name"));
        animal.setSpecies(jsonObject.getString("species"));
        animal.setBreed(jsonObject.getString("breed"));
        animal.setAge(jsonObject.getInt("age"));

        for (Object json : jsonObject.getJSONArray("medications")) {
            JSONObject nextMedication = (JSONObject) json;
            addMedication(animal, nextMedication);
        }

        animal.setActivityLevel(animal.convertActivityLevel(jsonObject.getString("activityLevel")));
        animal.setDietSize(animal.convertDietSize(jsonObject.getString("dietSize")));

        as.registerAnimal(animal);
    }

    //MODIFIES: animal
    //EFFECTS: parses medication info from nextMedication and logs it to the animal
    private void addMedication(Animal animal, JSONObject nextMedication) {

        animal.logMedication(nextMedication.getString("name"),
                nextMedication.getInt("dose"),
                animal.convertMedicationFrequency(nextMedication.getString("frequency")));
    }
}
