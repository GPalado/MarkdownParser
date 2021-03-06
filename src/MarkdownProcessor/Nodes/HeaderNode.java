package MarkdownProcessor.Nodes;

import MarkdownProcessor.Translators.Translator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Header node representing a line of text with the header effect. Header level is specified in markdown by the number
 * of hashes.
 */
public class HeaderNode implements CollectorNode {
    private List<TextNode> children;
    private int depth;

    public HeaderNode(int depth, List<TextNode> children) {
        this.depth = depth;
        this.children = children;
    }

    public int getDepth(){
        return depth;
    }

    @Override
    public String toString() {
        return "Header" + depth + "[" +
                children.stream()
                .map(child -> child.toString())
                .collect(Collectors.joining()) +
                "]";
    }

    @Override
    public String accept(Translator t) {
        return t.translateHeader(this);
    }

    @Override
    public List<TextNode> getChildren() {
        return Collections.unmodifiableList(children);
    }
}
