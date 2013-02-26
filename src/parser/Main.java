package parser;

import java.util.List;
import java.util.Scanner;
import parser.nodes.SyntaxNode;

public class Main {

    public Main () {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param args
     */
    public static void main (String[] args) {
        Parser p = new Parser();
        Scanner in = new Scanner(System.in);
        while (true)
        {
            String input = in.nextLine();
            List<SyntaxNode> l = p.parseCommand(input);
            for (SyntaxNode f : l)
            {
                System.out.println(f.evaluate(null));
            }
        }
    }

}
