package ui.tools.panels.selectsubpanels;

import model.Animal;
import model.AnimalShelter;
import ui.AnimalShelterApp;
import ui.tools.panels.SelectAnimalPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        try {
            createAnimalPanel(parent);
        } catch (IOException e) {
            //
        }
    }

    private void createAnimalPanel(JPanel parent) throws IOException {
        new JPanel();
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setSize(new Dimension(0, 0));

        BufferedImage catImage = ImageIO.read(new File("./data/cat_icon.png"));
        catImage = resize(catImage, 30, 20);

        BufferedImage dogImage = ImageIO.read(new File("./data/dog_icon.png"));
        dogImage = resize(dogImage, 30, 20);

        if (animal.getSpecies() == Animal.Species.CAT) {
            JLabel icon = new JLabel(new ImageIcon(catImage));
            this.add(icon);
        } else if (animal.getSpecies() == Animal.Species.DOG) {
            JLabel icon = new JLabel(new ImageIcon(dogImage));
            icon.setSize(new Dimension(30, 20));
            this.add(icon);
        }

        JLabel animalLabel = new JLabel(animal.getName() + ": ");
        this.add(animalLabel);

        createAnimalButtons(this);
    }

    //CITATION: got from Ocracoke on stack overflow
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
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
