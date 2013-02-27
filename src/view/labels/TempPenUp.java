package view.labels;

import model.IState;

public class TempPenUp extends TempNode{
    public String getValue(IState object){
        if (object.isPenWriting()) return "Yes";
        else return "No";
    }
}
