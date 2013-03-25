package parser.commands.other;

import java.util.Deque;
import parser.IParserProvider;
import parser.VariableManager;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * Implements the Repeat control structure.
 * Runs the specified command list a certain number of times.
 * The number of times to run is provided by the left node's value.
 * Assigns the current run count to the :repcount variable
 * 
 * @author Jonathan Schmidt
 * 
 */
public class Repeat extends BinaryNode {

    public Repeat (Deque<SyntaxNode> queue) {
        super(queue);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        VariableManager vm = w.getVariables();
        vm.createVariableScope("Repeat");
        int times = getLeft().evaluate(w);

        int val = 0;
        for (int i = 0; i < times; i++)
        {
            vm.setVariable(":repcount", i + 1);
            val = getRight().evaluate(w);
        }
        vm.revertVariableScope();
        return val;
    }

}
