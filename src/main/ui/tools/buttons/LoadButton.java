package ui.tools.buttons;

import ui.AnimalShelterApp;
import ui.tools.buttons.Button;

import javax.swing.*;

public class LoadButton extends Button {

    public LoadButton(AnimalShelterApp animalShelterApp, JPanel parent) {
        super(animalShelterApp, parent);
    }

    @Override
    protected void addListener() {
    }

    @Override
    protected void createButton(JPanel parent) {
        new JButton("Load Animal Shelter");
        addToParent(parent);
    }

}
