package view.labels;

import java.awt.Font;
import java.awt.GridBagConstraints;
import view.ILabelInformation;

/**
 * Displays information with the appropriate turtle query
 * @author Henrique Moraes
 *
 */
@SuppressWarnings("serial")
public class InformationLabel extends LogoLabel {
    public static final int ANCHOR = GridBagConstraints.LINE_START;
    public static final double X_WEIGHT = 0;
    public static final double Y_WEIGHT = .3;
    private int myYIndex;
    public static final Font INFO_FONT = new Font("Century Gothic", Font.PLAIN, 14);

    /**
     * 
     * @param y index position
     * @param description description of this label
     * @param query turtle query to obtain info from the turtle
     */
    public InformationLabel (int y, String description, ILabelInformation query) {
        super(description, query);
        myYIndex = y;
        setFont(INFO_FONT);
    }

    /**
     * @return Pre-defined gridBag constraints for this Label
     */
    public GridBagConstraints getGridBagConstraints () {
        return super.getGridBagConstraint(X_WEIGHT, Y_WEIGHT, myYIndex, ANCHOR);
    }

}
