package view.components;

import controller.Controller;

/**
 * Defines possible strokes for this program
 * 
 * @author Henrique Moraes
 * 
 */
public enum Strokes {
    SOLID(Controller.RESOURCE.getString("Solid")),
    DASHED(Controller.RESOURCE.getString("Dashed")),
    DOTTED(Controller.RESOURCE.getString("Dotted")),
    DASH_AND_DOT(Controller.RESOURCE.getString("DashDot")),
    DOUBLE_LINE(Controller.RESOURCE.getString("DoubleLine"));

    private String myName;

    Strokes (String name) {
        myName = name;
    }

    @Override
    public String toString () {
        return myName;
    }
}
