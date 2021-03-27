package ui.tools.panels;

import ui.tools.panels.selectsubpanels.AdoptPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.AnimalShelterApp.TOOL_AREA;

public class SavePanel extends JPanel {
    JPanel cards;
    JButton back;

    public SavePanel() {
    }

    public void createDisplay(JPanel cards) {
        this.cards = cards;
        JLabel savedMessage = new JLabel("The shelter has been saved to file!");
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
