package parser;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Stack;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Reflection;
import parser.nodes.NodeInformation;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.ClassDefinitionException;
import parser.nodes.exceptions.InvalidArgumentsException;
import parser.reflection.ReflectionHelper;

public class SemanticsChecker {

    private SemanticsTable myTable;    
    
    public SemanticsChecker () {
        myTable = SemanticsTable.getInstance();
    }    
    
    /**
     * Takes in a list of tokens in reverse order and turns them into SyntaxNodes
     * Builds trees and returns a list of all the 
     * 
     * @param tokens
     * @return
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
                catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | ClassDefinitionException e) {
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
    
    private SyntaxNode createNode(String token, NodeInformation n, Deque<SyntaxNode> params) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassDefinitionException
    {       
        if (n.getArgs() == 0)
        {
            return ReflectionHelper.createInstanceOf(n.getType(), token);
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
