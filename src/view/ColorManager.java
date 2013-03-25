package view;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;


/**
 * Class to manage the storing of colors.
 * Allows users to add colors and to retrieve colors.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class ColorManager {

    private static final Color[] DEFAULT_COLORS = new Color[] { Color.WHITE,
                                                               Color.BLACK,
                                                               Color.RED,
                                                               Color.GREEN,
                                                               Color.BLUE,
                                                               Color.DARK_GRAY,
                                                               Color.LIGHT_GRAY,
                                                               Color.PINK
    };

    private Map<Integer, Color> myPalette;

    public ColorManager () {
        myPalette = new HashMap<Integer, Color>();
        for (int i = 0; i < DEFAULT_COLORS.length; i++)
        {
            setColor(i + 1, DEFAULT_COLORS[i]);
        }
    }

    public void setColor (int index, Color c)
    {
        myPalette.put(index, c);
    }

    public Color getColor (int index)
    {
        Color c = myPalette.get(index);
        return (c == null) ? Color.WHITE : c;
    }

    public Map<Integer, Color> getColorMap ()
    {
        return myPalette;
    }

}
