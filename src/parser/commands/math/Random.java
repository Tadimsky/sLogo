package parser.commands.math;

import java.util.Deque;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

/**
 * Implements the Random function.
 * Returns a random value with a maximum value provided by the parameter.
 * 
 * @author Jonathan Schmidt
 *
 */
public class Random extends UnaryNode{
    
    java.util.Random myRand;
   
    public Random (Deque<SyntaxNode> queue) {
        super(queue);        
        myRand = new java.util.Random(); 
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {
        int max = getArgument().evaluate(w);
        return myRand.nextInt(max);
    }

}
