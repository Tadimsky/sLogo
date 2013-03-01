package parser.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import parser.Parser;
import parser.nodes.SyntaxNode;


public class MathTester {

    private Parser myParser;
    private Random myRandom;
    
    @Before
    public void setUp () throws Exception {
        myParser = new Parser();
        myRandom = new Random();
    }

    @Test
     public void testSum () {
        for (int i = 1; i < 10; i++)
        {
            int x = myRandom.nextInt(i * 100);
            int y = myRandom.nextInt(i * 100);
            
            List<SyntaxNode> n = myParser.parseCommand("sum " + x + " " + y);
            assertEquals(n.get(0).evaluate(null), x+y);
            n = myParser.parseCommand("+ " + x + " " + y);
            assertEquals(n.get(0).evaluate(null), x+y);            
        }
    }
    
    @Test
    public void testDifference() {
        for (int i = 1; i < 10; i++)
        {
            int x = myRandom.nextInt(i * 100);
            int y = myRandom.nextInt(i * 100);
            
            List<SyntaxNode> n = myParser.parseCommand("difference " + x + " " + y);
            assertEquals(n.get(0).evaluate(null), x-y);
            n = myParser.parseCommand("- " + x + " " + y);
            assertEquals(n.get(0).evaluate(null), x-y);            
        }
    }
        
    @Test
    public void testProduct () {
        for (int i = 1; i < 10; i++)
        {
            int x = myRandom.nextInt(i * 100);
            int y = myRandom.nextInt(i * 100);
            
            List<SyntaxNode> n = myParser.parseCommand("product " + x + " " + y);
            assertEquals(n.get(0).evaluate(null), x*y);
            n = myParser.parseCommand("* " + x + " " + y);
            assertEquals(n.get(0).evaluate(null), x*y); 
        }
    }
    
    @Test
    public void testQuotient () {
        for (int i = 1; i < 10; i++)
        {
            int x = myRandom.nextInt(i * 100);
            int y = myRandom.nextInt(i * 100);
            
            List<SyntaxNode> n = myParser.parseCommand("quotient " + x + " " + y);
            assertEquals(n.get(0).evaluate(null), x/y);
            n = myParser.parseCommand("/ " + x + " " + y);
            assertEquals(n.get(0).evaluate(null), x/y); 
        }
    }
    
    @Test
    public void testMinus () {
        for (int i = 1; i < 10; i++)
        {
            int x = myRandom.nextInt(i * 100);            
            
            List<SyntaxNode> n = myParser.parseCommand("minus " + x);
            assertEquals(n.get(0).evaluate(null), -x);
            n = myParser.parseCommand("~ " + x);
            assertEquals(n.get(0).evaluate(null), -x); 
        }
    }
    
    @Test
    public void testRemainder () {
        for (int i = 1; i < 10; i++)
        {
            int x = myRandom.nextInt(i * 100);
            int y = myRandom.nextInt(i * 100);
            
            List<SyntaxNode> n = myParser.parseCommand("remainder " + x + " " + y);
            assertEquals(n.get(0).evaluate(null), x%y);
            n = myParser.parseCommand("% " + x + " " + y);
            assertEquals(n.get(0).evaluate(null), x%y); 
        }
    }
    
    @Test
    public void testRandom() {
        for (int i = 1; i < 10; i++)
        {
            int x = myRandom.nextInt(i * 100);   
            List<SyntaxNode> n = myParser.parseCommand("random " + x);
            int val = n.get(0).evaluate(null);
            assertTrue((0 <= val) && (val <= x));
                         
        }
    }
}
