package ui.tools;

import ui.AnimalShelterApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoadTool extends Tool {
    public LoadTool(AnimalShelterApp animalShelterApp, JComponent parent) {
        super(animalShelterApp, parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new LoadToolClickHandler());
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Load Animal Shelter");
        addToParent(parent);
    }

    private void loadAnimalShelter() {
        try {
            shelter = jsonReader.read();
            System.out.println("Loaded the animal shelter from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    private class LoadToolClickHandler implements ActionListener {

        // CITATION: from DrawingEditor repository
        // EFFECTS: sets active tool to the delete tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            loadAnimalShelter();
        }
    }
}
