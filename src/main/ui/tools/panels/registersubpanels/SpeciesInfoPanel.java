package ui.tools.panels.registersubpanels;

import model.Animal;
import ui.AnimalShelterApp;
import ui.tools.panels.RegisterAnimalPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.Animal.Species.CAT;
import static model.Animal.Species.DOG;

public class SpeciesInfoPanel extends RegisterAnimalPanel {

    JButton dog;
    JButton cat;
    private Animal.Species selection;

    public SpeciesInfoPanel(AnimalShelterApp animalShelterApp, JPanel parent) {
        super(animalShelterApp);
        createTextPanel(parent);
    }

    public Animal.Species getSelection() {
        return this.selection;
    }

    protected void createTextPanel(JPanel parent) {
        JPanel nameInfoPanel = new JPanel();
        nameInfoPanel.setLayout(new GridLayout(1, 0));
        nameInfoPanel.setSize(new Dimension(0, 0));

        JLabel nameInfoLabel = new JLabel("Select animal's species: ");
        nameInfoPanel.add(nameInfoLabel);

        createSpeciesButtons(nameInfoPanel);

        parent.add(nameInfoPanel);
    }

    private void createSpeciesButtons(JPanel panel) {
        dog = new JButton("Dog");
        panel.add(dog);
        dog.addActionListener(new DogClickHandler());

        cat = new JButton("Cat");
        panel.add(cat);
        cat.addActionListener(new CatClickHandler());
    }


    private class DogClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            selection = DOG;
        }
    }

    private class CatClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            selection = CAT;
        }
    }
}
