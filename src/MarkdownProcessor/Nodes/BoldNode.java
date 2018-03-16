package MarkdownProcessor.Nodes;

import MarkdownProcessor.Translators.Translator;

/**
 * Bold node representing text with the bold effect applied.
 */
public class BoldNode extends LineNode {

    public BoldNode(String line) {
        // TODO: parse following content to identify where effect begins, ends, content, etc.
        super(line);
    }

    @Override
    public String accept(Translator t) {
        return t.translateBold(this);
    }

    @Override
    public String toString() {
        return "**" + super.toString() + "**";
    }
}
