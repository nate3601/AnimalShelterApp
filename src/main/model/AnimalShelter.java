package model;

import java.util.ArrayList;


public class AnimalShelter {
    private ArrayList<Animal> residents;

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

    public int size() {
        return residents.size();
    }

    public String listOfResidents() {
        String list = "";
        for (Animal a : residents) {
            list =  list + "\n" + residents.indexOf(a) + " -> " + a.getName();
        }
        return list;
    }

    public Animal selectAnimalAt(int n) {
        Animal dummy = new Animal();
        for (Animal a : residents) {
            if (n == residents.indexOf(a)) {
                dummy = a;
            }
        }
        return dummy;
    }
}
