package parser.nodes;

import controller.Workspace;

public class CommandNode implements ISyntaxNode {

    public CommandNode () {
        
    }

    @Override
    public int evaluate (Workspace w) {        
        return 0;
    }

}
