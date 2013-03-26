package view.labels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import model.IState;
import parser.nodes.exceptions.InvalidArgumentsException;


@SuppressWarnings("serial")
public abstract class LogoLabel extends JLabel {
    private String myDescription;
    private ILabelInformation myTurtleQuery;
    public static final Color LETTER_COLOR = Color.DARK_GRAY;

    protected LogoLabel (String description, ILabelInformation query) {
        myTurtleQuery = query;

        myDescription = description;
        setText();
        setForeground(LETTER_COLOR);
    }

    /**
     * Defines the gridBagConstraints through a function instead of
     * dening constraints over and over again
     * 
     * @return the correct constraints for this label
     */
    protected GridBagConstraints
            getGridBagConstraint (double weightx, double weighty, int y, int anchor) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = y;
        c.weightx = weightx;
        c.weighty = weighty;
        c.anchor = anchor;
        c.insets = new Insets(0, 5, 0, 0);
        return c;
    }

    @Override
    public void setText (String text) {
        super.setText(myDescription + text);
    }

    /**
     * Appends a double at the end of the description
     * 
     * @param text
     */
    public void setText (double text) {
        setText("" + text);
    }

    /**
     * Appends an int at the end of the description
     * 
     * @param text
     */
    public void setText (int text) {
        setText("" + text);
    }

    /**
     * Appends a boolean at the end of the description
     * 
     * @param text
     */
    public void setText (boolean text) {
        if (text) {
            setText("Yes");
        }
        else {
            setText("No");
        }
    }

    /**
     * solely displays the description
     * 
     * @param text
     */
    public void setText () {
        setText("");
    }

    /**
     * Takes an IState turtle and extracts the respective query for the label
     * If the label should display the location, it will contain a command
     * extended from basic control that will return the x position
     * 
     * @param object IState turtle to have the query evaluated
     */
    public void setText (IState object) {
        if (myTurtleQuery != null) {
            try {
                setText(myTurtleQuery.evaluateFromTurtle(object));
            }
            catch (InvalidArgumentsException e) {
                // swallow
            }
        }
        else {
            setText();
        }
    }
}
