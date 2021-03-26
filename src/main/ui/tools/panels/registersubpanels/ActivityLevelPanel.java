package ui.tools.panels.registersubpanels;

import model.Animal;
import ui.AnimalShelterApp;
import ui.tools.panels.RegisterAnimalPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivityLevelPanel extends RegisterAnimalPanel {
    JButton immobilized;
    JButton recovering;
    JButton lowEnergy;
    JButton highEnergy;

    private Animal.ActivityLevel selection;

    public ActivityLevelPanel(AnimalShelterApp animalShelterApp, JPanel parent) {
        super(animalShelterApp);
        createDietSizePanel(parent);
    }

    public Animal.ActivityLevel getSelection() {
        return this.selection;
    }

    public void createDietSizePanel(JPanel parent) {
        JPanel dietSizePanel = new JPanel();
        dietSizePanel.setLayout(new GridLayout(1, 0));
        dietSizePanel.setSize(new Dimension(0, 0));

        JLabel dietSizeLabel = new JLabel("Choose diet size: ");
        dietSizePanel.add(dietSizeLabel);

        createDietSizeButtons(dietSizePanel);

        parent.add(dietSizePanel);
    }

    private void createDietSizeButtons(JPanel dietSizePanel) {
        immobilized = new JButton("Immobilized");
        dietSizePanel.add(immobilized);
        immobilized.addActionListener(new ImmobilizedButtonClickHandler());

        recovering = new JButton("Recovering");
        dietSizePanel.add(recovering);
        recovering.addActionListener(new RecoveringButtonClickHandler());

        lowEnergy = new JButton("Low Energy");
        dietSizePanel.add(lowEnergy);
        lowEnergy.addActionListener(new LowEnergyButtonClickHandler());

        highEnergy = new JButton("High Energy");
        dietSizePanel.add(highEnergy);
        highEnergy.addActionListener(new HighEnergyButtonClickHandler());
    }

    private class ImmobilizedButtonClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            immobilized.setBackground(Color.LIGHT_GRAY);
            repaint();
            selection = Animal.ActivityLevel.IMMOBILIZED;
        }
    }

    private class RecoveringButtonClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            recovering.setBackground(Color.LIGHT_GRAY);
            repaint();
            selection = Animal.ActivityLevel.RECOVERING;
        }
    }

    private class LowEnergyButtonClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            lowEnergy.setBackground(Color.LIGHT_GRAY);
            repaint();
            selection = Animal.ActivityLevel.LOW_ENERGY;
        }
    }

    private class HighEnergyButtonClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            highEnergy.setBackground(Color.LIGHT_GRAY);
            repaint();
            selection = Animal.ActivityLevel.HIGH_ENERGY;
        }
    }
}
