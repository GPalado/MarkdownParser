package MarkdownProcessor.Nodes;

import MarkdownProcessor.Translators.Translator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Emphasis Node representing text with the italics effect applied.
 */
public class EmphasisNode implements CollectorNode {
    private List<TextNode> children;

    public EmphasisNode(Scanner scanner) {
        children = new ArrayList<>();
        // TODO: parse following content to identify where effect begins, ends, content, etc.
    }

    @Override
    public String accept(Translator t){
        return t.translateEmphasis(this);
    }

    @Override
    public String toString() {
        return "*" +
                Stream.of(children)
                .map(child -> child.toString())
                .collect(Collectors.joining()) +
                "*";
    }

    @Override
    public List<TextNode> getChildren() {
        return children;
    }
}
