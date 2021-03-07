package ui;

import model.Animal;
import model.AnimalShelter;
import persistence.JsonWriter;
import persistence.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ShelterApp {
    private static final String JSON_STORE = "./data/animalshelter.json";
    private AnimalShelter shelter;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the shelter application
    public ShelterApp() {
        runShelter();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runShelter() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye.");
    }

    //EFFECTS: initializes the scanner and new animal shelter
    public void init() {
        input = new Scanner(System.in);
        shelter = new AnimalShelter();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("r")) {
            doRegistration();
        } else if (command.equals("s")) {
            doSelection();
        } else if (command.equals("f")) {
            saveAnimalShelter();
        } else if (command.equals("l")) {
            loadAnimalShelter();
        } else {
            System.out.println("Selection not valid, try again:");
        }
    }

    // CITATION: got code from JsonSerializationDemo repository
    //EFFECTS: saves shelter to file
    private void saveAnimalShelter() {
        try {
            jsonWriter.open();
            jsonWriter.write(shelter);
            jsonWriter.close();
            System.out.println("Saved the animal shelter to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // CITATION: got code from JsonSerializationDemo repository
    // MODIFIES: this
    // EFFECTS: loads shelter from file
    private void loadAnimalShelter() {
        try {
            shelter = jsonReader.read();
            System.out.println("Loaded the animal shelter from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    //MODIFIES: this
    //EFFECTS: conducts a selection request
    private void doSelection() {
        if (shelter.size() == 0) {
            System.out.println("There are no residents currently in the shelter.");
        } else {
            System.out.println("Enter the number corresponding to the animal you want:");
            System.out.println(shelter.listOfResidents());
            Animal selectedAnimal = shelter.selectAnimalAt(input.nextInt());
            System.out.println("You have selected " + selectedAnimal.getName() + ". Choose an action:");
            doActions(selectedAnimal);
        }
    }

    //MODIFIES: this
    //EFFECTS: conducts a action request
    private void doActions(Animal selectedAnimal) {
        System.out.println("c -> view characteristics");
        System.out.println("l -> log medication");
        System.out.println("a -> log that animal has been adopted");
        processActionCommand(input.next(), selectedAnimal);
    }

    //MODIFIES: this
    //EFFECTS: processes action command
    private void processActionCommand(String next, Animal selectedAnimal) {
        switch (next) {
            case "c":
                System.out.println(selectedAnimal.showCharacteristics());
                break;
            case "l":
                doLogMedication(selectedAnimal);
                break;
            case "a":
                doAdoptAnimal(selectedAnimal);
                break;
            default:
                System.out.println("Selection not valid, try again:");
                break;
        }
    }

    //MODIFIES: this
    //EFFECTS: conducts an adoption request
    private void doAdoptAnimal(Animal selectedAnimal) {
        shelter.adoptAnimal(selectedAnimal);
        System.out.println(selectedAnimal.getName() + " has been adopted!");
    }

    //MODIFIES: this
    //EFFECTS: conducts a log medication request
    private void doLogMedication(Animal selectedAnimal) {
        System.out.println("Enter medication name: ");
        String medication = input.next();
        System.out.println("Enter dose (in mg): ");
        int dose = input.nextInt();
        System.out.println("\n Choose number corresponding to frequency of taking medication:");
        System.out.println("\t 1 -> Once Daily");
        System.out.println("\t 2 -> Twice Daily");
        Animal.MedicationFrequency frequency = chooseMedicationFrequency(input.nextInt());
        selectedAnimal.logMedication(medication, dose, frequency);
        System.out.println("Medication has been logged!");
    }

    //EFFECTS: converts medication frequency
    private Animal.MedicationFrequency chooseMedicationFrequency(int n) {
        if (n == 1) {
            return Animal.MedicationFrequency.ONCE_DAILY;
        } else if (n == 2) {
            return Animal.MedicationFrequency.TWICE_DAILY;
        } else {
            return null;
        }
    }

    //MODIFIES: this
    //EFFECTS: conducts a registration request
    private void doRegistration() {
        Animal animal = new Animal();
        System.out.println("Enter the animal's name: ");
        animal.setName(input.next());

        System.out.println("Enter the animal's species: ");
        animal.setSpecies(input.next());

        System.out.println("Enter the animal's breed (n/a if not applicable): ");
        animal.setBreed(input.next());

        System.out.println("Enter the animal's age:");
        animal.setAge(input.nextInt());

        System.out.println("\nChoose number corresponding to animal's activity level: ");
        System.out.println("\t1 -> Immobilized");
        System.out.println("\t2 -> Recovering");
        System.out.println("\t3 -> Low Energy");
        System.out.println("\t4 -> High Energy");
        animal.setActivityLevel(chooseActivityLevel(input.nextInt()));

        System.out.println("\nChoose number corresponding to animal's diet size: ");
        System.out.println("\t1 -> Small");
        System.out.println("\t2 -> Medium");
        System.out.println("\t3 -> Large");
        animal.setDietSize(chooseDietSize(input.nextInt()));

        shelter.registerAnimal(animal);
        System.out.println(animal.getName() + " has been registered!");
    }

    //EFFECTS: converts n to diet size
    private Animal.DietSize chooseDietSize(int n) {
        if (n == 1) {
            return Animal.DietSize.SMALL_DIET;
        } else if (n == 2) {
            return Animal.DietSize.MEDIUM_DIET;
        } else if (n == 3) {
            return Animal.DietSize.LARGE_DIET;
        } else {
            return null;
        }
    }

    //EFFECTS: converts n to activity level
    private Animal.ActivityLevel chooseActivityLevel(int n) {
        if (n == 1) {
            return Animal.ActivityLevel.IMMOBILIZED;
        } else if (n == 2) {
            return Animal.ActivityLevel.RECOVERING;
        } else if (n == 3) {
            return Animal.ActivityLevel.LOW_ENERGY;
        } else if (n == 4) {
            return Animal.ActivityLevel.HIGH_ENERGY;
        } else {
            return null;
        }
    }

    //EFFECTS: displays menu of options to the user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tr -> register new animal");
        System.out.println("\ts -> select an existing resident to perform actions on");
        System.out.println("\tf -> save animal shelter to file");
        System.out.println("\tl -> load animal shelter from file");
        System.out.println("\tq -> quit");
    }
}
