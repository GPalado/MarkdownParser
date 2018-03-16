package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.TextNode;

/**
 * Implementation of the Asterisk Rule, in which one or more '*' followed by a non-space character
 * can denote bold or italics effects.
 */
public class AsteriskRule implements ParsingRule {

    public AsteriskRule(){}

    @Override
    public boolean meetsCondition(String s) {
        return s.startsWith("[*]*[a-zA-Z0-9!@#$%^&*()_+-=`~<>?,./:\";'{}|[]\\']");
    }

    @Override
    public TextNode action(String s) {
        // TODO: Implement counting logic for asterisks and nesteds.
        return null;
    }
}
