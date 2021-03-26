package ui.tools.buttons;

import ui.AnimalShelterApp;
import ui.tools.buttons.Button;

import javax.swing.*;

public class SaveButton extends Button {

    public SaveButton(AnimalShelterApp animalShelterApp, JPanel parent) {
        super(animalShelterApp, parent);

    }

    @Override
    protected void addListener() {
    }

    @Override
    protected void createButton(JPanel parent) {
        new JButton("Save Animal Shelter");
        addToParent(parent);
    }

}
