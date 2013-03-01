package controller;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import model.Paintable;
import model.Turtle;
import parser.Nodes.exceptions.InvalidArgumentsException;
import view.Canvas;


public class Workspace  implements Paintable{
    public static final String UNTITLED = "Untitled";
    
    private Map<String,Integer> myVariableMap;
    private Map<String,Integer> myCommandMap;
    private Turtle myTurtle;
    //private Canvas myCanvas;
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
        //myCanvas = new Canvas(this);
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
    
//  // WARNING THESE IF STATEMENTS ARE HORRIBLE, THIS SOLUTION SHOULD
//  // BE TEMPORARY IF ANYONE THINK OF SOMETHING EASIER PLEASE IMPLEMENT
//  private void checkTurtleBounds () {
//      Rectangle turtleBounds = myTurtle.getBounds();
//
//      if (turtleBounds.getMaxX() >= CANVAS_DIMENSION.width ||
//          turtleBounds.getMinX() <= 0) {
//          myTurtle.wrapOnX();
//      }
//      if (turtleBounds.getMinY() >= CANVAS_DIMENSION.height ||
//          turtleBounds.getMaxY() <= 0) {
//          myTurtle.wrapOnY();
//      }
//  }

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
}
