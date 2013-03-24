package controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import controller.support.IError;
import model.Paintable;
import model.Turtle;
import model.TurtleManager;
import parser.CustomCommand;
import parser.IParserProvider;
import parser.VariableManager;
import view.Canvas;
import view.ColorManager;
import view.components.Strokes;

/**
 * Contains command information (history) for the particular workspace, holds turtle, allows saving of workspace
 * 
 * @author XuRui, Ziqiang Huang, Henrique Moraes
 *
 */
public class Workspace  extends Observable implements Paintable, IParserProvider, IError {
    public static final String UNTITLED = "Untitled";    
    private Map<String, CustomCommand> myCommandMap;
    private ResourceBundle myErrorResource;
    private String myName;
    
    private List<String> myHistory;
    private VariableManager myVariables;
    private UndoManager myUndoManager;
    private ColorManager myPalette;
    private Canvas myCanvas;
    private TurtleManager myTurtleManager;

    public Workspace (String name) {
        this();
        myName = name;         
    }
    
    /**
     * Workspace constructor
     */
    public Workspace () {
        myCommandMap = new HashMap<String, CustomCommand>();
        myTurtleManager = new TurtleManager();
        myErrorResource = Controller.RESOURCE_ERROR;
        myName = UNTITLED;
        myHistory = new ArrayList<String>();
        myVariables = new VariableManager();
        myUndoManager = new UndoManager();
        myPalette = new ColorManager();
        myCanvas = new Canvas(this); 
        
        testVariables();
    }
    
    private void testVariables(){
      //myVariables.createVariableScope("local");
        for (int i = 0; i<10; i++){
            myVariables.setVariable("var"+i, i);
        }
        updateInformation();
    }

    /**
     * @return Map with commands for this workspace
     */
    public Map<String,CustomCommand> getCommandMap () {
        return myCommandMap;
    }
 
    /**
     * Painting method that gets called by the Canvas
     */
    public void paint(Graphics2D pen){
        myTurtleManager.paint(pen);
    }
    
    /**
     * Updates the state of this workspace for the user
     */
    public void update(){
        myTurtleManager.update();
    }
    
    /**
     * Notifies objects that manage visual representation of variables, commands,
     * and expression related to this workspace
     */
    public void updateInformation() {
        setChanged();
        notifyObservers();
    }
    
    /**
     * @return the active Turtles associated with this workspace
     */
    public Map<Integer, Turtle> getTurtles () {
        return myTurtleManager.getTurtles();
    }
    
    /**
     * @return All the Turtles associated with this workspace
     */
    public Map<Integer, Turtle> getAllTurtles () {
        return myTurtleManager.getAllTurtles();
    }
    
    /**
     * Activates the turtle specified by the index
     */
    public void activateTurtle(int index) {
        myTurtleManager.activateTurtle(index);
        update();
    }
    
    /**
     * Activates the turtle specified by the index
     */
    public void deactivateTurtle(int index) {
        myTurtleManager.deactivateTurtle(index);
        update();
    }
    
    /**
     * @return Name of this workspace
     */
    public String getName () {
        return myName;
    }
    
    public VariableManager getVariables()
    {
        return myVariables;
    }
    
    /**
     * save the variables and commands from the current workspace to a file
     */
    
    //Edit to save workspace preferences as well
    public void saveWorkspace (Writer w) {
        PrintWriter output = new PrintWriter(w);
        for(String comName : myHistory) {
            output.printf("%s\n", comName);
        }
        output.flush();
        output.close();
    }
    
    /**
     * Display any string message in a popup error dialog.
     */
    @Override
    public void showError (String message) {
        JOptionPane.showMessageDialog(null, message,
                                      myErrorResource.getString("ErrorTitle"),
                                      JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Adds a new turtle with the specified index to the workspace
     * @param index
     */
    public void addTurtle(int index) {
        myTurtleManager.addNew(index);
        update();
    }

    @Override
    public void addCommand (CustomCommand com) {
        myCommandMap.put(com.getName(), com);
        
    }

    @Override
    public CustomCommand getCommand (String command) {
        
        return myCommandMap.get(command.toLowerCase());
    }  
    
    public void addHistory(String s)
    {
        myHistory.add(s);
    }
    
    @Override
    public int setBackground(int colorIndex) {        
        myCanvas.setBackgroundColor(myPalette.getColor(colorIndex));
        return colorIndex;
    }
    
    public void setHighlighted(boolean active){
        myTurtleManager.setHighlighted(active);
    }
    
    public boolean getHighlighted(){
        return myTurtleManager.getHighlighted();
    }
    
    /**
     * @return Canvas associated with this workspace
     */
    public Canvas getCanvas()
    {
        return myCanvas;
    }
    
    /**
     * @param o observer to be added to the turtle manager
     */
    public void addTurtleObserver(Observer o){
        myTurtleManager.addObserver(o);
    }
    
    /**
     * @param stroke stroke to be set on active turtles of this workspace
     */
    public void setStroke(Stroke stroke) {
        myTurtleManager.setStroke(stroke);
    }
    
    /**
     * @param type type of stroke to be set on active turtles of this workspace
     * @param thickness thickness of the stroke
     */
    public void setStroke(Strokes type, float thickness) {
        myTurtleManager.setStroke(myCanvas.createStroke(type, thickness));
    }

    @Override
    public ColorManager getColors () { 
        return myPalette;
    }

    @Override
    public void addColor (int colorIndex, Color color) {
        // TODO Auto-generated method stub   
    }
    
}
