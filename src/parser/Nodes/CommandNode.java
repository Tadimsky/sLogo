package parser.nodes;

import java.util.Deque;
import controller.Workspace;

public class CommandNode extends SimpleNode {

    private String myName;

    public CommandNode (String val) {
        this.myName = val;
    }
    
    public CommandNode (Deque<SyntaxNode> stack)
    {        
        this(((TokenNode)stack.pop()).getToken());
    }  
    
    public String getName()
    {
        return myName;
    }

    @Override
    public int evaluate (Workspace w) {        
        return 0;
    }

}
