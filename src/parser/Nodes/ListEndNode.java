package parser.nodes;

import java.util.Deque;
import parser.nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

public class ListEndNode extends TokenNode {
    
    public ListEndNode (String token) {
        super(token);        
    }
    
    public ListEndNode (Deque<SyntaxNode> stack)
    {        
        this(((TokenNode)stack.pop()).getToken());
    } 

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {        
        return 0;
    }

}
