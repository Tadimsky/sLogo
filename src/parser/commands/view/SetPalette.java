package parser.commands.view;

import java.awt.Color;
import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.ParameterNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the SetPalette function.
 * Creates colors and adds them to the color manager
 * 
 * @author Jonathan Schmidt
 * 
 */
public class SetPalette extends ParameterNode {

    private static final int MAX_COLOR_VAL = 255;
    private static final String RGB_VALUE_FORMAT = "RGB values must be a number between 0 and 255";
    private static final int BLUE = 2;
    private static final int GREEN = 1;
    private static final int RED = 0;
    private static final int NUM_COLORS = 3;

    /**
     * Creates Random Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public SetPalette (Deque<SyntaxNode> queue) {
        addParameter(0, queue.pop());
        addParameter(RED + 1, queue.pop());
        addParameter(GREEN + 1, queue.pop());
        addParameter(BLUE + 1, queue.pop());
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int[] colors = new int[NUM_COLORS];
        int colorIndex = 0;
        colorIndex = getParameter(0).evaluate(w);
        colors[RED] = getParameter(RED + 1).evaluate(w);
        colors[GREEN] = getParameter(GREEN + 1).evaluate(w);
        colors[BLUE] = getParameter(BLUE + 1).evaluate(w);

        for (int i = 0; i < NUM_COLORS; i++) {
            if (0 > colors[i] || colors[i] > MAX_COLOR_VAL) {
                throw new InvalidArgumentsException(RGB_VALUE_FORMAT, "");
            }
        }
        w.getColors().setColor(colorIndex, new Color(colors[RED], colors[GREEN], colors[BLUE]));
        return colorIndex;
    }

}
