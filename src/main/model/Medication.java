package model;

import org.json.JSONObject;
import model.Animal;

//represents a medication which has a name, dose, and frequency
public class Medication {
    protected String name;
    protected String dose;
    protected Animal.MedicationFrequency frequency;
    protected Animal animal;

    //REQUIRES: dose is in mg
    //EFFECTS: constructs a medication
    public Medication(String name, int dose, Animal.MedicationFrequency frequency, Animal animal) {
        this.name = name;
        this.dose = dose + "";
        this.frequency = frequency;
        this.animal = animal;
    }

    // CITATION: got code from JsonSerializationDemo repository
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("dose", dose);
        json.put("frequency", animal.convertMedicationFrequency(frequency));

        return json;
    }
}
