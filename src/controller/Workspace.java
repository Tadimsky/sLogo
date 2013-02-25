package controller;

import javax.swing.JComponent;
import view.Canvas;
import view.InformationView;
import model.Turtle;


public class Workspace extends JComponent {
    private Canvas myCanvas;
    private Turtle myTurtle;
    private String myName;  


    public Workspace(String name) {
        myTurtle = new Turtle();
        myCanvas = new Canvas(this);
        myName = name;
    }

    public void handleCommand() {
        // TODO
    }
    
    public String getName(){
        return myName;
    }

    public Canvas getCanvas() {
        return myCanvas;
    }

    public void setCanvas(Canvas canvas) {
        myCanvas = canvas;
    }

//    public InformationView getInformationView() {
//        return myInformationView;
//    }
//
//    public void setInformationView(InformationView informationView) {
//        myInformationView = informationView;
//    }

    public Turtle getTurtle() {
        return myTurtle;
    }

    public void setTurtle(Turtle turtle) {
        myTurtle = turtle;
    }
    
    public int getVariable(String var)
    {
        return 0;
    }
    
    public void setVariable(String var, Integer val)
    {
        
    }
    
    

}
