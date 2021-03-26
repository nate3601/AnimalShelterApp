package ui.tools.panels.selectsubpanels;

import model.Animal;
import ui.AnimalShelterApp;
import ui.tools.panels.BackButtonClickHandler;

import javax.swing.*;
import java.awt.*;

import static ui.AnimalShelterApp.SELECT_ANIMAL_PANEL;

public class ShowCharacteristicsPanel extends JPanel {
    AnimalShelterApp animalShelterApp;
    JPanel cards;
    JButton back;
    Animal animal;

    public ShowCharacteristicsPanel(AnimalShelterApp animalShelterApp) {
        this.animalShelterApp = animalShelterApp;
    }

    public void displayCharacteristics(Animal animal, JPanel cards) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(animal.getName() + "'s characteristics:");
        add(title);
        JLabel name = new JLabel("Name: " + animal.getName());
        add(name);
        JLabel species = new JLabel("Species: " + animal.getSpecies());
        add(species);
        JLabel breed = new JLabel("Breed: " + animal.getBreed());
        add(breed);
        JLabel age = new JLabel("Age: " + animal.getAge());
        JLabel dietSize = new JLabel("Diet Size: " + animal.convertDietSize(animal.getDietSize()));
        add(dietSize);
        JLabel activityLevel = new JLabel("Activity Level: " + animal.convertActivityLevel(animal.getActivityLevel()));
        add(activityLevel);
        JLabel medications = new JLabel("Medications: " + animal.listMedications());

        back = new JButton("Return to animal selection");
        back.addActionListener(new BackButtonClickHandler(cards, SELECT_ANIMAL_PANEL));
        add(back);
    }

}
