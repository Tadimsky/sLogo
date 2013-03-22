package parser.nodes;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import parser.IParserProvider;
import parser.nodes.exceptions.ClassDefinitionException;
import parser.nodes.exceptions.InvalidArgumentsException;
import parser.reflection.ReflectionHelper;

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
        // The command that operates on the list  
        SyntaxNode command = queue.pop();
        int params = ((ParameterNode)command).getParameterCount();
        if (params > 1)
        {
            queue.push(command);
        }
        else
        {
            myContents.add(command);
        }
        
        Deque<SyntaxNode> temp = new LinkedList<SyntaxNode>();        
        // Move all elements in the list to a new queue
        while (!(queue.isEmpty()))
        {
            SyntaxNode sn = queue.pop();
            if (sn instanceof ParamListEndNode)
            {
                break;
            }
            temp.add(sn);
        }
        
        if (params > 1)
        {
            // Last item means it's empty
            while (temp.size() > 1)
            {
                try {
                    SyntaxNode again = ReflectionHelper.createInstanceOf(command.getClass(), temp);
                    temp.push(again);
                }
                catch (Exception e) {
                    return;
                }
            }
        }
        else
        {
            for (int i = 0; i < temp.size(); i++)
            {
                try {
                    SyntaxNode again = ReflectionHelper.createInstanceOf(command.getClass(), temp);
                    temp.add(again);
                }
                catch (Exception e) {
                    return;
                }
            }
        }
        
        while (!(temp.isEmpty()))
        {   
            myContents.add(temp.pop());
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
