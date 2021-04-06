package ui.tools.panels;

import exceptions.AnimalAlreadyRegisteredException;
import model.Animal;
import model.AnimalShelter;
import ui.AnimalShelterApp;
import ui.tools.panels.registersubpanels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;
import static ui.AnimalShelterApp.TOOL_AREA;

public class RegisterAnimalPanel extends JPanel {
    AnimalShelter shelter;
    Animal animalToRegister;
    AnimalShelterApp animalShelterApp;

    JPanel cards;

    NameInfoPanel nameInfoPanel;
    SpeciesInfoPanel speciesInfoPanel;
    BreedInfoPanel breedInfoPanel;
    AgeInfoPanel ageInfoPanel;
    DietSizePanel dietSizePanel;
    ActivityLevelPanel activityLevelPanel;

    public RegisterAnimalPanel(AnimalShelterApp animalShelterApp) {
        this.animalShelterApp = animalShelterApp;
    }

    public void processAnimalRegistration(AnimalShelter shelter, JPanel cards) {
        this.cards = cards;
        this.shelter = shelter;
        this.animalToRegister = new Animal();
        createRegistrationTools();
    }

    public void createRegistrationTools() {
        JLabel title = new JLabel("Fill out animal's information (press enter after each text input): ");
        add(title);

        nameInfoPanel = new NameInfoPanel(animalShelterApp, this);
        add(nameInfoPanel);

        speciesInfoPanel = new SpeciesInfoPanel(animalShelterApp, this);
        add(speciesInfoPanel);

        breedInfoPanel = new BreedInfoPanel(animalShelterApp, this);
        add(breedInfoPanel);

        ageInfoPanel = new AgeInfoPanel(animalShelterApp, this);
        add(breedInfoPanel);

        dietSizePanel = new DietSizePanel(animalShelterApp, this);
        add(dietSizePanel);

        activityLevelPanel = new ActivityLevelPanel(animalShelterApp, this);
        add(activityLevelPanel);

        JButton registerButton = new JButton("Register animal");
        add(registerButton);
        registerButton.addActionListener(new RegisterButtonClickHandler());
    }

    private class RegisterButtonClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            animalToRegister.setName(nameInfoPanel.getInput());
            animalToRegister.setSpecies(speciesInfoPanel.getSelection());
            animalToRegister.setBreed(breedInfoPanel.getInput());
            try {
                animalToRegister.setAge(parseInt(ageInfoPanel.getInput()));
            } catch (NumberFormatException exception) {
                animalToRegister.setAge(0);
            }
            animalToRegister.setDietSize(dietSizePanel.getSelection());
            animalToRegister.setActivityLevel(activityLevelPanel.getSelection());

            try {
                shelter.registerAnimal(animalToRegister);
            } catch (AnimalAlreadyRegisteredException animalAlreadyRegisteredException) {
                System.out.println("Animal is already in the shelter!");
            }

            removeAll();

            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, TOOL_AREA);
        }
    }

}
