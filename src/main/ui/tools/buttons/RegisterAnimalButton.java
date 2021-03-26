package ui.tools.buttons;

import ui.AnimalShelterApp;
import ui.tools.buttons.Button;
import ui.tools.panels.RegisterAnimalPanel;

import javax.swing.*;

public class RegisterAnimalButton extends Button {

    public RegisterAnimalButton(AnimalShelterApp animalShelterApp, JPanel parent) {
        super(animalShelterApp, parent);
    }

    @Override
    protected void addListener() {
    }

    @Override
    protected void createButton(JPanel parent) {
        this.setText("Register Animal");
        addToParent(parent);
    }



}
