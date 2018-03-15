package MarkdownProcessor.Nodes;

import MarkdownProcessor.Translators.Translator;

/**
 * Header node representing a line of text with the header effect. Header level is specified in markdown by the number
 * of hashes.
 */
public class HeaderNode implements TextNode {
    private String content;

    public HeaderNode(String line) {
        // TODO: count hashes to know level of header
        // header different to line node? Or can headers also have multiple children?
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public String accept(Translator t) {
        return t.translateHeader(this);
    }
}
