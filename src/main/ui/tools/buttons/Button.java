package ui.tools.buttons;

import model.AnimalShelter;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.AnimalShelterApp;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Scanner;

public abstract class Button extends JButton {

    protected AnimalShelterApp animalShelterApp;
    protected JPanel parent;

    public Button(AnimalShelterApp animalShelterApp, JPanel parent) {
        this.parent = parent;
        this.animalShelterApp = animalShelterApp;
        createButton(parent);
        addToParent(parent);
        addListener();
        initializeFields();
    }

    public void initializeFields() {
    }

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JPanel parent) {
        parent.add(this);
    }

    // EFFECTS: creates button
    protected abstract void createButton(JPanel parent);

}
