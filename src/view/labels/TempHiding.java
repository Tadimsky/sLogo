package view.labels;

import model.IState;

public class TempHiding extends TempNode{
    public String getValue(IState object){
        if (object.isHiding()) return "Yes";
        else return "No";
    }
}
