package Tests;

import MarkdownProcessor.Nodes.*;
import org.junit.Test;

import java.util.List;

public class HTMLTranslator_Tests {

    private static BoldNode getBoldNode(List<TextNode> children) {
        return new BoldNode(children);
    }

    private static EmphasisNode getEmphasisNode(List<TextNode> children) {
        return new EmphasisNode(children);
    }

    private static LineNode getLineNode(List<TextNode> children) {
        return new LineNode(children);
    }

    private static ParagraphNode getParagraphNode(List<TextNode> children) {
        return new ParagraphNode(children);
    }

    private static MarkdownFileNode getMarkdownFileNode(List<TextNode> children) {
        return new MarkdownFileNode(children);
    }

    private static HeaderNode getHeaderNode(List<TextNode> children, int count) {
        return new HeaderNode(count, children);
    }

    private static StringNode getStringNode(String string) {
        return new StringNode(string);
    }

//    @Test
//    public void translate_
}
