package ui.tools;

import ui.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveAnimalShelterTool extends Tool {
    public SaveAnimalShelterTool(GUI gui, JComponent parent) {
        super(gui, parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new SaveAnimalShelterToolClickHandler());
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save Animal Shelter");
        addtoParent(parent);
    }

    private class SaveAnimalShelterToolClickHandler implements ActionListener {

        // CITATION: from DrawingEditor repository
        // EFFECTS: sets active tool to the delete tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.setActiveTool(SaveAnimalShelterTool.this);
        }
    }
}
