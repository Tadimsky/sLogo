package parser.commands.turtle.commands;

import java.util.Deque;
import java.util.Random;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Backward function.
 * Moves the turtles backwards an amount specified by the first argument.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class Nothing extends UnaryNode {

    /**
     * Creates Backward Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public Nothing (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            int move = 10 - r.nextInt(20);
            int turn = 10 - r.nextInt(20);
            w.getTurtleManager().execute("move", move);
            w.getTurtleManager().execute("turn", move);

        }

        return 0;
    }

}
