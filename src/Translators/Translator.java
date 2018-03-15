package Visitors;

import Nodes.*;

/**
 * Interface specifying the different translators that can be applied to {@link Nodes.TextNode}.
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
}
