package MarkdownProcessor.Translators;

import MarkdownProcessor.Nodes.*;

/**
 * Interface specifying the different translators that can be applied to {@link MarkdownProcessor.Nodes.TextNode}.
 */
public interface Translator {

    /**
     * Translates a Header node.
     * @param node
     * @return
     */
    String translateHeader(HeaderNode node);

    /**
     * Translates a Line node.
     * @param node
     * @return
     */
    String translateLine(LineNode node);

    /**
     * Translates an Emphasis node.
     * @param node
     * @return
     */
    String translateEmphasis(EmphasisNode node);

    /**
     * Translates a Bold node.
     * @param node
     * @return
     */
    String translateBold(BoldNode node);

    /**
     * Translates a paragraph node.
     * @param node
     * @return
     */
    String translateParagraph(ParagraphNode node);

    /**
     * Translates a MarkdownFile node.
     * @param node
     * @return
     */
    String translateMarkdownFile(MarkdownFileNode node);

    /**
     * Translates a String node.
     * @param node
     * @return
     */
    String translateString(StringNode node);

    /**
     * Translates a NumberedList node.
     * @param node
     * @return
     */
    String translateNumberedList(NumberedListNode node);

    /**
     * Translates a BulletedList node.
     * @param node
     * @return
     */
    String translateBulletedList(BulletedListNode node);

    /**
     * Translates a Separator node.
     * @param node
     * @return
     */
    String translateSeparator(SeparatorNode node);

    /**
     * Translates a Blockquote node.
     * @param node
     * @return
     */
    String translateBlockquote(BlockquoteNode node);
}
