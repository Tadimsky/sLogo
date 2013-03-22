package parser.tests;

import java.util.List;
import java.util.Random;
import parser.Parser;
import parser.nodes.SyntaxNode;


public class BooleanTester {

    Parser myParser;
    Random myRandom;

    @Before
    public void setUp () throws Exception {
        myParser = new Parser();
        myRandom = new Random();
    }

    @Test
    public void testAnd () {
        for (int i = 1; i < 10; i++)
        {
            int x = myRandom.nextInt(1);
            int y = myRandom.nextInt(1);
            
            List<SyntaxNode> n = myParser.parseCommand("AND " + x + " " + y);
            int val = (x == y) && (x==1) ? 1 : 0;
            assertEquals(n.get(0).evaluate(null), val);            
        }
    }
    
    @Test
    public void testEqual () {
       for (int i = 1; i < 10; i++)
       {
           int x = myRandom.nextInt(i * 100);
           int y = myRandom.nextInt(i * 100);
           
           List<SyntaxNode> n = myParser.parseCommand("equal? " + x + " " + y);
           int val = (x == y) ? 1 : 0;
           
           assertEquals(n.get(0).evaluate(null), val);
           n = myParser.parseCommand("equalp " + x + " " + y);
           assertEquals(n.get(0).evaluate(null), val);            
       }
   }
   
   @Test
   public void testGreaterThan() {
       for (int i = 1; i < 10; i++)
       {
           int x = myRandom.nextInt(i * 100);
           int y = myRandom.nextInt(i * 100);           
           
           List<SyntaxNode> n = myParser.parseCommand("greater? " + x + " " + y);
           
           int val = (x > y) ? 1 : 0;
           
           assertEquals(n.get(0).evaluate(null), val);
           n = myParser.parseCommand("greaterp " + x + " " + y);
           assertEquals(n.get(0).evaluate(null), val);            
       }
   }
   
   @Test
   public void testLessThan() {
       for (int i = 1; i < 10; i++)
       {
           int x = myRandom.nextInt(i * 100);
           int y = myRandom.nextInt(i * 100);           
           
           List<SyntaxNode> n = myParser.parseCommand("less? " + x + " " + y);
           
           int val = (x < y) ? 1 : 0;
           
           assertEquals(n.get(0).evaluate(null), val);
           n = myParser.parseCommand("lessp " + x + " " + y);
           assertEquals(n.get(0).evaluate(null), val);            
       }
   }
   
   @Test
   public void testNot () {
       for (int i = 1; i < 10; i++)
       {
           int x = myRandom.nextInt(1);                      
           
           List<SyntaxNode> n = myParser.parseCommand("not " + x);
           
           int val = (x == 1) ? 0 : 1;
           
           assertEquals(n.get(0).evaluate(null), val);              
       }
   }
   
   @Test
   public void testNotEqual () {
       for (int i = 1; i < 10; i++)
       {
           int x = myRandom.nextInt(i * 100);
           int y = myRandom.nextInt(i * 100);           
           
           List<SyntaxNode> n = myParser.parseCommand("notequal? " + x + " " + y);
           
           int val = (x != y) ? 1 : 0;
           
           assertEquals(n.get(0).evaluate(null), val);
           n = myParser.parseCommand("notequalp " + x + " " + y);
           assertEquals(n.get(0).evaluate(null), val);            
       }
   }
   
   @Test
   public void testOr () {
       for (int i = 1; i < 10; i++)
       {
           int x = myRandom.nextInt(i * 100);
           int y = myRandom.nextInt(i * 100);           
           
           List<SyntaxNode> n = myParser.parseCommand("or " + x + " " + y);
           
           int val = (x == 1) || (y == 1) ? 1 : 0;
           
           assertEquals(n.get(0).evaluate(null), val);                       
       }
   }
}