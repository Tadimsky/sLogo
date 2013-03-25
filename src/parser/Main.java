package parser;

import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;
import parser.nodes.SyntaxNode;


public class Main {

    private static JFileChooser myChooser;

    public Main () {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param args
     */
    public static void main (String[] args) {
        Parser p = new Parser();
        myChooser = new JFileChooser();
        // checkLine(p);
        checkFile(p);

    }

    private static void checkLine (Parser p)
    {
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

    private static void checkFile (Parser p)
    {
        try {
            int response = myChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                Scanner f = new Scanner(myChooser.getSelectedFile());
                List<SyntaxNode> l = p.parseCommand(f);
                for (SyntaxNode j : l)
                {
                    System.out.println(j.evaluate(null));
                }
            }
        }
        catch (Exception exception) {
            // showError(exception.toString());
        }
    }

}
