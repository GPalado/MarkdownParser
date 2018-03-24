package MarkdownProcessor.Nodes;

import MarkdownProcessor.Translators.Translator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Emphasis Node representing text with the italics effect applied.
 */
public class EmphasisNode implements CollectorNode {
    private List<TextNode> children;

    public EmphasisNode(List<TextNode> children) {
        this.children = children;
    }

    @Override
    public String accept(Translator t){
        return t.translateEmphasis(this);
    }

    @Override
    public String toString() {
        return "Italics[" +
                children.stream()
                .map(child -> child.toString())
                .collect(Collectors.joining()) +
                "]";
    }

    @Override
    public List<TextNode> getChildren() {
        return Collections.unmodifiableList(children);
    }
}
