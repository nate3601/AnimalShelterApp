package ui.tools.panels.logmedicationsubpanels;

import model.Animal;
import ui.AnimalShelterApp;
import ui.tools.panels.registersubpanels.ActivityLevelPanel;
import ui.tools.panels.selectsubpanels.LogMedicationPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedicationFrequencyPanel extends LogMedicationPanel {
    JButton onceDaily;
    JButton twiceDaily;

    private Animal.MedicationFrequency selection;

    public MedicationFrequencyPanel(AnimalShelterApp animalShelterApp, JPanel parent) {
        super(animalShelterApp);
        createPanel(parent);
    }

    private void createPanel(JPanel parent) {
        JPanel medicationFrequencyPanel = new JPanel();
        medicationFrequencyPanel.setLayout(new GridLayout(1, 0));
        medicationFrequencyPanel.setSize(new Dimension(0, 0));

        JLabel frequencyLabel = new JLabel("Choose frequency: ");
        add(frequencyLabel);

        createButtons(medicationFrequencyPanel);

        add(medicationFrequencyPanel);
    }

    private void createButtons(JPanel medicationFrequencyPanel) {
        onceDaily = new JButton("Once Daily");
        medicationFrequencyPanel.add(onceDaily);
        onceDaily.addActionListener(new OnceDailyClickHandler());

        twiceDaily = new JButton("Twice Daily");
        medicationFrequencyPanel.add(twiceDaily);
        twiceDaily.addActionListener(new TwiceDailyClickHandler());
    }

    public Animal.MedicationFrequency getSelection() {
        return this.selection;
    }

    private class OnceDailyClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            selection = Animal.MedicationFrequency.ONCE_DAILY;
        }
    }

    private class TwiceDailyClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            selection = Animal.MedicationFrequency.TWICE_DAILY;
        }
    }
}
