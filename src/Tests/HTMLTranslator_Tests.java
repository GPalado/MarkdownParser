package Tests;

import MarkdownProcessor.Nodes.*;
import MarkdownProcessor.Translators.HTMLTranslator;
import org.junit.Test;

import java.util.Arrays;

import static Tests.NodeFactory.*;
import static org.junit.Assert.assertEquals;

public class HTMLTranslator_Tests {
    private static final HTMLTranslator translator = new HTMLTranslator();

    @Test
    public void translate_header_test(){
        HeaderNode header = getHeaderNode(Arrays.asList(getStringNode("I am child")), 1);
        assertEquals("<h1>I am child</h1>", header.accept(translator));
    }

    @Test
    public void translate_paragraph_test(){
        ParagraphNode paragraph = getParagraphNode(Arrays.asList(getStringNode("I am para body")));
        assertEquals("<p>I am para body</p>", paragraph.accept(translator));
    }

    @Test
    public void translate_bold_test(){
        BoldNode bold = getBoldNode(Arrays.asList(getStringNode("I am bold")));
        assertEquals("<strong>I am bold</strong>", bold.accept(translator));
    }

    @Test
    public void translate_italics_test(){
        EmphasisNode italics = getEmphasisNode(Arrays.asList(getStringNode("I am italics")));
        assertEquals("<em>I am italics</em>", italics.accept(translator));
    }

    @Test
    public void translate_lineWithBoldAndString_test(){
        LineNode line = getLineNode(Arrays.asList(getStringNode("I am normal"), getBoldNode(Arrays.asList(getStringNode("I am bold")))));
        assertEquals("I am normal<strong>I am bold</strong>", line.accept(translator));
    }

    @Test
    public void translate_MarkdownWithHeaderAndPara_test(){
        MarkdownFileNode markdown = getMarkdownFileNode(
                Arrays.asList(
                        getHeaderNode(Arrays.asList(getStringNode("I am header")), 1),
                        getParagraphNode(Arrays.asList(getStringNode("I am paragraph")))
                )
        );
        assertEquals("<h1>I am header</h1>\n<p>I am paragraph</p>\n", markdown.accept(translator));
    }

    @Test
    public void translate_string_test(){
        StringNode string = getStringNode("I am string");
        assertEquals("I am string", string.accept(translator));
    }
}
