package ui.tools;

import ui.GUI;

import javax.swing.*;

public abstract class Tool {
    protected JButton button;
    protected GUI gui;
    private boolean active;

    public Tool(GUI gui, JComponent parent) {
        this.gui = gui;
        createButton(parent);
        addtoParent(parent);
        addListener();
    }

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addtoParent(JComponent parent) {
        parent.add(button);
    }

    // EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent);

    public  void deactivate() {
        active = false;
    }

    public  void activate() {
        active = true;
    }
}
