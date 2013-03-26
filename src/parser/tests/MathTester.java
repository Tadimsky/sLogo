package parser.tests;

import java.util.List;
import java.util.Random;
import parser.Parser;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;
import parser.nodes.exceptions.InvalidSemanticsException;


public class MathTester {

    private Parser myParser;
    private Random myRandom;

    @Before
    public void setUp () throws Exception {
        myParser = new Parser();
        myRandom = new Random();
    }

    @Test
    public void testSum () throws InvalidSemanticsException, InvalidArgumentsException {
        for (int i = 1; i < 10; i++)
        {
            int x = myRandom.nextInt(i * 100);
            int y = myRandom.nextInt(i * 100);

            List<SyntaxNode> n = myParser.parseCommand("sum " + x + " " + y);
            assertEquals(n.get(0).evaluate(null), x + y);
            n = myParser.parseCommand("+ " + x + " " + y);
            assertEquals(n.get(0).evaluate(null), x + y);
        }
    }

    @Test
    public void testDifference () throws InvalidSemanticsException, InvalidArgumentsException {
        for (int i = 1; i < 10; i++)
        {
            int x = myRandom.nextInt(i * 100);
            int y = myRandom.nextInt(i * 100);

            List<SyntaxNode> n = myParser.parseCommand("difference " + x + " " + y);
            assertEquals(n.get(0).evaluate(null), x - y);
            n = myParser.parseCommand("- " + x + " " + y);
            assertEquals(n.get(0).evaluate(null), x - y);
        }
    }

    @Test
    public void testProduct () throws InvalidSemanticsException, InvalidArgumentsException {
        for (int i = 1; i < 10; i++)
        {
            int x = myRandom.nextInt(i * 100);
            int y = myRandom.nextInt(i * 100);

            List<SyntaxNode> n = myParser.parseCommand("product " + x + " " + y);
            assertEquals(n.get(0).evaluate(null), x * y);
            n = myParser.parseCommand("* " + x + " " + y);
            assertEquals(n.get(0).evaluate(null), x * y);
        }
    }

    @Test
    public void testQuotient () throws InvalidSemanticsException, InvalidArgumentsException {
        for (int i = 1; i < 10; i++)
        {
            int x = myRandom.nextInt(i * 100);
            int y = myRandom.nextInt(i * 100);

            List<SyntaxNode> n = myParser.parseCommand("quotient " + x + " " + y);
            assertEquals(n.get(0).evaluate(null), x / y);
            n = myParser.parseCommand("/ " + x + " " + y);
            assertEquals(n.get(0).evaluate(null), x / y);
        }
    }

    @Test
    public void testMinus () throws InvalidSemanticsException, InvalidArgumentsException {
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
    public void testRemainder () throws InvalidSemanticsException, InvalidArgumentsException {
        for (int i = 1; i < 10; i++)
        {
            int x = myRandom.nextInt(i * 100);
            int y = myRandom.nextInt(i * 100);

            List<SyntaxNode> n = myParser.parseCommand("remainder " + x + " " + y);
            assertEquals(n.get(0).evaluate(null), x % y);
            n = myParser.parseCommand("% " + x + " " + y);
            assertEquals(n.get(0).evaluate(null), x % y);
        }
    }

    @Test
    public void testRandom () throws InvalidSemanticsException, InvalidArgumentsException {
        for (int i = 1; i < 10; i++)
        {
            int x = myRandom.nextInt(i * 100);
            List<SyntaxNode> n = myParser.parseCommand("random " + x);
            int val = n.get(0).evaluate(null);
            assertTrue((0 <= val) && (val <= x));

        }
    }
}
