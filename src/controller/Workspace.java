package controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import model.Paintable;
import model.Turtle;
import model.TurtleManager;
import parser.CustomCommand;
import parser.IParserProvider;
import parser.VariableManager;
import view.Canvas;
import view.ColorManager;

/**
 * Contains command information (history) for the particular workspace, holds turtle, allows saving of workspace
 * 
 * @author XuRui, Ziqiang Huang
 *
 */
public class Workspace  implements Paintable, IParserProvider {
    public static final String UNTITLED = "Untitled";    
    private Map<String, CustomCommand> myCommandMap;
    private Turtle myTurtle;
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
        myErrorResource = ResourceBundle.getBundle(Controller.DEFAULT_RESOURCE_PACKAGE + "error."+ "ErrorEnglish");
        myName = UNTITLED;
        myHistory = new ArrayList<String>();
        myVariables = new VariableManager();
        myUndoManager = new UndoManager();
        myPalette = new ColorManager();
        myCanvas = new Canvas(this);
        
    }

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
     * @return the Turtle Manager associated with this workspace
     */
    public TurtleManager getTurtleManager () {
        return myTurtleManager;
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
    public void showError (String message) {
        JOptionPane.showMessageDialog(null, message,
                                      myErrorResource.getString("ErrorTitle"),
                                      JOptionPane.ERROR_MESSAGE);
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
    
    public Canvas getCanvas()
    {
        return myCanvas;
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
