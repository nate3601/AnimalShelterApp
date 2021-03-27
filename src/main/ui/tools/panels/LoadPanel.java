package ui.tools.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.AnimalShelterApp.TOOL_AREA;

public class LoadPanel extends JPanel {
    JPanel cards;
    JButton back;

    public LoadPanel() {
    }

    public void createDisplay(JPanel cards) {
        this.cards = cards;
        JLabel savedMessage = new JLabel("The shelter has been loaded from file!");
        add(savedMessage);

        back = new JButton("Return to home screen");
        add(back);
        back.addActionListener(new BackButtonClickHandler());
    }

    private class BackButtonClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeAll();

            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, TOOL_AREA);
        }
    }
}
