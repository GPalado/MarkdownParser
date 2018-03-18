package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.HeaderNode;
import MarkdownProcessor.Nodes.TextNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Implementation of the Hash Rule, in which one or more '#' followed by one or more
 * spaces denotes a header.
 */
public class HashRule implements EffectRule {

    public HashRule() {
    }

    @Override
    public boolean meetsCondition(Scanner s) {
        s.useDelimiter("\n");
        return s.hasNext("#+[\\s]+[a-zA-Z0-9//\\\\`~!@#$%^&*()-=_+\\[\\]{}|'\",?><,.:;]+");
    }

    @Override
    public List<TextNode> applyAction(Scanner s) {
        if (meetsCondition(s)) {
            int depth = filterHashesAndSpaces(s);
            return Arrays.asList(new HeaderNode(depth > 5 ? 5 : depth, parseChildren(s)));
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
        System.out.println(hashes+"|");
        scanner.skip("[\\s]*");
        return hashes.length();
    }
}
