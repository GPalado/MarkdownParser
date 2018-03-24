package Tests;

import MarkdownProcessor.Nodes.*;
import MarkdownProcessor.Rules.MarkdownFileRule;
import MarkdownProcessor.Translators.HTMLTranslator;
import org.junit.Test;

import java.util.Arrays;

import static Tests.NodeFactory.*;
import static org.junit.Assert.assertEquals;

public class HTMLTranslator_Tests {
    private static final HTMLTranslator translator = new HTMLTranslator();

    @Test
    public void translate_header_test() {
        HeaderNode header = getHeaderNode(Arrays.asList(getStringNode("I am child")), 1);
        assertEquals("<h1>I am child</h1>", header.accept(translator));
    }

    @Test
    public void translate_paragraph_test() {
        ParagraphNode paragraph = getParagraphNode(Arrays.asList(getStringNode("I am para body")));
        assertEquals("<p>\nI am para body\n</p>", paragraph.accept(translator));
    }

    @Test
    public void translate_bold_test() {
        BoldNode bold = getBoldNode(Arrays.asList(getStringNode("I am bold")));
        assertEquals("<strong>I am bold</strong>", bold.accept(translator));
    }

    @Test
    public void translate_italics_test() {
        EmphasisNode italics = getEmphasisNode(Arrays.asList(getStringNode("I am italics")));
        assertEquals("<em>I am italics</em>", italics.accept(translator));
    }

    @Test
    public void translate_lineWithBoldAndString_test() {
        LineNode line = getLineNode(Arrays.asList(getStringNode("I am normal"), getBoldNode(Arrays.asList(getStringNode("I am bold")))));
        assertEquals("I am normal<strong>I am bold</strong>", line.accept(translator));
    }

    @Test
    public void translate_MarkdownWithHeaderAndPara_test() {
        MarkdownFileNode markdown = getMarkdownFileNode(
                Arrays.asList(
                        getHeaderNode(Arrays.asList(getStringNode("I am header")), 1),
                        getParagraphNode(Arrays.asList(getStringNode("I am paragraph")))
                )
        );
        assertEquals("<h1>I am header</h1>\n<p>\nI am paragraph\n</p>\n", markdown.accept(translator));
    }

    @Test
    public void translate_string_test() {
        StringNode string = getStringNode("I am string");
        assertEquals("I am string", string.accept(translator));
    }

    @Test
    public void translate_numberedList_test() {
        NumberedListNode nList = getNumberedListNode(
                Arrays.asList(getLineNode(
                        Arrays.asList(getStringNode("hello"))
                )));
        assertEquals("<ol>\n<li>hello</li>\n</ol>", nList.accept(translator));
    }

    @Test
    public void translate_bulletedList_test() {
        BulletedListNode bList = getBulletedListNode(
                Arrays.asList(getLineNode(
                        Arrays.asList(getStringNode("hello"))
                )));
        assertEquals("<ul>\n<li>hello</li>\n</ul>", bList.accept(translator));
    }

    @Test
    public void translate_parasAndSeparator_test() {
        MarkdownFileNode markdown = getMarkdownFileNode(
                Arrays.asList(getParagraphNode(
                        Arrays.asList(getLineNode(
                                Arrays.asList(getStringNode("Hello"))
                        ))
                ), getSeparatorNode(), getParagraphNode(
                        Arrays.asList(getLineNode(
                                Arrays.asList(getStringNode("There"))
                        ))
                ))
        );
        assertEquals("<p>\nHello\n</p>\n<hr>\n<p>\nThere\n</p>\n", markdown.accept(translator));
    }

    @Test
    public void translate_parasAndBlockquote_test() {
        MarkdownFileNode markdown = getMarkdownFileNode(
                Arrays.asList(getParagraphNode(
                        Arrays.asList(getLineNode(
                                Arrays.asList(getStringNode("Hello"))
                        ))
                ), getBlockquoteNode(
                        Arrays.asList(getLineNode(
                                Arrays.asList(getStringNode("Hello"))
                        ))
                ), getParagraphNode(
                        Arrays.asList(getLineNode(
                                Arrays.asList(getStringNode("There"))
                        ))
                ))
        );
        assertEquals("<p>\nHello\n</p>\n<blockquote>\n<p>\nHello\n</p>\n</blockquote>\n<p>\nThere\n</p>\n",
                markdown.accept(translator));
    }
}
