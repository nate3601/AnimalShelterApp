package ui.tools.panels.selectsubpanels;

import model.Animal;
import model.Medication;
import ui.AnimalShelterApp;
import ui.tools.panels.RegisterAnimalPanel;
import ui.tools.panels.logmedicationsubpanels.MedicationDosePanel;
import ui.tools.panels.logmedicationsubpanels.MedicationFrequencyPanel;
import ui.tools.panels.logmedicationsubpanels.MedicationNamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;
import static ui.AnimalShelterApp.SELECT_ANIMAL_PANEL;
import static ui.AnimalShelterApp.TOOL_AREA;

public class LogMedicationPanel extends JPanel {
    AnimalShelterApp animalShelterApp;
    Animal animal;
    JPanel cards;

    MedicationNamePanel namePanel;
    MedicationDosePanel dosePanel;
    MedicationFrequencyPanel frequencyPanel;


    public LogMedicationPanel(AnimalShelterApp animalShelterApp) {
        this.animalShelterApp = animalShelterApp;
    }

    public void processLog(Animal animal, JPanel cards) {
        this.animal = animal;
        this.cards = cards;
        createLogTools();
    }

    private void createLogTools() {
        JLabel title = new JLabel("Fill out information for " + animal.getName() + "'s medication: ");
        add(title);

        namePanel = new MedicationNamePanel(animalShelterApp, this);
        add(namePanel);

        dosePanel = new MedicationDosePanel(animalShelterApp, this);
        add(dosePanel);

        frequencyPanel = new MedicationFrequencyPanel(animalShelterApp, this);
        add(frequencyPanel);

        JButton logButton = new JButton("Log Medication");
        add(logButton);
        logButton.addActionListener(new LogButtonClickHandler());
    }

    private class LogButtonClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                animal.logMedication(namePanel.getInput(),
                        parseInt(dosePanel.getInput()), frequencyPanel.getSelection());
            } catch (NumberFormatException nfe) {
                animal.logMedication(namePanel.getInput(), 2, frequencyPanel.getSelection());
            }

            removeAll();

            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, SELECT_ANIMAL_PANEL);
        }
    }
}
