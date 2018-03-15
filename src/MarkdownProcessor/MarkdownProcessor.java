package MarkdownProcessor;

import MarkdownProcessor.Nodes.MarkdownFileNode;

import java.util.Scanner;

/**
 * Markdown file processor. Generates syntax tree of given input.
 */
public class MarkdownProcessor {
    private Scanner scanner;

    /**
     * Processor takes scanner that reads input string/file.
     *
     * @param scanner
     */
    public MarkdownProcessor(Scanner scanner) {
        //TODO: Determine how to specify output file. Ask for filename or final field?
        this.scanner = scanner;
    }

    /**
     * Parses markdown lines into LineNodes.
     *
     * @return List<ParserNode> representing the text in the scanner.
     */
    public MarkdownFileNode parseMarkdown() {
        return new MarkdownFileNode(scanner);
    }
}
