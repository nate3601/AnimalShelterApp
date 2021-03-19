package ui.tools;

import ui.AnimalShelterApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectAnimalTool extends Tool {
    public SelectAnimalTool(AnimalShelterApp animalShelterApp, JComponent parent) {
        super(animalShelterApp, parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new SelectAnimalToolClickHandler());
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Select Animal");
        addToParent(parent);
    }

    private class SelectAnimalToolClickHandler implements ActionListener {

        // CITATION: from DrawingEditor repository
        // EFFECTS: sets active tool to the delete tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
}
