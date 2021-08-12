package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static model.Animal.Species.CAT;
import static model.Animal.Species.DOG;

//represents an Animal that has a name, species, breed, age, list of medications, activity level, and diet size
public class Animal {
    private String name;
    private String breed;
    private int age;

    protected ArrayList<Medication> medications;

    private Species species;
    private ActivityLevel activityLevel;
    private DietSize dietSize;

    public enum Species {
        DOG,
        CAT
    }

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

    public Species getSpecies() {
        return this.species;
    }

    public String getBreed() {
        return this.breed;
    }

    public int getAge() {
        return this.age;
    }

    // EFFECTS:
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

    public void setSpecies(Species species) {
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
        Medication dummy = new Medication(medication, dose, frequency, this);
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
        return "Species: " + convertSpecies(this.getSpecies()) + System.lineSeparator()
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
            case "High Energy":
                return ActivityLevel.HIGH_ENERGY;
            default: return null;
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
            case "Large" :
                return DietSize.LARGE_DIET;
            default: return null;
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

    //EFFECTS: converts a string to an enumerated MedicationFrequency
    public MedicationFrequency convertMedicationFrequency(String mf) {
        switch (mf) {
            case "Once Daily":
                return MedicationFrequency.ONCE_DAILY;
            default:
                return MedicationFrequency.TWICE_DAILY;
        }
    }

    //EFFECTS: converts enumerated Species to a string
    public String convertSpecies(Species species) {
        switch (species) {
            case DOG: return "Dog";
            case CAT: return "Cat";
            default: return "none";
        }
    }

    //EFFECTS: converts a string to an enumerated Species
    public Species convertSpecies(String species) {
        switch (species) {
            case "Cat":
                return CAT;
            case "Dog":
                return DOG;
            default:
                return null;
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
        json.put("species", convertSpecies(species));
        json.put("breed", breed);
        json.put("age", age);
        json.put("medications", medicationsToJson());
        json.put("activityLevel", convertActivityLevel(activityLevel));
        json.put("dietSize", convertDietSize(dietSize));

        return json;
    }

}
