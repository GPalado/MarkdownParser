package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.HeaderNode;
import MarkdownProcessor.Nodes.TextNode;

/**
 * Implementation of the Hash Rule, in which one or more '#' followed by a space denotes a header.
 */
public class HashRule implements ParsingRule {

    public HashRule(){}

    @Override
    public boolean meetsCondition(String s) {
        //TODO: fix regex to accept multiple hashes
        return s.startsWith("[#]+ ");
    }

    @Override
    public TextNode action(String s) {
        return new HeaderNode(s);
    }
}
