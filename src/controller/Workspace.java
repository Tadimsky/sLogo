package controller;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import model.ColorManager;
import model.Paintable;
import model.Turtle;
import model.TurtleManager;
import parser.CustomCommand;
import parser.IParserProvider;
import parser.VariableManager;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;
import view.Canvas;
import view.components.Strokes;
import controller.support.IError;


/**
 * Contains command information (history) for the particular workspace, holds turtle, allows saving
 * of workspace
 * 
 * @author XuRui, Ziqiang Huang, Henrique Moraes
 * 
 */
public class Workspace extends Observable implements Paintable, IParserProvider, IError, Serializable {
    public static final String UNTITLED = "Untitled";
    private Map<String, CustomCommand> myCommandMap;
    private ResourceBundle myErrorResource;
    private String myName;

    private List<String> myHistory;
    private VariableManager myVariables;
    private ColorManager myPalette;
    private Canvas myCanvas;
    private TurtleManager myTurtleManager;
    private WSUndoManager myUndoManager;

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
        new WSUndoManager();
        myPalette = new ColorManager();
        myCanvas = new Canvas(this);
    }

    /**
     * @return Map with commands for this workspace
     */
    public Map<String, CustomCommand> getCommandMap () {
        return myCommandMap;
    }

    /**
     * Painting method that gets called by the Canvas
     */
    @Override
    public void paint (Graphics2D pen) {
        myTurtleManager.paint(pen);
    }

    /**
     * Updates the state of this workspace for the user
     */

    public void update () {
        myTurtleManager.update();
    }

    @Override
    public Turtle getTurtle () {
        return myTurtleManager.getCurrent();
    }

    /**
     * Notifies objects that manage visual representation of variables, commands,
     * and expression related to this workspace
     */
    public void updateInformation () {
        setChanged();
        notifyObservers();
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
    public void activateTurtle (int index) {
        myTurtleManager.activate(index);
        update();
    }

    /**
     * Activates the turtle specified by the index
     */
    public void deactivateTurtle (int index) {
        myTurtleManager.deactivateTurtle(index);
        update();
    }

    /**
     * @return Name of this workspace
     */
    public String getName () {
        return myName;
    }

    @Override
    public VariableManager getVariables () {
        return myVariables;
    }

    /**
     * save the variables and commands from the current workspace to a file
     */

    // Edit to save workspace preferences as well
    public void saveWorkspace (Writer w) {
        PrintWriter output = new PrintWriter(w);
        for (String comName : myHistory) {
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
        JOptionPane.showMessageDialog(null, message, myErrorResource.getString("ErrorTitle"),
                                      JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Adds a new turtle with the specified index to the workspace
     * 
     * @param index
     */
    public void addTurtle (int index) {
        myTurtleManager.addNew(index);
        update();
    }

    @Override
    public void addCommand (CustomCommand com) {
    	//myUndoManager.addEdit(new UndoableEdit());
        myCommandMap.put(com.getName(), com);

    }

    @Override
    public CustomCommand getCommand (String command) {

        return myCommandMap.get(command.toLowerCase());
    }

    public void addHistory (String s) {
        myHistory.add(s);
    }
    
    public List<String> getHistory () 
    {
        return myHistory;
    }

    @Override
    public int setBackground (int colorIndex) {
        myCanvas.setBackgroundColor(myPalette.getColor(colorIndex));
        return colorIndex;
    }

    public void setHighlighted (boolean active) {
        myTurtleManager.setHighlighted(active);
    }

    public void setImage (String path) {
        myTurtleManager.setImage(path);
    }

    public boolean getHighlighted () {
        return myTurtleManager.getHighlighted();
    }

    /**
     * @return Canvas associated with this workspace
     */
    public Canvas getCanvas () {
        return myCanvas;
    }

    /**
     * @param o observer to be added to the turtle manager
     */
    public void addTurtleObserver (Observer o) {
        myTurtleManager.addObserver(o);
    }

    /**
     * @param stroke stroke to be set on active turtles of this workspace
     */
    public void setStroke (Stroke stroke) {
        myTurtleManager.setStroke(stroke);
    }

    /**
     * @param type type of stroke to be set on active turtles of this workspace
     * @param thickness thickness of the stroke
     */
    public void setStroke (Strokes type, float thickness) {
        myTurtleManager.setStroke(myCanvas.createStroke(type, thickness));
    }

    @Override
    public ColorManager getColors () {
        return myPalette;
    }

    public void execute (List<SyntaxNode> commands) {
        try {
            for (SyntaxNode node : commands) {
                int returnValue = node.evaluate(this);
                System.out.printf("my return value is %d", returnValue);
            }
        }
        catch (NullPointerException ne) {
            showError("You entered an invalid command.");
        }
        catch (InvalidArgumentsException e) {
            showError("Invalid Input: " + e.getMessage());
        }
    }

    @Override
    public TurtleManager getTurtleManager () {
        return myTurtleManager;
    }

    public void setStrokeType (Strokes s) {
        myTurtleManager.setStrokeType(s);

    }

    public void addTurtleImage (int index, String imageDir) {
        myTurtleManager.addTurtleImage(index, imageDir);

    }

    public WSUndoManager getUndoManager(){
    	return myUndoManager;
    }
    

}
