package parser.commands.turtle.commands;

import java.util.Deque;
import controller.Workspace;
import parser.nodes.SimpleNode;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;

public abstract class BasicControl extends SimpleNode {
    
    public BasicControl (Deque<SyntaxNode> stack)
    {
        // remove the token
        stack.pop();
    }
}
