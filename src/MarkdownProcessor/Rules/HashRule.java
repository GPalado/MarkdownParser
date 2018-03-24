package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.HeaderNode;
import MarkdownProcessor.Nodes.LineNode;
import MarkdownProcessor.Nodes.StringNode;
import MarkdownProcessor.Nodes.TextNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Implementation of the Hash Rule, in which one or more '#' followed by one or more
 * spaces denotes a header.
 */
public class HashRule implements StructureRule {

    public HashRule() {
    }

    @Override
    public boolean meetsCondition(Scanner s) {
        s.useDelimiter("\n");
        return s.hasNext("#{1,6}[\\s]+.+");
    }

    @Override
    public TextNode applyStructure(Scanner s) {
        if (meetsCondition(s)) {
            int depth = filterHashesAndSpaces(s);
            return new HeaderNode(depth, parseChildren(s));
        }
        throw new IllegalArgumentException("HashRule cannot be applied to the given input.");
    }

    private List<TextNode> parseChildren(Scanner s){
        List<TextNode> children = new ArrayList<>();
        String line = s.nextLine();
        Scanner lineScanner = new Scanner(line);
        children.addAll(ParserHelper.applyEffectRules(lineScanner));
        return children;
    }

    /**
     * Filters the hashes and spaces from the start of the given scanner.
     * @param scanner
     * @return number of hashes at the start of the scanner input.
     */
    private int filterHashesAndSpaces(Scanner scanner) {
        scanner.reset();
        String hashes = scanner.next();
        scanner.skip("[\\s]*");
        return hashes.length();
    }
}
