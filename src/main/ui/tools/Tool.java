package ui.tools;

import model.AnimalShelter;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.AnimalShelterApp;

import javax.swing.*;
import java.util.Scanner;

public abstract class Tool extends AnimalShelterApp {

    protected JButton button;
    protected AnimalShelterApp animalShelterApp;

    public Tool(AnimalShelterApp animalShelterApp, JComponent parent) {
        this.animalShelterApp = animalShelterApp;
        createButton(parent);
        addToParent(parent);
        addListener();
        initializeFields();
    }

    public void initializeFields() {
        input = new Scanner(System.in);
        shelter = new AnimalShelter();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }

    // EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent);

}
