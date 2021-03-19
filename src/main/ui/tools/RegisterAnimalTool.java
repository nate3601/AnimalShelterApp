package ui.tools;

import ui.AnimalShelterApp;
import ui.tools.panels.RegisterAnimalPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterAnimalTool extends Tool {

    private RegisterAnimalPanel panel;

    public RegisterAnimalTool(AnimalShelterApp animalShelterApp, JComponent parent) {
        super(animalShelterApp, parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new RegisterAnimalToolClickHandler());
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Register Animal");
        addToParent(parent);
    }


    private class RegisterAnimalToolClickHandler implements ActionListener {

        // CITATION: from DrawingEditor repository
        // EFFECTS: sets active tool to the delete tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.processAnimalRegistration();
        }
    }

}
