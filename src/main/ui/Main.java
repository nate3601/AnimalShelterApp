package ui;

import model.AnimalShelter;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Animal Shelter App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AnimalShelterApp a = new AnimalShelterApp();
        a.init(frame.getContentPane());

        frame.setMinimumSize(new Dimension(700, 300));
        frame.setVisible(true);
    }
}
