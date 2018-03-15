import Nodes.LineNode;
import Nodes.ParserNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Markdown file parser. Able to generate HTML equivalent of given input.
 */
public class Parser {
    private Scanner scanner;
    private static final HashMap<String, Runnable> visitors = new HashMap<>();

    /**
     * Parser takes scanner that reads input string.
     *
     * @param scanner
     */
    public Parser(Scanner scanner) {
        //TODO: Determine how to specify output file. Ask for filename or final field?
        this.scanner = scanner;
    }

    static {
        //TODO: initialize map content for visitors. Decide where this belongs.
    }

    /**
     * Parses markdown lines into LineNodes.
     *
     * @return List<ParserNode> representing the text in the scanner.
     */
    public List<ParserNode> parseMarkdown() {
        List<ParserNode> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(new LineNode(scanner.nextLine()));
        }
        return lines;
    }
}
