package parser.commands.turtle.commands;

import java.util.Deque;
import parser.Nodes.SimpleNode;
import parser.Nodes.SyntaxNode;

public abstract class BasicControl extends SimpleNode {
    
    public BasicControl (Deque<SyntaxNode> stack)
    {
        // remove the token
        stack.pop();
    }
}
