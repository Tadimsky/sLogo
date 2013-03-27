package parser;

import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;
import parser.nodes.SyntaxNode;
import parser.nodes.exceptions.InvalidArgumentsException;
import parser.nodes.exceptions.InvalidSemanticsException;


public class Main {

    private static JFileChooser myChooser;

    /**
     * @param args text
     */
    public static void main (String[] args) {
        Parser p = new Parser();
        myChooser = new JFileChooser();
        try {
            checkLine(p);
        }
        catch (InvalidSemanticsException e) {
            return;
        }
        checkFile(p);

    }

    private static void checkLine (Parser p) throws InvalidSemanticsException
    {
        Scanner in = new Scanner(System.in);
        while (true)
        {
            String input = in.nextLine();
            List<SyntaxNode> l = null;
            try {
                l = p.parseCommand(input);
            }
            catch (InvalidSemanticsException e) {
            }
            for (SyntaxNode f : l)
            {
                try {
                    System.out.println(f.evaluate(null));
                }
                catch (InvalidArgumentsException e) {
                    continue;
                }
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
