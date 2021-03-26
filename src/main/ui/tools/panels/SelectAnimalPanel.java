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

public class SelectAnimalPanel extends JPanel {
    private AnimalShelterApp animalShelterApp;
    protected AnimalShelter shelter;

    JPanel cards;

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
            animalPanel = new AnimalPanel(animalShelterApp, this, animal, cards,
                    showCharacteristicsPanel, logMedicationPanel, adoptPanel);
            add(animalPanel);
        }
    }
}
