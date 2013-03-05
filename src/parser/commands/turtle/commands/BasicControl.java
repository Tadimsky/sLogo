package parser.commands.turtle.commands;

import java.util.Deque;
import parser.nodes.SimpleNode;
import parser.nodes.SyntaxNode;

public abstract class BasicControl extends SimpleNode {
    
    public BasicControl (Deque<SyntaxNode> stack)
    {
        // remove the token
        if (stack != null)
        stack.pop();
    }
    
}
