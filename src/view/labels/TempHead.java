package view.labels;

import model.IState;

public class TempHead extends TempNode{
    public String getValue(IState object){
        return ""+object.getHeading();
    }
}
