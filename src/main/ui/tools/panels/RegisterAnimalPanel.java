package ui.tools.panels;

import ui.AnimalShelterApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class RegisterAnimalPanel extends JPanel implements ActionListener {

    HashMap<String, JTextField> registrationInfo = new HashMap<>();
    String[] labels = {"Name: ",
            "Species: ",
            "Breed (n/a if not applicable): ",
            "Address: ",
            "Age: ",
    };

    public RegisterAnimalPanel(AnimalShelterApp animalShelterApp, JComponent parent) {

    }
    
    public void setRegistrationInfoLabels() {
        for (String label : labels) {
            registrationInfo.put(label, new JTextField());
        }
    }

    public void processAnimalRegistration() {
        initializeGraphics();
    }

    public void initializeGraphics() {
        setLayout(new SpringLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createRegistrationTools();
        setVisible(true);
    }

    public void createRegistrationTools() {
        //Create and populate the panel.
        for (String label : registrationInfo.keySet()) {
            JLabel l = new JLabel(label, JLabel.TRAILING);
            this.add(l);
            JTextField textField = new JTextField(20);
            l.setLabelFor(textField);
            this.add(textField);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
