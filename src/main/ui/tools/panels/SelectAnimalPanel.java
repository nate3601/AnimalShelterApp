package ui.tools.panels;

import model.Animal;
import model.AnimalShelter;
import ui.AnimalShelterApp;
import ui.tools.panels.registersubpanels.DietSizePanel;
import ui.tools.panels.selectsubpanels.AdoptPanel;
import ui.tools.panels.selectsubpanels.AnimalPanel;
import ui.tools.panels.selectsubpanels.LogMedicationPanel;
import ui.tools.panels.selectsubpanels.ShowCharacteristicsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.AnimalShelterApp.SELECT_ANIMAL_PANEL;
import static ui.AnimalShelterApp.TOOL_AREA;

public class SelectAnimalPanel extends JPanel {
    private AnimalShelterApp animalShelterApp;
    protected AnimalShelter shelter;

    JPanel cards;
    JButton back;

    protected ShowCharacteristicsPanel showCharacteristicsPanel;
    protected LogMedicationPanel logMedicationPanel;
    protected AdoptPanel adoptPanel;

    AnimalPanel animalPanel;

    public SelectAnimalPanel(AnimalShelterApp animalShelterApp) {
        this.animalShelterApp = animalShelterApp;
    }

    public void processAnimalSelection(AnimalShelter shelter, JPanel cards,
                                       ShowCharacteristicsPanel showCharacteristicsPanel,
                                       LogMedicationPanel logMedicationPanel,
                                       AdoptPanel adoptPanel) {
        this.cards = cards;
        this.shelter = shelter;
        this.showCharacteristicsPanel = showCharacteristicsPanel;
        this.logMedicationPanel = logMedicationPanel;
        this.adoptPanel = adoptPanel;
        createAnimalPanels();
    }

    private void createAnimalPanels() {
        setLayout(new FlowLayout());
        JLabel title = new JLabel("Animals currently in shelter: ");
        add(title);

        if (shelter.size() == 0) {
            JLabel noResidents = new JLabel("There are no current residents.");
            add(noResidents);
        }

        for (Animal animal : shelter.getResidents()) {
            animalPanel = new AnimalPanel(animalShelterApp, this, shelter, animal, cards,
                    showCharacteristicsPanel, logMedicationPanel, adoptPanel);
            add(animalPanel);
        }

        back = new JButton("Return to home screen");
        back.addActionListener(new BackButtonHandler());
        add(back);
    }

    private class BackButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeAll();

            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, TOOL_AREA);
        }
    }
}
