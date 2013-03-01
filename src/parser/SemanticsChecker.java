package parser;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import parser.nodes.NodeInformation;
import parser.nodes.SyntaxNode;
import parser.nodes.TokenNode;
import parser.nodes.exceptions.ClassDefinitionException;
import parser.nodes.exceptions.InvalidArgumentsException;
import parser.reflection.ReflectionHelper;

/**
 * This class takes tokens and builds them into something that
 * can be run by the application. These SyntaxNodes are created
 * by reading in a properties file containing all the information
 * about a node in the tree.
 * 
 * The nodes are then created using reflection.
 * Some basic syntax checking is performed in the creation of these nodes.
 * 
 * @author Jonathan Schmidt
 *
 */
public class SemanticsChecker {

    private SemanticsTable myTable;    
    
    public SemanticsChecker () {
        myTable = SemanticsTable.getInstance();
    }    
    
    /**
     * Takes in a list of tokens of input and turns them into SyntaxNodes
     * Builds trees and returns a list of all the roots of the trees.
     * 
     * @param tokens a list of tokens that have been processed.
     * @return List of SyntaxNodes representing the roots of trees.
     */
    public List<SyntaxNode> evaluateExpression(List<String> tokens)
    {
        // handle the end first
        Collections.reverse(tokens);
        Deque<SyntaxNode> stack = new ArrayDeque<SyntaxNode>();
        
        for (String token : tokens)
        {
            NodeInformation node = myTable.getTokenClass(token);
            if (node != null)
            {
                try {
                    SyntaxNode sn = createNode(token, node, stack);
                    stack.push(sn);
                }
                catch (Exception e) {
                    // Fatal error with this node.
                    return null;
                }               
            }
        }
        
        List<SyntaxNode> result = new ArrayList<SyntaxNode>();
        while (!stack.isEmpty())
        {
            result.add(stack.pop());
        }
        
        return result;
    }
    
    /**
     * Creates a Node using the specified information and reflection.
     * 
     * @param token
     * @param n
     * @param params
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws ClassDefinitionException
     */
    private SyntaxNode createNode(String token, NodeInformation n, Deque<SyntaxNode> params) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassDefinitionException
    {       
        if (n.getArgs() == 0)
        {
            // Add the token as the first itme in the queue.
            params.push(new TokenNode(token));            
        }
        if (params.size() >= n.getArgs())
        {
            return ReflectionHelper.createInstanceOf(n.getType(), params);
        }
        else
        {
            throw new InvalidArgumentsException(InvalidArgumentsException.INCORRECT_NUMBER_ARGS, n.getName());
        }
    }
    

}
