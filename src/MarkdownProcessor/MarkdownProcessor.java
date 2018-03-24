package MarkdownProcessor;

import MarkdownProcessor.Nodes.TextNode;
import MarkdownProcessor.Rules.ParserHelper;

import java.util.Scanner;

/**
 * Markdown file processor. Generates syntax tree of given input.
 */
public class MarkdownProcessor {

    public MarkdownProcessor() {}

    /**
     * Parses markdown into syntax tree and returns the root node of the tree.
     *
     * @return TextNode representing the text in the scanner.
     */
    public static TextNode parseMarkdown(Scanner scanner) {
        return ParserHelper.applyStructureRules(scanner);
    }
}
