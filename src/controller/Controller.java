package controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import view.Canvas;
import view.Window;

public class Controller {

//    private static final String WORKSPACE_NAME = "Workspace ";
//    private int workspaceIndex = 1;

    public Controller () {
        //myWorkspaces = new ArrayList<Workspace>();
        //myWindow = new Window(this);      
    }
    
    /**
     * This method is called whenever the run button or enter is pressed 
     * @param command string in input Text Field
     */
    public void processCommand(String command, Canvas canvas){
        System.out.println(command);
        canvas.getTurtle().setColor(Color.RED);
    }
}
