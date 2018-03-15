package Nodes;

/**
 * Generic ParserNode interface which specifies the languages to which the node's content can be translated to.
 */
public interface ParserNode {

    /**
     * Returns the HTML equivalent of this node's content.
     *
     * @return String representation of this node in HTML.
     */
    String toHTML();
}
