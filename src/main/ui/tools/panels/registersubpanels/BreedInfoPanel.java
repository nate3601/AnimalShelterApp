package ui.tools.panels.registersubpanels;

import ui.AnimalShelterApp;
import ui.tools.panels.RegisterAnimalPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BreedInfoPanel extends RegisterAnimalPanel {

    JTextField textField;
    private String input = "unknown";

    public BreedInfoPanel(AnimalShelterApp animalShelterApp, JPanel parent) {
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

        JLabel nameInfoLabel = new JLabel("Enter animal's breed: ");
        nameInfoPanel.add(nameInfoLabel);

        textField = new JTextField(20);
        textField.addActionListener(new BreedInfoPanel.TextFieldEnterHandler(this));
        nameInfoPanel.add(textField);

        parent.add(nameInfoPanel);
    }

    private class TextFieldEnterHandler implements ActionListener {
        public TextFieldEnterHandler(BreedInfoPanel nameInfoPanel) {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            textField.setBackground(Color.LIGHT_GRAY);
            input = textField.getText();
        }
    }
}
