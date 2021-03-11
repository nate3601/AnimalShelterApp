package ui;

import ui.tools.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GUI extends JFrame {

    int count = 0;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    public static final int BORDER_WIDTH = 20;

    private JFrame frame;
    private JPanel panel;
    private JLabel label;

    private List<Tool> tools;
    private Tool activeTool;

    public GUI() {
        frame = new JFrame();
        panel = new JPanel();

        label = new JLabel("Number of animals:");

        panel.setBorder(BorderFactory.createEmptyBorder(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH));
        panel.setLayout(new FlowLayout());

        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Animal Shelter");
        frame.setLayout(new FlowLayout());
        createToolButtons();
        frame.pack();
        frame.setVisible(true);
    }


    public void createToolButtons() {
        RegisterAnimalTool registerButton = new RegisterAnimalTool(this, panel);
        SelectAnimalTool selectAnimalButton = new SelectAnimalTool(this, panel);
        LoadAnimalShelterTool loadShelterButton = new LoadAnimalShelterTool(this, panel);
        SaveAnimalShelterTool saveShelterButton = new SaveAnimalShelterTool(this, panel);

        tools.add(registerButton);
        tools.add(selectAnimalButton);
        tools.add(saveShelterButton);
        tools.add(loadShelterButton);

    }

    // CITATION: got from DrawingEditor repository
    // MODIFIES: this
    // EFFECTS:  sets the given tool as the activeTool
    public void setActiveTool(Tool tool) {
        if (activeTool != null) {
            activeTool.deactivate();
            tool.activate();
            activeTool = tool;
        }
    }
}
