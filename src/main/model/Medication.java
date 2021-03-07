package model;

import org.json.JSONObject;
import model.Animal;

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

    // CITATION: got code from JsonSerializationDemo repository
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("dose", dose);
        json.put("frequency", convertMedicationFrequency(frequency));

        return json;
    }
}
