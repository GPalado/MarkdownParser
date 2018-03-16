package MarkdownProcessor.Nodes;

import MarkdownProcessor.Translators.Translator;

/**
 * Emphasis Node representing text with the italics effect applied.
 */
public class EmphasisNode extends LineNode {

    public EmphasisNode(String line) {
        // TODO: parse following content to identify where effect begins, ends, content, etc.
        super(line);
    }

    @Override
    public String accept(Translator t){
        return t.translateEmphasis(this);
    }

    @Override
    public String toString() {
        return "*" + super.toString() + "*";
    }
}
