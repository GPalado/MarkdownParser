package Nodes;

/**
 * String Node representing a plain String.
 */
public class StringNode implements TextNode {
    private String value;

    public StringNode(String value) {
        this.value = value;
    }

    @Override
    public String toHTML() {
        return value;
    }
}
