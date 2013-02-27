package view.labels;

import model.IState;

public class TempLocX extends TempNode{
    public String getValue(IState object){
        return ""+object.getCenter().x;
    }
}
