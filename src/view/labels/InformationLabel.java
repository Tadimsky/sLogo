package view.labels;

import java.awt.Font;
import java.awt.GridBagConstraints;

public class InformationLabel extends LogoLabel {
    public static final int ANCHOR = GridBagConstraints.LINE_START;
    public static final double X_WEIGHT = 0;
    public static final double Y_WEIGHT = .3;
    private int myYIndex;
    public static final Font INFO_FONT = new Font("Century Gothic",Font.PLAIN,14);
    
    public InformationLabel(int y, String description,TempNode node){
        super(description,node);
        myYIndex = y; 
        setFont(INFO_FONT);
    }
    
    public GridBagConstraints getGridBagConstraints(){
        return super.getGridBagConstraint(X_WEIGHT, Y_WEIGHT, myYIndex, ANCHOR);
    }
    
    
}