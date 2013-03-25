package parser.commands.turtle.commands;

import java.util.Deque;
import parser.IParserProvider;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;


public class ClearStamps extends BasicControl {

    public ClearStamps (Deque<SyntaxNode> stack) {
        super(stack);
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        w.getTurtle().clearStamp();
        return 0;
    }
}
