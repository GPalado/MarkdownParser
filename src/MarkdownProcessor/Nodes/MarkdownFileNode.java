package MarkdownProcessor.Nodes;

import MarkdownProcessor.Translators.Translator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MarkdownFileNode implements CollectorNode {
    List<TextNode> children;

    public MarkdownFileNode(List<TextNode> children) {
        this.children = children;
    }

    @Override
    public String accept(Translator t) {
        return t.translateMarkdownFile(this);
    }

    @Override
    public String toString() {
        return "Markdown" +
                Stream.of(children)
                .map(child -> child.toString())
                .collect(Collectors.joining());
    }

    @Override
    public List<TextNode> getChildren() {
        return children;
    }
}
