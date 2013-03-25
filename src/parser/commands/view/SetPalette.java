package parser.commands.view;

import java.awt.Color;
import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.ParameterNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


public class SetPalette extends ParameterNode {

    private static final int BLUE = 2;
    private static final int GREEN = 1;
    private static final int RED = 0;

    public SetPalette (Deque<SyntaxNode> queue) {
        addParameter(0, queue.pop());
        addParameter(RED + 1, queue.pop());
        addParameter(GREEN + 1, queue.pop());
        addParameter(BLUE + 1, queue.pop());
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int[] colors = new int[3];
        int colorIndex = 0;
        try {
            colorIndex = getParameter(0).evaluate(w);
            colors[RED] = getParameter(RED + 1).evaluate(w);
            colors[GREEN] = getParameter(GREEN + 1).evaluate(w);
            colors[BLUE] = getParameter(BLUE + 1).evaluate(w);
        }
        catch (Exception e)
        {
            throw new InvalidArgumentsException("Invalid input for the SetPalette command.", "");
        }
        for (int i = 0; i < 3; i++)
        {
            if (0 > colors[i] || colors[i] > 255)
                throw new InvalidArgumentsException(
                                                    "RGB values must be a number between 0 and 255",
                                                    "");
        }
        w.getColors().setColor(colorIndex, new Color(colors[RED], colors[GREEN], colors[BLUE]));
        return colorIndex;
    }

}
