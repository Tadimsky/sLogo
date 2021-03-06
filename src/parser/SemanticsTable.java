package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import parser.nodes.NodeInformation;
import parser.nodes.exceptions.NodeDefinitionException;


/**
 * The Semantics Table stores all the different nodes that can be created
 * from commands that are entered. It links these to the format that the
 * commands are in so that new instances of a node can be created.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class SemanticsTable {
    private static final String PARSER_RESOURCES = "parser.resources.";
    private static final String SEMANTICS_FILE = "tokens";
    private static final Locale DEFAULT_REGION = Locale.ENGLISH;

    private static final String TOKEN_LIST = "tokens";
    private static final String LIST_SEPERATOR = ",";
    private static SemanticsTable instance = null;

    private ResourceBundle myResource;
    private IParserProvider myContext;

    private List<NodeInformation> myNodeList;

    /**
     * Creates a new instance of the Semantics Table and loads the properties file.
     */
    public SemanticsTable () {
        myResource = ResourceBundle.getBundle(PARSER_RESOURCES + SEMANTICS_FILE);
        buildMap();
    }

    /**
     * Reads in every item in the properties file and turns it into a Node Information.
     * Builds the list of nodes that can be created.
     */
    private void buildMap () {
        myNodeList = new ArrayList<NodeInformation>();
        String tokens = myResource.getString(TOKEN_LIST);
        String[] tokenArray = tokens.split(LIST_SEPERATOR);
        for (String tok : tokenArray) {
            String token = tok.trim();
            try {
                NodeInformation ni = new NodeInformation(token, myResource);
                myNodeList.add(ni);
            }
            catch (NodeDefinitionException e) {
                // just skip this particular token
                System.out.println(e.getMessage());
                continue;
            }
        }
    }

    /**
     * Returns the instance of the SemanticsTable
     * 
     * @return Singleton
     */
    public static SemanticsTable getInstance () {
        if (instance == null) {
            // Application is not threaded at the moment so don't need this
            synchronized (SemanticsTable.class) {
                if (instance == null) {
                    instance = new SemanticsTable();
                }
            }
        }
        return instance;
    }

    /**
     * Returns the Node Information for a specific token.
     * This will be used to get information about what a
     * token represents in this application.
     * 
     * @param token The token of which the information is required.
     * @return
     */
    public NodeInformation getTokenClass (String token) {
        String lower = token.toLowerCase();
        for (NodeInformation n : myNodeList) {
            if (n.equals(lower)) {
                NodeInformation ni = (NodeInformation) n.clone();
                ni.setToken(lower);
                return ni;
            }
        }
        return null;
    }

    /**
     * Set the Current Context of the Semantics Table
     * This allows each context to have it's own custom commands.
     * 
     * @param context The Context
     */
    public void setContext (IParserProvider context) {
        myContext = context;
    }

    /**
     * Registers a custom command to the current context.
     * 
     * @param c The custom command to register
     */
    public void registerCustomCommand (CustomCommand c) {
        if (myContext != null && c != null) {
            myContext.addCommand(c);
        }
    }

    /**
     * Returns a custom command from the current context
     * 
     * @param name The name of the custom command
     * @return The custom command
     */
    public CustomCommand getCommand (String name) {
        if (myContext != null) return myContext.getCommand(name);
        return null;
        // throw new InvalidSemanticsException(INVALID_CUSTOM_COMMAND, name);
    }

}
