package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.TextNode;

import java.util.List;
import java.util.Scanner;

/**
 * Specifies the functionalities of effect rules such as the conformity to certain conditions,
 * and the actions applied to each rule.
 */
public interface EffectRule extends Rule {

    /**
     * Returns the TextNode that is created when the rule is applied.
     * @param s
     * @return TextNode representing the rule applied to the given string.
     */
    List<TextNode> applyAction(Scanner s);
}
