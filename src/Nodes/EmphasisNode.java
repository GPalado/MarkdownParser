package Nodes;

/**
 * Emphasis Node representing text with the italics effect applied.
 */
public class EmphasisNode extends LineNode {

    public EmphasisNode(String line) {
        // TODO: parse following content to identify where effect begins, ends, content, etc.
        super(line);
    }

    @Override
    public String toHTML() {
        return "<em>" + super.toHTML() + "</em>";
    }

    @Override
    public String toString() {
        return "*" + super.toString() + "*";
    }
}
