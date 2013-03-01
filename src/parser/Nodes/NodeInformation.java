package parser.nodes;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import parser.nodes.exceptions.NodeDefinitionException;

/**
 * This class represents the information that is stored in the properties file.
 * This is all the information that is required to generate a new node.
 * 
 * @author Jonathan Schmidt
 *
 */
public class NodeInformation {

    private static final String PROP_TOKEN = "token";
    private static final String PROP_CLASS = "class";
    private static final String PROP_ARGS = "args";
    private static final String PROP_DESC = "desc";

    private int myArgs;
    private Class<?> myClass;
    private String[] myPatterns;
    private String myDesc;
    private String myName;

    public NodeInformation (String name, ResourceBundle info) throws NodeDefinitionException {

        myName = name;

        try {
            myPatterns = info.getString(getKey(PROP_TOKEN)).split(",");
            myClass = Class.forName(info.getString(getKey(PROP_CLASS)));
            myArgs = Integer.parseInt(info.getString(getKey(PROP_ARGS)));
        }
        catch (ClassNotFoundException e1) {
            throw new NodeDefinitionException(
                                              NodeDefinitionException.MISSING_CLASS,
                                              e1.getMessage());
        }
        catch (MissingResourceException e2)
        {
            throw new NodeDefinitionException(
                                              NodeDefinitionException.REQUIRED_PROPERTY,
                                              e2.getKey());
        }

        try
        {
            myDesc = info.getString(getKey(PROP_DESC));
        }
        catch (MissingResourceException e)
        {
            myDesc = "";
        }
    }

    private String getKey (String field)
    {
        return myName + "." + field;
    }
    
    public String getName()
    {
        return myName;
    }
    
    public String getDesc()
    {
        return myDesc;
    }
    
    public Class<?> getType()
    {
        return myClass;
    }
    
    public int getArgs()
    {
        return myArgs;
    }
    
    public boolean match(String token)
    {
        for (int i = 0; i < myPatterns.length; i++)
        {
            if (token.matches(myPatterns[i]))
            {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o != null)
        {
            if (o instanceof String)
            {
                return match((String)o);
            }
        }
        return false;
    }
}
