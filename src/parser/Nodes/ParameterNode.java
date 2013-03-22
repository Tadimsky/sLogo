package parser.nodes;

import java.util.ArrayList;
import java.util.List;


/**
 * This node is the super class for all nodes that have parameters.
 * These would be mostly any kind of function.
 * 
 * @author Jonathan Schmidt
 * 
 */
public abstract class ParameterNode extends SyntaxNode {
    private List<SyntaxNode> myNodes = new ArrayList<SyntaxNode>();
    
    protected void addParameter(int index, SyntaxNode node)
    {
        myNodes.add(index, node);
    }
    
    protected SyntaxNode getParameter(int index)
    {
        return myNodes.get(index);
    }
    
    public int getParameterCount()
    {
        return myNodes.size();
    }
}
