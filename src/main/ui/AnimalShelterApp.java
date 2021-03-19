package ui;

import model.AnimalShelter;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tools.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimalShelterApp extends JFrame {

    protected static final String JSON_STORE = "./data/animalshelter.json";
    protected AnimalShelter shelter;
    protected Scanner input;
    protected JsonWriter jsonWriter;
    protected JsonReader jsonReader;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    public static final int BORDER_WIDTH = 20;

    protected JFrame frame;

    private List<Tool> tools;

    public AnimalShelterApp() {

        super("Animal Shelter");
        initializeFields();
        initializeGraphics();
    }

    private void initializeGraphics() {
        frame = new JFrame();

        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createTools();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeFields() {
        input = new Scanner(System.in);
        shelter = new AnimalShelter();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        tools = new ArrayList<Tool>();
    }

    //EFFECTS: creates tool buttons
    public void createTools() {
        JPanel toolArea = new JPanel();
        toolArea.setLayout(new GridLayout(1, 0));
        toolArea.setSize(new Dimension(0, 0));
        add(toolArea, BorderLayout.CENTER);

        RegisterAnimalTool registerButton = new RegisterAnimalTool(this, toolArea);
        SelectAnimalTool selectAnimalButton = new SelectAnimalTool(this, toolArea);
        LoadTool loadShelterButton = new LoadTool(this, toolArea);
        SaveTool saveShelterButton = new SaveTool(this, toolArea);

        tools.add(registerButton);
        tools.add(selectAnimalButton);
        tools.add(saveShelterButton);
        tools.add(loadShelterButton);
    }



}
