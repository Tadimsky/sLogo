package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import parser.nodes.SyntaxNode;
import parser.nodes.NodeInformation;
import parser.nodes.exceptions.NodeDefinitionException;

public class SemanticsTable {
    public static final String PARSER_RESOURCES = "parser.resources.";
    public static final String SEMANTICS_FILE = "tokens";
    public static final Locale DEFAULT_REGION = Locale.ENGLISH;
    
    private static final String TOKEN_LIST = "tokens";
    private static final String LIST_SEPERATOR = ",";
    
    private ResourceBundle myResource;

    private static SemanticsTable instance = null;
    
    List<NodeInformation> myNodeList;

    public SemanticsTable () {      
        myResource = PropertyResourceBundle.getBundle(PARSER_RESOURCES + SEMANTICS_FILE);
        buildMap();
    }
    
    private void buildMap()
    {
        myNodeList = new ArrayList<NodeInformation>();
        String tokens = myResource.getString(TOKEN_LIST);
        String[] tokenArray = tokens.split(LIST_SEPERATOR); 
        for (String tok : tokenArray)
        {
            try
            {
                NodeInformation ni = new NodeInformation(tok, myResource);
                myNodeList.add(ni);
            }
            catch (NodeDefinitionException e) { 
                // just skip this particular token
                System.out.println(e.getMessage());
                continue;
            }
        }
    }

    public static SemanticsTable getInstance ()
    {
        if (instance == null)
        {
            // Application is not threaded at the moment so don't need this
            synchronized (SemanticsTable.class) {
                if (instance == null) {
                    instance = new SemanticsTable();
                }
            }
        }
        return instance;
    }
    
    public NodeInformation getTokenClass(String token)
    {
        
        for (NodeInformation n : myNodeList)
        {
            if (n.equals(token))
            {
                return n;
            }
        }
        
        /*
        int ind = myNodeList.indexOf(token);
        if (ind > 0)            
        {
            return myNodeList.get(ind);
        }*/
        return null;
    }
    
    
}
