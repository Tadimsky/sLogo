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
import parser.nodes.exceptions.InvalidSemanticsException;
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
    public List<SyntaxNode> evaluateExpression (List<String> tokens)
    {
        // handle the end first

        Collections.reverse(tokens);
        List<NodeInformation> nodes = new ArrayList<NodeInformation>();

        // Run through it a first time in order to identify and register custom commands
        for (int i = 0; i < tokens.size(); i++)
        {
            String token = tokens.get(i);
            NodeInformation node = myTable.getTokenClass(token);
            nodes.add(node);
            if (node.getName().equals("to"))
            {
                myTable.registerCustomCommand(createCustomCommand(nodes, i));
            }
        }

        // Create nodes
        Deque<SyntaxNode> stack = new ArrayDeque<SyntaxNode>();
        for (int i = 0; i < nodes.size(); i++)
        {
            NodeInformation node = nodes.get(i);
            String token = tokens.get(i);
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
    private SyntaxNode createNode (String token, NodeInformation n, Deque<SyntaxNode> params)
                                                                                             throws InstantiationException,
                                                                                             IllegalAccessException,
                                                                                             IllegalArgumentException,
                                                                                             InvocationTargetException,
                                                                                             ClassDefinitionException
    {
        // Stop custom functions from messing things up and gobbling arguments.
        if (!n.shouldCreate()) return new TokenNode(token);

        if (n.getArgs() == 0)
        {
            // Add the token as the first item in the queue.
            params.push(new TokenNode(token));
        }
        if (params.size() >= n.getArgs())
            return ReflectionHelper.createInstanceOf(n.getType(), params);
        else throw new InvalidArgumentsException(InvalidArgumentsException.INCORRECT_NUMBER_ARGS,
                                                 n.getName());
    }

    private CustomCommand createCustomCommand (List<NodeInformation> nodeI, int pos)
    {
        try
        {
            NodeInformation comName = nodeI.get(pos - 1);
            comName.setShouldCreate(false);
            String commandName = comName.getToken();
            NodeInformation cur = nodeI.get(pos - 2);
            if (cur.getType().getName().contains("ListNode"))
            {
                int argc = 0;

                for (int i = pos - 3; i > 0; i--)
                {
                    cur = nodeI.get(i);
                    if (cur.getType().getName().contains("ListEndNode"))
                        return new CustomCommand(commandName, argc);
                    argc++;
                }
            }
        }
        catch (Exception e)
        {
            throw new InvalidSemanticsException("Incorrect format for creating a custom command.",
                                                "");
        }
        return null;
    }

}
