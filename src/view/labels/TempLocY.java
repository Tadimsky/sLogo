package view.labels;

import model.IState;

public class TempLocY extends TempNode{
    public String getValue(IState object){
        return ""+object.getCenter().y;
    }
}
