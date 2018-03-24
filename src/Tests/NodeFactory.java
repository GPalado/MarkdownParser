package Tests;

import MarkdownProcessor.Nodes.*;

import java.util.List;

public class NodeFactory {

    public static BoldNode getBoldNode(List<TextNode> children) {
        return new BoldNode(children);
    }

    public static EmphasisNode getEmphasisNode(List<TextNode> children) {
        return new EmphasisNode(children);
    }

    public static LineNode getLineNode(List<TextNode> children) {
        return new LineNode(children);
    }

    public static ParagraphNode getParagraphNode(List<TextNode> children) {
        return new ParagraphNode(children);
    }

    public static MarkdownFileNode getMarkdownFileNode(List<TextNode> children) {
        return new MarkdownFileNode(children);
    }

    public static HeaderNode getHeaderNode(List<TextNode> children, int count) {
        return new HeaderNode(count, children);
    }

    public static StringNode getStringNode(String string) {
        return new StringNode(string);
    }

    public static BulletedListNode getBulletedListNode(List<TextNode> children){
        return new BulletedListNode(children);
    }

    public static NumberedListNode getNumberedListNode(List<TextNode> children){
        return new NumberedListNode(children);
    }

    public static SeparatorNode getSeparatorNode(){
        return new SeparatorNode();
    }
}
