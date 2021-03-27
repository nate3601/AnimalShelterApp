package ui.tools.panels.selectsubpanels;

import model.Animal;
import model.AnimalShelter;
import ui.AnimalShelterApp;
import ui.tools.panels.SelectAnimalPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.AnimalShelterApp.*;

public class AnimalPanel extends SelectAnimalPanel {
    AnimalShelter shelter;
    Animal animal;
    JPanel cards;
    JPanel parent;

    ShowCharacteristicsPanel showCharacteristicsPanel;
    LogMedicationPanel logMedicationPanel;
    AdoptPanel adoptPanel;

    JButton showCharacteristics;
    JButton logMedication;
    JButton adopt;

    public AnimalPanel(AnimalShelterApp animalShelterApp, JPanel parent, AnimalShelter shelter, Animal animal,
                       JPanel cards, ShowCharacteristicsPanel showCharacteristicsPanel,
                       LogMedicationPanel logMedicationPanel,
                       AdoptPanel adoptPanel) {
        super(animalShelterApp);
        this.parent = parent;
        this.shelter = shelter;
        this.animal = animal;
        this.cards = cards;
        this.showCharacteristicsPanel = showCharacteristicsPanel;
        this.logMedicationPanel = logMedicationPanel;
        this.adoptPanel = adoptPanel;
        createAnimalPanel(parent);
    }

    private void createAnimalPanel(JPanel parent) {
        new JPanel();
        this.setLayout(new GridLayout(1, 0));
        this.setSize(new Dimension(0, 0));

        JLabel animalLabel = new JLabel(animal.getName() + ": ");
        this.add(animalLabel);

        createAnimalButtons(this);
    }

    private void createAnimalButtons(JPanel animalPanel) {
        showCharacteristics = new JButton("Show Characteristics");
        animalPanel.add(showCharacteristics);
        showCharacteristics.addActionListener(new ShowCharacteristicsClickHandler());

        logMedication = new JButton("Log Medication");
        animalPanel.add(logMedication);
        logMedication.addActionListener(new LogMedicationClickHandler());

        adopt = new JButton("Adopt");
        animalPanel.add(adopt);
        adopt.addActionListener(new AdoptClickHandler(this));
    }


    private class ShowCharacteristicsClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, SHOW_CHARACTERISTICS_PANEL);
            showCharacteristicsPanel.displayCharacteristics(animal, cards);
        }
    }

    private class LogMedicationClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, LOG_MEDICATION_PANEL);
            logMedicationPanel.processLog(animal, cards);
        }
    }

    private class AdoptClickHandler implements ActionListener {
        AnimalPanel panel;

        public AdoptClickHandler(AnimalPanel animalPanel) {
            this.panel = animalPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            parent.removeAll();
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, ADOPT_PANEL);
            adoptPanel.processAdoption(shelter, animal, cards);
        }
    }
}
