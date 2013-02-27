package parser.nodes;

import parser.nodes.exceptions.InvalidArgumentsException;
import controller.Workspace;

public class ListEndNode extends SimpleNode {

    public ListEndNode (String tok) {
        
    }

    @Override
    public int evaluate (Workspace w) throws InvalidArgumentsException {        
        return 0;
    }

}
