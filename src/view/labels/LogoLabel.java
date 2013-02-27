package view.labels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import model.IState;

public abstract class LogoLabel extends JLabel {
    private String myDescription;
    private TempNode myNode;
    public static final Color LETTER_COLOR = Color.DARK_GRAY;
    
    protected LogoLabel(String description,TempNode node){
        //Will change!!
        myNode = node;
        
        myDescription = description;
        setText();
        setForeground(LETTER_COLOR);
    }
    protected GridBagConstraints 
    getGridBagConstraint(double weightx,double weighty, int y,int anchor){
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = y;
        c.weightx = weightx;
        c.weighty = weighty;
        c.anchor = anchor;
        c.insets = new Insets(0,5,0,0);
        return c;
    }
    
    @Override
    public void setText(String text){
        System.out.println("im here and I got "+text);
        super.setText(myDescription+text);
    }
    
    public void setText(double text){
        setText(""+text);
    }
    
    public void setText(int text){
        setText(""+text);
    }
    
    public void setText(){
        setText("");
    }
    
    public void setText(IState object){
        if(myNode!=null){
            System.out.println("value is "+myNode.getValue(object));
            setText(myNode.getValue(object));
        }
        else
            setText();
    }
}
