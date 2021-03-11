package ui.tools;

import ui.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadAnimalShelterTool extends Tool {
    public LoadAnimalShelterTool(GUI gui, JComponent parent) {
        super(gui, parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new LoadAnimalShelterToolClickHandler());
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Load Animal Shelter");
        addtoParent(parent);
    }

    private class LoadAnimalShelterToolClickHandler implements ActionListener {

        // CITATION: from DrawingEditor repository
        // EFFECTS: sets active tool to the delete tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.setActiveTool(LoadAnimalShelterTool.this);
        }
    }
}
