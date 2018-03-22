package MarkdownProcessor.Nodes;

import MarkdownProcessor.Translators.Translator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Paragraph Node specifying a paragraph break.
 */
public class ParagraphNode implements CollectorNode {
    private List<TextNode> lines;

    public ParagraphNode(List<TextNode> lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "Paragraph" +
                Stream.of(lines)
                        .map(child -> child.toString())
                        .collect(Collectors.joining());
    }

    @Override
    public String accept(Translator t) {
        return t.translateParagraph(this);
    }

    @Override
    public List<TextNode> getChildren() {
        return lines;
    }
}
