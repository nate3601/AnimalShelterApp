package ui.tools.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButtonClickHandler implements ActionListener {
    JPanel cards;
    String location;

    public BackButtonClickHandler(JPanel cards, String location) {
        this.cards = cards;
        this.location = location;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, location);
    }
}
