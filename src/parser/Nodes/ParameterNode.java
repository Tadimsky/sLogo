package parser.nodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This node is the super class for all nodes that have parameters.
 * These would be mostly any kind of function.
 * 
 * @author Jonathan Schmidt
 * 
 */
public abstract class ParameterNode extends SyntaxNode {
    private Map<Integer, SyntaxNode> myNodes = new HashMap<Integer, SyntaxNode>();

    protected void addParameter (int index, SyntaxNode node)
    {
        myNodes.put(index, node);
    }

    protected SyntaxNode getParameter (int index)
    {
        return myNodes.get(index);
    }

    public int getParameterCount ()
    {
        return myNodes.size();
    }
}
