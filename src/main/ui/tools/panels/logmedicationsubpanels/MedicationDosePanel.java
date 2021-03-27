package ui.tools.panels.logmedicationsubpanels;

import ui.AnimalShelterApp;
import ui.tools.panels.registersubpanels.AgeInfoPanel;
import ui.tools.panels.selectsubpanels.LogMedicationPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedicationDosePanel extends LogMedicationPanel {

    JTextField textField;
    private String input = "2";

    public MedicationDosePanel(AnimalShelterApp animalShelterApp, JPanel parent) {
        super(animalShelterApp);
        createTextPanel(parent);
    }

    public String getInput() {
        return this.input;
    }

    protected void createTextPanel(JPanel parent) {
        JPanel nameInfoPanel = new JPanel();
        nameInfoPanel.setLayout(new GridLayout(1, 0));
        nameInfoPanel.setSize(new Dimension(0, 0));

        JLabel nameInfoLabel = new JLabel("Enter medication's dose: ");
        nameInfoPanel.add(nameInfoLabel);

        textField = new JTextField(20);
        textField.addActionListener(new TextEnterHandler());
        nameInfoPanel.add(textField);

        parent.add(nameInfoPanel);
    }

    private class TextEnterHandler implements ActionListener {
        public TextEnterHandler() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            textField.setBackground(Color.LIGHT_GRAY);
            input = textField.getText();
        }
    }
}
