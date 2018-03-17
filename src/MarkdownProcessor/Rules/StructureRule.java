package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.TextNode;

import java.util.Scanner;

/**
 * Describes a rule specific to the structure of the input.
 */
public interface StructureRule {

    /**
     * Applies the structure rule to the given scanner input.
     * @param s
     * @return Optional of a TextNode containing the structure if possible, Optional.empty() otherwise.
     */
    TextNode applyStructure(Scanner s);
}
