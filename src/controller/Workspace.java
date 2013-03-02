package controller;

import java.awt.Graphics2D;
import java.io.BufferedReader;
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


public class Workspace  implements Paintable{
    public static final String UNTITLED = "Untitled";
    private static final String VARIABLE_KEYWORD = "Variable";
    private static final String COMMAND_KEYWORD = "Command";
    private Map<String,Integer> myVariableMap;
    private Map<String,Integer> myCommandMap;
    private Turtle myTurtle;
    private ResourceBundle myResource;
    private String myName;

    public Workspace (String name) {
        this();
        myName = name;
    }
    
    public Workspace () {
        // test save and load
        myVariableMap = new HashMap<String, Integer>();
        myCommandMap = new HashMap<String, Integer>();
        myTurtle = new Turtle();
        myResource = ResourceBundle.getBundle(Controller.DEFAULT_RESOURCE_PACKAGE + "English");
        myName = UNTITLED;
        new Canvas(this);
        
    }

    public void handleCommand () {
        // TODO
    }

    public Map<String,Integer> getVariableMap () {
        return myVariableMap;
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
     * load a file of variable and command to a current workspace
     */
    public void loadWorkspace (Reader r) {
        try {
            BufferedReader input = new BufferedReader(r);
            String line = input.readLine();
            while (line != null) {
                String[] str = line.split(" ");
                if(str[0].equals(VARIABLE_KEYWORD)) { 
                    myVariableMap.put(str[1], Integer.parseInt(str[2]));
                }
                if(str[0].equals(COMMAND_KEYWORD)) { 
                    myCommandMap.put(str[1], Integer.parseInt(str[2]));
                }
                line = input.readLine();
            }
        }
        catch (IOException e) {
            showError(e.toString());
        }
    }
    
    /**
     * save the variables and commands from the current workspace to a file
     */
    public void saveWorkspace (Writer w) {
        PrintWriter output = new PrintWriter(w);
        
        Map<String,Integer> varMap = myVariableMap;
        Map<String,Integer> comMap = myCommandMap;
        
        for(String varName : varMap.keySet()) {
            output.printf("%s %s %s\n", VARIABLE_KEYWORD, varName, varMap.get(varName));
        }
        
        for(String comName : comMap.keySet()) {
            output.printf("%s %s %s \n", COMMAND_KEYWORD, comName, varMap.get(comName));
        }
        
        output.flush();
        output.close();
    }
    
    /**
     * Display any string message in a popup error dialog.
     */
    public void showError (String message) {
        JOptionPane.showMessageDialog(null, message,
                                      myResource.getString("ErrorTitle"),
                                      JOptionPane.ERROR_MESSAGE);
    }  
    
}
