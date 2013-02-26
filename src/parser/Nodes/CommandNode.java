package parser.nodes;

import controller.Workspace;

public class CommandNode extends SyntaxNode {

    public CommandNode () {
        
    }

    @Override
    public int evaluate (Workspace w) {        
        return 0;
    }

}
