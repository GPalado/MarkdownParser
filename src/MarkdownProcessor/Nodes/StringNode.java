package MarkdownProcessor.Nodes;

import MarkdownProcessor.Translators.Translator;

/**
 * String Node representing a plain String.
 */
public class StringNode implements TextNode {
    private String value;

    public StringNode(String value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return "\"" + value + "\"";
    }

    @Override
    public String accept(Translator t) {
        return t.translateString(this);
    }
}
