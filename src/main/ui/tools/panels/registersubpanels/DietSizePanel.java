package ui.tools.panels.registersubpanels;

import model.Animal;
import ui.AnimalShelterApp;
import ui.tools.panels.RegisterAnimalPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DietSizePanel extends RegisterAnimalPanel {
    JButton smallDiet;
    JButton mediumDiet;
    JButton largeDiet;

    private Animal.DietSize selection;

    public DietSizePanel(AnimalShelterApp animalShelterApp, JPanel parent) {
        super(animalShelterApp);
        createDietSizePanel(parent);
    }

    public Animal.DietSize getSelection() {
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
        smallDiet = new JButton("Small");
        dietSizePanel.add(smallDiet);
        smallDiet.addActionListener(new SmallDietButtonClickHandler());

        mediumDiet = new JButton("Medium");
        dietSizePanel.add(mediumDiet);
        mediumDiet.addActionListener(new MediumDietButtonClickHandler());

        largeDiet = new JButton("Large");
        dietSizePanel.add(largeDiet);
        largeDiet.addActionListener(new LargeDietButtonClickHandler());
    }

    private class SmallDietButtonClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            smallDiet.setBackground(Color.LIGHT_GRAY);
            repaint();
            selection = Animal.DietSize.SMALL_DIET;
        }
    }

    private class MediumDietButtonClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mediumDiet.setBackground(Color.LIGHT_GRAY);
            repaint();
            selection = Animal.DietSize.MEDIUM_DIET;
        }
    }

    private class LargeDietButtonClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            largeDiet.setBackground(Color.LIGHT_GRAY);
            repaint();
            selection = Animal.DietSize.LARGE_DIET;
        }
    }
}
