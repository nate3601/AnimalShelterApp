package model;

public class Medication extends Animal {
    protected String medication;
    protected String dose;
    protected Animal.MedicationFrequency frequency;

    //REQUIRES: dose is in mg
    //EFFECTS: constructs a medication
    public Medication(String medication, int dose, Animal.MedicationFrequency frequency) {
        this.medication = medication;
        this.dose = dose + "";
        this.frequency = frequency;
    }

}
