package ui.tools.buttons;

import ui.AnimalShelterApp;
import ui.tools.buttons.Button;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectAnimalButton extends Button {
    public SelectAnimalButton(AnimalShelterApp animalShelterApp, JPanel parent) {
        super(animalShelterApp, parent);
    }

    @Override
    protected void addListener() {
    }

    @Override
    protected void createButton(JPanel parent) {
        new JButton("Select Animal");
        addToParent(parent);
    }

}
