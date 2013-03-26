package parser.nodes;

import java.util.HashMap;
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

    protected void addParameter (int index, SyntaxNode node) {
        myNodes.put(index, node);
    }

    protected SyntaxNode getParameter (int index) {
        return myNodes.get(index);
    }

    /**
     * Returns the number of parameters that this parameter node contains. 
     * @return
     */
    public int getParameterCount () {
        return myNodes.size();
    }
}
