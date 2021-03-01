package model;

import java.util.ArrayList;

//represents an Animal that has a name, species, breed, age, list of medications, activity level, and diet size
public class Animal {
    private String name;
    private String species;
    private String breed;
    private int age;

    protected ArrayList<Medication> medications;

    private Object activityLevel;
    private Object dietSize;


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
//
//    public String getSpecies() {
//        return this.species;
//    }

    public ActivityLevel getActivityLevel() {
        return (ActivityLevel) this.activityLevel;
    }

    public DietSize getDietSize() {
        return (DietSize) this.dietSize;
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

}
