package Nodes;

import Translators.Translator;

/**
 * Paragraph Node specifying a paragraph break.
 */
public class ParagraphNode implements TextNode {

    public ParagraphNode() {
        // TODO: figure out how to make contents of para contained in tags
    }

    @Override
    public String toString() {
        return "\n";
    }

    @Override
    public String accept(Translator t) {
        return t.translateParagraph(this);
    }
}
