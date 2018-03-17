package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.TextNode;

import java.util.Scanner;

/**
 * Specifies the functionalities of effect rules such as the conformity to certain conditions,
 * and the actions applied to each rule.
 */
public interface EffectRule {

    /**
     * Returns true if the given string conforms to the rule.
     * @param s
     * @return true if it fits the rule's conditions, false otherwise.
     */
    boolean meetsCondition(Scanner s);

    /**
     * Returns the TextNode that is created when the rule is applied.
     * @param s
     * @return TextNode representing the rule applied to the given string.
     */
    TextNode applyAction(Scanner s);
}
