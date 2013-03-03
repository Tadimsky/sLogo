package controller;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import model.Paintable;
import model.Turtle;
import parser.nodes.exceptions.InvalidArgumentsException;
import view.Canvas;

/**
 * Contains command information (history) for the particular workspace, holds turtle, allows saving of workspace
 * 
 * @author XuRui
 *
 */
public class Workspace  implements Paintable{
    public static final String UNTITLED = "Untitled";
    private static final String COMMAND_KEYWORD = "Command";
    private Map<String,Integer> myVariableMap;
    private Map<String,Integer> myCommandMap;
    private Turtle myTurtle;
    private ResourceBundle myErrorResource;
    private String myName;

    public Workspace (String name) {
        this();
        myName = name;
    }
    
    /**
     * Workspace constructor
     */
    public Workspace () {
        myCommandMap = new HashMap<String, Integer>();
        myTurtle = new Turtle();
        myErrorResource = ResourceBundle.getBundle(Controller.DEFAULT_RESOURCE_PACKAGE + "error."+ "ErrorEnglish");
        myName = UNTITLED;
        new Canvas(this);
    }

    public Map<String,Integer> getCommandMap () {
        return myCommandMap;
    }

    public void addVariable (String variable, int value) {
        myVariableMap.put(variable, value);
    }

    public void addCommand (String command, Integer syntax) {
        myCommandMap.put(command, syntax);
    }
    
    /**
     * Painting method that gets called by the Canvas
     */
    public void paint(Graphics2D pen){
        myTurtle.paint(pen);
    }
    
    /**
     * @return the Turtle associated with this workspace
     */
    public Turtle getTurtle () {
        return myTurtle;
    }
    
    /**
     * @return Name of this workspace
     */
    public String getName () {
        return myName;
    }

    public Integer getVariable (String var) throws InvalidArgumentsException
    {
        if (myVariableMap.containsKey(var))
        {
            return myVariableMap.get(var);
        }
        throw new InvalidArgumentsException("This variable does not exist.", "");
    }

    public void setVariable (String var, Integer val)
    {
        myVariableMap.put(var, val);
    }
   
    /**
     * save the variables and commands from the current workspace to a file
     */
    public void saveWorkspace (Writer w) {
        PrintWriter output = new PrintWriter(w);
        Map<String,Integer> commandMap = myCommandMap;
        for(String comName : commandMap.keySet()) {
            output.printf("%s %s\n", COMMAND_KEYWORD, comName);
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
    
}
