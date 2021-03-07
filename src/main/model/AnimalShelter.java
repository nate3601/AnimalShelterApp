package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//represents an animal shelter with a list of current animal residents
public class AnimalShelter {
    private final ArrayList<Animal> residents;

    //EFFECTS: constructs a new animal shelter object
    public AnimalShelter() {
        residents = new ArrayList<>();
    }

    //REQUIRES: animal is not already registered
    //MODIFIES: this
    //EFFECTS: adds a given animal to the list of animals in the shelter, returns true if successful
    public boolean registerAnimal(Animal animal) {
        if (!residents.contains(animal)) {
            residents.add(animal);
            return true;
        } else {
            return false;
        }
    }

    //REQUIRES: animal is already registered in the shelter
    //MODIFIES: this
    //EFFECTS: the animal get removed from the list of animals in the shelter, returns true if successful
    public boolean adoptAnimal(Animal animal) {
        if (residents.contains(animal)) {
            residents.remove(animal);
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: returns the number of residents currently in the shelter
    public int size() {
        return residents.size();
    }

    //EFFECTS: returns a string listing out the current residents by their position in the list
    public String listOfResidents() {
        String list = "";
        for (Animal a : residents) {
            list =  list.concat("\n" + residents.indexOf(a) + " -> " + a.getName());
        }
        return list;
    }

    //EFFECTS: returns the animal found at position n in the list of residents
    public Animal selectAnimalAt(int n) {
        Animal dummy = new Animal();
        for (Animal a : residents) {
            if (n == residents.indexOf(a)) {
                dummy = a;
            }
        }
        return dummy;
    }

    // CITATION: got code from JsonSerializationDemo repository
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("residents", residentsToJson());
        return json;
    }

    // CITATION: got code from JsonSerializationDemo repository
    private JSONArray residentsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Animal a : residents) {
            jsonArray.put(a.toJson());
        }
        return jsonArray;
    }

    public List<Animal> getResidents() {
        return this.residents;
    }
}
