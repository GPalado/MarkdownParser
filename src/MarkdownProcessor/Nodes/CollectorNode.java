package MarkdownProcessor.Nodes;

import java.util.List;

/**
 * A type of text node that contains several children nodes.
 */
public interface CollectorNode extends TextNode {
    List<TextNode> getChildren();
}
