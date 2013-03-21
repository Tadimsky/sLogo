package parser.commands.other;

import java.util.Deque;
import parser.IParserProvider;
import parser.VariableManager;
import parser.nodes.BinaryNode;
import parser.nodes.ListNode;
import parser.nodes.SyntaxNode;
import parser.nodes.TokenNode;
import parser.nodes.VariableNode;
import parser.nodes.exceptions.InvalidArgumentsException;

/**
 * Implements the For loop control structure.
 * Runs the specified command list a certain number of times.
 * The start, end and increment values of the loop are given in the first list.
 * The current loop number is assigned to the specified variable name. 
 * 
 * @author Jonathan Schmidt
 *
 */
public class For extends BinaryNode {

    public For (Deque<SyntaxNode> queue) {
        super(queue);    
        
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        checkSyntax();
        
        VariableManager vm = w.getVariables();
        vm.createVariableScope("For");
        
        // evaluates the list and returns the final value
        ListNode ln = ((ListNode)getLeft());
        
        int start = ln.getContents().get(1).evaluate(w);
        int end = ln.getContents().get(2).evaluate(w);
        int increment = ln.getContents().get(3).evaluate(w);
        
        String varName = ((VariableNode)((ListNode)getLeft()).getContents().get(0)).getName();
        
        int val = 0;
        for (int i = start; i < end; i+=increment)
        {
            vm.setVariable(varName, i);            
            val = getRight().evaluate(w);            
        }
        vm.revertVariableScope();
        return val;
    }
    
    private void checkSyntax() throws InvalidArgumentsException
    {
        if (!(getLeft() instanceof ListNode))
        {
            throw new InvalidArgumentsException("First argument must be a list.", "");
        }        
        
        if (!(getRight() instanceof ListNode))
        {
            throw new InvalidArgumentsException("Second argument must be a list of commands to run.", "");
        }  
        
        // Check the First List
        ListNode ln = ((ListNode)getLeft());
        if (ln.getContents().size() != 4) {
            throw new InvalidArgumentsException("The first list must contain a variable, the starting value, the end value and the amount to increment by.", "");
        }
        if (!(ln.getContents().get(0) instanceof VariableNode)){
            throw new InvalidArgumentsException("First argument in first list must be a variable.", "");
        }
    }

}
