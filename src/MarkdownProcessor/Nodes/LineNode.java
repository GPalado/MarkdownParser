package MarkdownProcessor.Nodes;

import MarkdownProcessor.Translators.Translator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Line Node specifying a line of text. Can consist of several {@link TextNode}s.
 */
public class LineNode implements CollectorNode {
    private List<TextNode> children;

    public LineNode(List<TextNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return Stream.of(children)
                .map(child -> child.toString())
                .collect(Collectors.joining());
    }

    @Override
    public String accept(Translator t) {
        return t.translateLine(this);
    }

    @Override
    public List<TextNode> getChildren() {
        return children;
    }
}
