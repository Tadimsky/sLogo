package controller;

import javax.swing.JComponent;
import view.Canvas;
import view.InformationView;
import model.Turtle;


public class Workspace extends JComponent {
//    private Canvas myCanvas;
//    private InformationView myInformationView;
    private Turtle myTurtle;

    public Workspace() {
//        setMyCanvas(new Canvas());
//        setMyInformationView(new InformationView());
        setMyTurtle(new Turtle());
    }

    public void handleCommand() {
        // TODO
    }

//    public Canvas getMyCanvas() {
//        return myCanvas;
//    }
//
//    public void setMyCanvas(Canvas myCanvas) {
//        this.myCanvas = myCanvas;
//    }
//
//    public InformationView getMyInformationView() {
//        return myInformationView;
//    }
//
//    public void setMyInformationView(InformationView myInformationView) {
//        this.myInformationView = myInformationView;
//    }

    public Turtle getTurtle() {
        return myTurtle;
    }

    public void setMyTurtle(Turtle turtle) {
        myTurtle = turtle;
    }

}
