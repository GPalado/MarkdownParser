package MarkdownToHTMLTests;

import MarkdownProcessor.MarkdownProcessor;
import MarkdownProcessor.Nodes.MarkdownFileNode;
import MarkdownProcessor.Translators.HTMLTranslator;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;


public class HTML_Tests {
    private static final MarkdownProcessor processor = new MarkdownProcessor();

    @Test
    void paragraph_oneBreak_test(){
        MarkdownFileNode markdown = processor.parseMarkdown(new Scanner("para1\n\npara2"));
        assertEquals("<p>para1</p><p>para2</p>", markdown.accept(new HTMLTranslator()));
    }

    @Test
    void paragraph_twoBreak_test(){
        //TODO: check scanner of string
        MarkdownFileNode markdown = processor.parseMarkdown(new Scanner("para1\n\n\npara2"));
        // Any number of breaks should result in one break
        assertEquals("<p>para1</p><p>para2</p>", markdown.accept(new HTMLTranslator()));
    }

    @Test
    void italics_basecase_test(){
        MarkdownFileNode markdown = processor.parseMarkdown(new Scanner("*italics*"));
        assertEquals("<em>italics</em>", markdown.accept(new HTMLTranslator()));
    }

    @Test
    void italics_rightHeavy_test(){
        MarkdownFileNode markdown = processor.parseMarkdown(new Scanner("*italics**"));
        assertEquals("<em>italics</em>*", markdown.accept(new HTMLTranslator()));
    }

    @Test
    void italics_leftHeavy_test(){
        MarkdownFileNode markdown = processor.parseMarkdown(new Scanner("**italics*"));
        assertEquals("*<em>italics</em>", markdown.accept(new HTMLTranslator()));
    }

    @Test
    void italics_triGroupingRightHeavy_test(){
        MarkdownFileNode markdown = processor.parseMarkdown(new Scanner("*ita*lics**"));
        assertEquals("<em>ita</em>lics**", markdown.accept(new HTMLTranslator()));
    }
    @Test
    void italics_triGroupingLeftHeavy_test(){
        MarkdownFileNode markdown = processor.parseMarkdown(new Scanner("**ita*lics*"));
        assertEquals("**ita<em>lics</em>", markdown.accept(new HTMLTranslator()));
    }

    @Test
    void bold_basecase_test(){
        MarkdownFileNode markdown = processor.parseMarkdown(new Scanner("**bold**"));
        assertEquals("<strong>bold</strong>", markdown.accept(new HTMLTranslator()));
    }

    @Test
    void bold_rightHeavy_test(){
        MarkdownFileNode markdown = processor.parseMarkdown(new Scanner("**bold***"));
        assertEquals("<strong>bold</strong>*", markdown.accept(new HTMLTranslator()));
    }

    @Test
    void bold_leftHeavy_test(){
        MarkdownFileNode markdown = processor.parseMarkdown(new Scanner("***bold**"));
        assertEquals("*<strong>bold</strong>", markdown.accept(new HTMLTranslator()));
    }

    @Test
    void bold_triGroupRightHeavy_test(){
        MarkdownFileNode markdown = processor.parseMarkdown(new Scanner("**bo**ld***"));
        assertEquals("<strong>bo</strong>ld***", markdown.accept(new HTMLTranslator()));
    }

    @Test
    void bold_triGroupLeftHeavy_test(){
        MarkdownFileNode markdown = processor.parseMarkdown(new Scanner("***bo**ld**"));
        assertEquals("<em><strong>bo</strong>ld</em>*", markdown.accept(new HTMLTranslator()));
    }

    @Test
    void header_firstLevel_test(){
        MarkdownFileNode markdown = processor.parseMarkdown(new Scanner("# header1"));
        assertEquals("<h1>header1</h1>", markdown.accept(new HTMLTranslator()));
    }

    @Test
    void header_secondLevel_test(){
        MarkdownFileNode markdown = processor.parseMarkdown(new Scanner("## header2"));
        assertEquals("<h2>header2</h2>", markdown.accept(new HTMLTranslator()));
    }

    @Test
    void header_thirdLevel_test(){
        MarkdownFileNode markdown = processor.parseMarkdown(new Scanner("### header3"));
        assertEquals("<h3>header3</h3>", markdown.accept(new HTMLTranslator()));
    }

    @Test
    void header_fourthLevel_test(){
        MarkdownFileNode markdown = processor.parseMarkdown(new Scanner("#### header4"));
        assertEquals("<h4>header4</h4>", markdown.accept(new HTMLTranslator()));
    }

    @Test
    void header_fifthLevel_test(){
        MarkdownFileNode markdown = processor.parseMarkdown(new Scanner("##### header5"));
        assertEquals("<h5>header5</h5>", markdown.accept(new HTMLTranslator()));
    }

    @Test
    void header_upperBound_test(){
        MarkdownFileNode markdown = processor.parseMarkdown(new Scanner("####### headerX>5"));
        assertEquals("<h5>headerx>5</h5>", markdown.accept(new HTMLTranslator()));
    }

    @Test
    void header_missingSpace_test(){
        MarkdownFileNode markdown = processor.parseMarkdown(new Scanner("###notHeader"));
        assertEquals("###notHeader", markdown.accept(new HTMLTranslator()));
    }
}
