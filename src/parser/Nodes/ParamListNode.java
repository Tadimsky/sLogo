package parser.nodes;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import parser.IParserProvider;
import parser.nodes.exceptions.InvalidArgumentsException;

/**
 * This represents the start of a list of parameters in the tree. It contains a command and multiple parameters 
 * that the command will use.
 * 
 * @author Jonathan Schmidt
 *
 */
public class ParamListNode extends SyntaxNode {

    private List<SyntaxNode> myContents;
    
    public ParamListNode (Deque<SyntaxNode> queue) {        
        myContents = new ArrayList<SyntaxNode>();
        
        // pop off all the nodes until we get to a ListEndNode
        while (!queue.isEmpty()){
            SyntaxNode s = queue.pop();
            if (s instanceof ListEndNode)
            {
                return;
            }
            myContents.add(s);            
        }
    }
    
    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int val = 0;
        for (SyntaxNode sn : myContents)
        {
            val = sn.evaluate(w);
        }
        return val;
    }

    /**
     * @return the contents
     */
    public List<SyntaxNode> getContents () {
        return myContents;
    }

}
