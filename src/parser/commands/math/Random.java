package parser.commands.math;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Random function.
 * Returns a random value with a maximum value provided by the parameter.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class Random extends UnaryNode {

    private java.util.Random myRand;

    /**
     * Creates Random Command Node
     * 
     * @param queue The list of nodes that come before this command
     */
    public Random (Deque<SyntaxNode> queue) {
        super(queue);
        myRand = new java.util.Random();
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int max = getArgument().evaluate(w);
        return myRand.nextInt(max);
    }

}
