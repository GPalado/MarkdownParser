package Nodes;

/**
 * Bold node representing text with the bold effect applied.
 */
public class BoldNode extends LineNode {

    public BoldNode(String line) {
        // TODO: parse following content to identify where effect begins, ends, content, etc.
        super(line);
    }

    @Override
    public String toHTML() {
        return "<strong>" + super.toHTML() + "<strong>";
    }

    @Override
    public String toString() {
        return "**" + super.toString() + "**";
    }
}
