package parser;

import java.util.HashMap;
import java.util.Map;
import parser.nodes.ISyntaxNode;

public class SemanticsTable {

    public static final String SEMANTICS_FILE = "commands.properties";

    private static SemanticsTable instance = null;
    
    Map<String, Class<ISyntaxNode>> mynodeMap;

    public SemanticsTable () {
        
        // load file and store things.
        
        mynodeMap = new HashMap<String, Class<ISyntaxNode>>();
        //mynodeMap.put(key, value)        
    }

    public static SemanticsTable getInstance ()
    {
        if (instance == null)
        {
            synchronized (SemanticsTable.class) {
                if (instance == null) {
                    instance = new SemanticsTable();
                }
            }
        }
        return instance;
    }
    
    
    
}
