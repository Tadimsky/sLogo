package parser.nodes;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import parser.IParserProvider;
import parser.nodes.exceptions.InvalidArgumentsException;


/**
 * This represents a list in the tree. It contains all the nodes
 * that are in the list.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class ListNode extends SyntaxNode {

    private List<SyntaxNode> myContents;

    /**
     * Creates a List node from a queue of parameters.
     * Reads in all the parameters until a list end node is found.
     * 
     * @param queue The list of parameters.
     */
    public ListNode (Deque<SyntaxNode> queue) {
        myContents = new ArrayList<SyntaxNode>();

        // pop off all the nodes until we get to a ListEndNode
        while (!queue.isEmpty()) {
            SyntaxNode s = queue.pop();
            if (s instanceof ListEndNode) return;
            myContents.add(s);
        }
    }

    @Override
    public int evaluate (IParserProvider w) throws InvalidArgumentsException {
        int val = 0;
        for (SyntaxNode sn : myContents) {
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
