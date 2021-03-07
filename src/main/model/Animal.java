package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

//represents an Animal that has a name, species, breed, age, list of medications, activity level, and diet size
public class Animal {
    private String name;
    private String species;
    private String breed;
    private int age;

    protected ArrayList<Medication> medications;

    private ActivityLevel activityLevel;
    private DietSize dietSize;

    public enum ActivityLevel {
        IMMOBILIZED,
        RECOVERING,
        LOW_ENERGY,
        HIGH_ENERGY
    }

    public enum DietSize {
        SMALL_DIET,
        MEDIUM_DIET,
        LARGE_DIET
    }

    public enum MedicationFrequency {
        ONCE_DAILY,
        TWICE_DAILY
    }

    //EFFECTS: constructs a new animal object
    public Animal() {
        medications = new ArrayList<>();
    }

    //getters
    public String getName() {
        return this.name;
    }

    public String getSpecies() {
        return this.species;
    }

    public String getBreed() {
        return this.breed;
    }

    public int getAge() {
        return this.age;
    }

    public String getMedicationNames() {
        String list = "";
        for (Medication m : medications) {
            list = list.concat(m.name + ", ");
        }
        return list;
    }

    public ActivityLevel getActivityLevel() {
        return (ActivityLevel) this.activityLevel;
    }

    public DietSize getDietSize() {
        return this.dietSize;
    }

//    public MedicationFrequency getMedicationFrequency() {
//        return (MedicationFrequency) this.medicationFrequency;
//    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }

    public void setDietSize(DietSize dietSize) {
        this.dietSize = dietSize;
    }

    //MODIFIES: this
    //EFFECTS: logs medication onto list of medications for this animal
    public void logMedication(String medication, int dose, MedicationFrequency frequency) {
        Medication dummy = new Medication(medication, dose, frequency);
        medications.add(dummy);
    }

    //EFFECTS: prints list of specified medications
    public String listMedications() {
        String list = "";
        if (medications.size() == 0) {
            return "none.";
        } else {
            for (Medication m : medications) {
                list = list.concat(m.name
                        + " " + m.dose + "mg, " + convertMedicationFrequency(m.frequency) + ". ");
            }
        }
        list = list.substring(0, list.length() - 1);
        return list;
    }

    //EFFECTS: prints out the characteristics of this animal
    public String showCharacteristics() {
        return "Species: " + this.species + System.lineSeparator()
                + "Breed: " + this.breed + System.lineSeparator()
                + "Name: " + this.name + System.lineSeparator()
                + "Age: " + this.age + System.lineSeparator()
                + "Diet Size: " + convertDietSize(this.getDietSize()) + System.lineSeparator()
                + "Activity Level: " + convertActivityLevel(this.getActivityLevel()) + System.lineSeparator()
                + "Medications: " + this.listMedications();
    }

    //EFFECTS: converts enumerated ActivityLevel to a string
    public String convertActivityLevel(ActivityLevel energyLevel) {
        switch (energyLevel) {
            case IMMOBILIZED:
                return "Immobilized";
            case RECOVERING:
                return "Recovering";
            case LOW_ENERGY:
                return "Low Energy";
            case HIGH_ENERGY:
                return "High Energy";
            default:
                return "none";
        }
    }

    //EFFECTS: converts a string to an enumerated ActivityLevel
    public ActivityLevel convertActivityLevel(String energyLevel) {
        switch (energyLevel) {
            case "Immobilized":
                return ActivityLevel.IMMOBILIZED;
            case "Recovering":
                return ActivityLevel.RECOVERING;
            case "Low Energy":
                return ActivityLevel.LOW_ENERGY;
            default:
                return ActivityLevel.HIGH_ENERGY;
        }
    }

    //EFFECTS: converts enumerated DietSize to a string
    public String convertDietSize(DietSize dietSize) {
        switch (dietSize) {
            case SMALL_DIET:
                return "Small";
            case MEDIUM_DIET:
                return "Medium";
            case LARGE_DIET:
                return "Large";
            default:
                return "none";
        }
    }

    //EFFECTS: converts a string to an enumerated DietSize
    public DietSize convertDietSize(String dietSize) {
        switch (dietSize) {
            case "Small":
                return DietSize.SMALL_DIET;
            case "Medium":
                return DietSize.MEDIUM_DIET;
            default:
                return DietSize.LARGE_DIET;
        }
    }

    //EFFECTS: converts enumerated MedicationFrequency to a string
    public String convertMedicationFrequency(MedicationFrequency mf) {
        switch (mf) {
            case ONCE_DAILY:
                return "Once Daily";
            case TWICE_DAILY:
                return "Twice Daily";
            default:
                return "none";
        }
    }

    //EFFECTS: converts enumerated MedicationFrequency to a string
    public MedicationFrequency convertMedicationFrequency(String mf) {
        switch (mf) {
            case "Once Daily":
                return MedicationFrequency.ONCE_DAILY;
            default:
                return MedicationFrequency.TWICE_DAILY;
        }
    }

    // CITATION: got code from JsonSerializationDemo repository
    private JSONArray medicationsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Medication m : medications) {
            jsonArray.put(m.toJson());
        }
        return jsonArray;
    }

    // CITATION: got code from JsonSerializationDemo repository
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("species", species);
        json.put("breed", breed);
        json.put("age", age);
        json.put("medications", medicationsToJson());
        json.put("activityLevel", convertActivityLevel(activityLevel));
        json.put("dietSize", dietSize);

        return json;
    }

}
