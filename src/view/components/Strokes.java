package view.components;

/**
 * Defines possible strokes for this program
 * @author Henrique Moraes
 *
 */
public enum Strokes {
    SOLID ("Solid"), 
    DASHED ("Dashed"), 
    DOTTED ("Dotted"), 
    DASH_AND_DOT ("Dash And Dot"), 
    DOUBLE_LINE ("Double Line");
    
    private String myName;
    Strokes(String name) {
        myName = name;
    }
    
    @Override
    public String toString() {
        return myName;
    }
}
