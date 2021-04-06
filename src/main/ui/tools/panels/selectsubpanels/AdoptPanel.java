package ui.tools.panels.selectsubpanels;

import exceptions.AnimalNotFoundException;
import model.Animal;
import model.AnimalShelter;
import ui.AnimalShelterApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.AnimalShelterApp.SELECT_ANIMAL_PANEL;
import static ui.AnimalShelterApp.TOOL_AREA;

public class AdoptPanel extends JPanel {
    AnimalShelter shelter;
    Animal animal;
    JPanel cards;

    JButton back;

    public AdoptPanel(AnimalShelterApp animalShelterApp) {

    }

    public void processAdoption(AnimalShelter shelter, Animal animal, JPanel cards) {
        this.shelter = shelter;
        this.animal = animal;
        this.cards = cards;

        try {
            shelter.adoptAnimal(animal);
        } catch (AnimalNotFoundException e) {
            System.out.println("This animal is not in the shelter!");
        }

        JLabel adoptionMessage = new JLabel(animal.getName() + " has been adopted!");
        add(adoptionMessage);

        back = new JButton("Return to home screen");
        add(back);
        back.addActionListener(new BackButtonClickHandler());
    }

    private class BackButtonClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeAll();

            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, TOOL_AREA);
        }
    }
}
