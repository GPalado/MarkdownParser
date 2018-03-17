package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.TextNode;

import java.util.Optional;

/**
 * Specifies the functionalities of parsing rules such as the conformity to certain conditions,
 * and the actions applied to each rule.
 */
public interface ParsingRule {
    //TODO: Concerns about sequential requirements for calls to these methods.s

    /**
     * Returns true if the given string conforms to the rule.
     * @param s
     * @return true if it fits the rule's conditions, false otherwise.
     */
    boolean meetsCondition(String s);

    /**
     * Returns the TextNode that is created when the rule is applied.
     * @param s
     * @return Optional of the TextNode representing the rule applied to the given string if the string meets the condition,
     * Optional.empty() otherwise.
     */
    Optional<TextNode> applyAction(String s);
}
