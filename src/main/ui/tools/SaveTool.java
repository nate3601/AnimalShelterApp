package ui.tools;

import ui.AnimalShelterApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SaveTool extends Tool {
    public SaveTool(AnimalShelterApp animalShelterApp, JComponent parent) {
        super(animalShelterApp, parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new SaveToolClickHandler());
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save Animal Shelter");
        addToParent(parent);
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

    private class SaveToolClickHandler implements ActionListener {

        // CITATION: from DrawingEditor repository
        // EFFECTS: sets active tool to the delete tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            saveAnimalShelter();
        }
    }
}
