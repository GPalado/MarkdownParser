package MarkdownProcessor.Nodes;

import MarkdownProcessor.Translators.Translator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Bold node representing text with the bold effect applied.
 */
public class BoldNode implements CollectorNode {
    private List<TextNode> children;

    public BoldNode(String s) {
        children = new ArrayList<>();
        // TODO: parse following content to identify where effect begins, ends, content, etc.
    }

    @Override
    public String accept(Translator t) {
        return t.translateBold(this);
    }

    @Override
    public String toString() {
        return "**" +
                Stream.of(children)
                .map(child -> child.toString())
                .collect(Collectors.joining()) +
                "**";
    }

    @Override
    public List<TextNode> getChildren() {
        return children;
    }
}
