package MarkdownProcessor;

import MarkdownProcessor.Nodes.MarkdownFileNode;

import java.util.Scanner;

/**
 * Markdown file processor. Generates syntax tree of given input.
 */
public class MarkdownProcessor {

    public MarkdownProcessor() {}

    /**
     * Parses markdown lines into LineNodes.
     *
     * @return List<ParserNode> representing the text in the scanner.
     */
    public MarkdownFileNode parseMarkdown(Scanner scanner) {
        return new MarkdownFileNode(scanner);
    }
}
