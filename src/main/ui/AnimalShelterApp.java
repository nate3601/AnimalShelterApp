package ui;

import model.AnimalShelter;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tools.buttons.LoadButton;
import ui.tools.buttons.RegisterAnimalButton;
import ui.tools.buttons.SaveButton;
import ui.tools.buttons.SelectAnimalButton;
import ui.tools.panels.LoadPanel;
import ui.tools.panels.RegisterAnimalPanel;
import ui.tools.panels.SavePanel;
import ui.tools.panels.SelectAnimalPanel;
import ui.tools.panels.selectsubpanels.AdoptPanel;
import ui.tools.panels.selectsubpanels.LogMedicationPanel;
import ui.tools.panels.selectsubpanels.ShowCharacteristicsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AnimalShelterApp {

    protected static final String JSON_STORE = "./data/animalshelter.json";
    protected AnimalShelter shelter;
    protected Scanner input;
    protected JsonWriter jsonWriter;
    protected JsonReader jsonReader;

    public static final String TOOL_AREA = "card for showing buttons";
    public static final String REGISTER_ANIMAL_PANEL = "card for animal registration";
    public static final String SELECT_ANIMAL_PANEL = "card for selecting animals";
    public static final String LOAD_PANEL = "card for loading animal shelter";
    public static final String SAVE_PANEL = "card for saving animal shelter";
    public static final String SHOW_CHARACTERISTICS_PANEL = "card for showing an animal's characteristics";
    public static final String LOG_MEDICATION_PANEL = "card for logging an animal's medication";
    public static final String ADOPT_PANEL = "card for adopting an animal";

    JPanel cards;

    JPanel toolArea;
    RegisterAnimalPanel registerAnimalPanel;
    SelectAnimalPanel selectAnimalPanel;
    LoadPanel loadPanel;
    SavePanel savePanel;
    ShowCharacteristicsPanel showCharacteristicsPanel;
    LogMedicationPanel logMedicationPanel;
    AdoptPanel adoptPanel;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    public AnimalShelterApp() {}

    public void init(Container pane) {
        initializeFields();
        initializeGraphics(pane);
    }

    private void initializeGraphics(Container pane) {

        pane.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        cards = new JPanel(new CardLayout());

        toolArea = new JPanel();
        toolArea.setLayout(new GridLayout(1, 0));
        toolArea.setSize(new Dimension(0, 0));
        createButtons();
        cards.add(toolArea, TOOL_AREA);

        registerAnimalPanel = new RegisterAnimalPanel(this);
        cards.add(registerAnimalPanel, REGISTER_ANIMAL_PANEL);

        selectAnimalPanel = new SelectAnimalPanel(this);
        cards.add(selectAnimalPanel, SELECT_ANIMAL_PANEL);

        loadPanel = new LoadPanel();
        cards.add(loadPanel, LOAD_PANEL);

        savePanel = new SavePanel();
        cards.add(savePanel, SAVE_PANEL);

        showCharacteristicsPanel = new ShowCharacteristicsPanel(this);
        cards.add(showCharacteristicsPanel, SHOW_CHARACTERISTICS_PANEL);

        logMedicationPanel = new LogMedicationPanel(this);
        cards.add(logMedicationPanel, LOG_MEDICATION_PANEL);

        adoptPanel = new AdoptPanel(this);
        cards.add(adoptPanel, ADOPT_PANEL);

        pane.add(cards, BorderLayout.CENTER);

    }

    private void initializeFields() {
        shelter = new AnimalShelter();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

    }

    //EFFECTS: creates tool buttons
    public void createButtons() {

        RegisterAnimalButton registerButton = new RegisterAnimalButton(this, toolArea);
        toolArea.add(registerButton);
        registerButton.addActionListener(new RegisterAnimalButtonClickHandler());

        SelectAnimalButton selectAnimalButton = new SelectAnimalButton(this, toolArea);
        toolArea.add(selectAnimalButton);
        selectAnimalButton.addActionListener(new SelectAnimalButtonClickHandler());

        LoadButton loadShelterButton = new LoadButton(this, toolArea);
        toolArea.add(loadShelterButton);
        loadShelterButton.addActionListener(new LoadToolClickHandler());

        SaveButton saveShelterButton = new SaveButton(this, toolArea);
        toolArea.add(saveShelterButton);
        saveShelterButton.addActionListener(new SaveToolClickHandler());

    }


    private class LoadToolClickHandler implements ActionListener {

        // CITATION: from DrawingEditor repository
        // EFFECTS: sets active tool to the delete tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, LOAD_PANEL);
            loadAnimalShelter();
        }
    }

    private void loadAnimalShelter() {
        jsonReader = new JsonReader(JSON_STORE);
        try {
            shelter = jsonReader.read();
            System.out.println("Loaded the animal shelter from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    private class SaveToolClickHandler implements ActionListener {

        // CITATION: from DrawingEditor repository
        // EFFECTS: sets active tool to the delete tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, SAVE_PANEL);
            saveAnimalShelter();
        }
    }

    // CITATION: got code from JsonSerializationDemo repository
    //EFFECTS: saves shelter to file
    private void saveAnimalShelter() {
        jsonWriter = new JsonWriter(JSON_STORE);

        try {
            jsonWriter.open();
            jsonWriter.write(shelter);
            jsonWriter.close();
            System.out.println("Saved the animal shelter to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private class RegisterAnimalButtonClickHandler implements ActionListener {

        // CITATION: from DrawingEditor repository
        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, REGISTER_ANIMAL_PANEL);
            registerAnimalPanel.processAnimalRegistration(shelter, cards);
        }
    }

    private class SelectAnimalButtonClickHandler implements ActionListener {

        // CITATION: from DrawingEditor repository
        @SuppressWarnings("checkstyle:LineLength")
        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, SELECT_ANIMAL_PANEL);
            selectAnimalPanel.processAnimalSelection(shelter, cards, showCharacteristicsPanel,
                    logMedicationPanel, adoptPanel);
        }
    }


}
