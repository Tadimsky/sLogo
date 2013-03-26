package view.labels;

import java.awt.Font;
import java.awt.GridBagConstraints;

/**
 * Label that represents the title of the infoView
 * @author Henrique Moraes
 *
 */
@SuppressWarnings("serial")
public class TitleLabel extends LogoLabel {
    public static final int ANCHOR = GridBagConstraints.CENTER;
    public static final int X_WEIGHT = 1;
    public static final double Y_WEIGHT = .1;
    private static final int Y_INDEX = 0;
    public static final Font TITLE_FONT = new Font("Century Gothic", Font.BOLD, 16);

    /**
     * @param description String of this label
     */
    public TitleLabel (String description) {
        super(description, null);
        setFont(TITLE_FONT);
    }

    /**
     * @return The pre-defined constraints of this label
     */
    public GridBagConstraints getGridBagConstraints () {
        return super.getGridBagConstraint(X_WEIGHT, Y_WEIGHT, Y_INDEX, ANCHOR);
    }
}
