package Nodes;

/**
 * Paragraph Node specifying a paragraph break.
 */
public class ParagraphNode implements TextNode {

    public ParagraphNode() {
        // TODO: figure out how to make contents of para contained in tags
    }

    @Override
    public String toHTML() {
        return "</p><p>";
    }

    @Override
    public String toString() {
        return "\n";
    }
}
