package model;

//represents a medication which has a name, dose, and frequency
public class Medication extends Animal {
    protected String name;
    protected String dose;
    protected Animal.MedicationFrequency frequency;

    //REQUIRES: dose is in mg
    //EFFECTS: constructs a medication
    public Medication(String name, int dose, Animal.MedicationFrequency frequency) {
        this.name = name;
        this.dose = dose + "";
        this.frequency = frequency;
    }

}
