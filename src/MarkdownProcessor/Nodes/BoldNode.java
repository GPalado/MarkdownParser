package MarkdownProcessor.Nodes;

import MarkdownProcessor.Translators.Translator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Bold node representing text with the bold effect applied.
 */
public class BoldNode implements CollectorNode {
    private List<TextNode> children;

    public BoldNode(Scanner scanner) {
        children = new ArrayList<>();
        // TODO: parse following content to identify where effect begins, ends, content, etc.
    }

    @Override
    public String accept(Translator t) {
        return t.translateBold(this);
    }

    @Override
    public String toString() {
        return "**" + super.toString() + "**";
    }

    @Override
    public List<TextNode> getChildren() {
        return children;
    }
}
