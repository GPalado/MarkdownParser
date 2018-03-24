package Tests;

import MarkdownProcessor.MarkdownProcessor;
import MarkdownProcessor.Nodes.TextNode;
import MarkdownProcessor.Translators.HTMLTranslator;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;


public class MarkdownProcessor_Tests {
    private static final MarkdownProcessor processor = new MarkdownProcessor();

    @Test
    public void paragraph_oneBreak_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("para1\n\npara2"));
        assertEquals("<p>\npara1\n</p>\n<p>\npara2\n</p>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void paragraph_twoBreak_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("para1\n\n\npara2")); // Any number of breaks should result in one break
        assertEquals("<p>\npara1\n</p>\n<p>\npara2\n</p>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void italics_basecase_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("*italics*"));
        assertEquals("<p>\n<em>italics</em>\n</p>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void italics_noPair_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("*notItalics"));
        assertEquals("<p>\n*notItalics\n</p>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void italics_rightHeavy_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("*italics**"));
        assertEquals("<p>\n<em>italics</em>*\n</p>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void italics_leftHeavy_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("**italics*"));
        assertEquals("<p>\n*<em>italics</em>\n</p>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void bold_basecase_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("**bold**"));
        assertEquals("<p>\n<strong>bold</strong>\n</p>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void bold_rightHeavy_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("**bold***"));
        assertEquals("<p>\n<strong>bold</strong>*\n</p>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void bold_leftHeavy_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("***bold**"));
        assertEquals("<p>\n*<strong>bold</strong>\n</p>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void header_firstLevel_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("# header1"));
        assertEquals("<h1>header1</h1>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void header_secondLevel_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("## header2"));
        assertEquals("<h2>header2</h2>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void header_thirdLevel_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("### header3"));
        assertEquals("<h3>header3</h3>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void header_fourthLevel_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("#### header4"));
        assertEquals("<h4>header4</h4>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void header_fifthLevel_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("##### header5"));
        assertEquals("<h5>header5</h5>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void header_sixthLevel_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("###### header6"));
        assertEquals("<h6>header6</h6>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void header_exceedsSixthLevel_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("####### notHeader"));
        assertEquals("<p>\n####### notHeader\n</p>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void header_missingSpace_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("###notHeader"));
        assertEquals("<p>\n###notHeader\n</p>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void bold_invalidSpace_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("** notBold**"));
        assertEquals("<p>\n** notBold**\n</p>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void bold_invalidSpace_test2(){
        TextNode markdown = processor.parseMarkdown(new Scanner("**notBold **"));
        assertEquals("<p>\n**notBold **\n</p>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void bold_validSpace_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner(" **Bold**"));
        assertEquals("<p>\n<strong>Bold</strong>\n</p>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void bold_validSpace_test2(){
        TextNode markdown = processor.parseMarkdown(new Scanner("**Bold** "));
        assertEquals("<p>\n<strong>Bold</strong>\n</p>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void italics_invalidSpace_test2(){
        TextNode markdown = processor.parseMarkdown(new Scanner("*notItalics *"));
        assertEquals("<p>\n*notItalics *\n</p>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void italics_validSpace_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner(" *Italics*"));
        assertEquals("<p>\n<em>Italics</em>\n</p>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void italics_validSpace_test2(){
        TextNode markdown = processor.parseMarkdown(new Scanner("*Italics* "));
        assertEquals("<p>\n<em>Italics</em>\n</p>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void nested_dualGroup_test2(){
        TextNode markdown = processor.parseMarkdown(new Scanner("***Both***"));
        assertEquals("<p>\n<em><strong>Both</strong></em>\n</p>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void coreCombo_headerItalics_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("# *Hello*"));
        assertEquals("<h1><em>Hello</em></h1>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void coreCombo_headerBold_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("# **Hello**"));
        assertEquals("<h1><strong>Hello</strong></h1>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void coreCombo_headerItalicsBold_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("# ***Hello***"));
        assertEquals("<h1><em><strong>Hello</strong></em></h1>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void coreCombo_headerParaItalics_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("# Hello\nHello *there*"));
        assertEquals("<h1>Hello</h1>\n<p>\nHello <em>there</em>\n</p>\n", markdown.accept(new HTMLTranslator()));
    }

        @Test
    public void bulletedList_basecase_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("* Bulleted List\n* Content"));
        assertEquals("<ul>\n<li>Bulleted List</li>\n<li>Content</li>\n</ul>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void numberedList_basecase_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("1. Numbered List\n1. Content"));
        assertEquals("<ol>\n<li>Numbered List</li>\n<li>Content</li>\n</ol>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void listsAndCoreCombo_headerAndBulletedList_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("## Header2\n* Content\n* More Content"));
        assertEquals("<h2>Header2</h2>\n<ul>\n<li>Content</li>\n<li>More Content</li>\n</ul>\n", markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void listsAndCoreCombo_paragraphAndNumberedList_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("Random text yay\nAnd another line of text\n1. Content\n2. More Content"));
        assertEquals("<p>\nRandom text yay\nAnd another line of text\n</p>\n<ol>\n<li>Content</li>\n<li>More Content</li>\n</ol>\n",
                markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void parasAndSeparatorCombo_basecase_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("Yo wassup I'm a para\n---\nAnd I'm another"));
        assertEquals("<p>\nYo wassup I'm a para\n</p>\n<hr>\n<p>\nAnd I'm another\n</p>\n",
                markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void parasAndSeparatorCombo_invalidSeparator_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("Yo wassup I'm a para\n---And I'm part of the same"));
        assertEquals("<p>\nYo wassup I'm a para\n---And I'm part of the same\n</p>\n",
                markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void parasAndSeparatorCombo_invalidSeparator_test2(){
        TextNode markdown = processor.parseMarkdown(new Scanner("Yo wassup I'm a para\n--\nAnd I'm part of the same"));
        assertEquals("<p>\nYo wassup I'm a para\n--\nAnd I'm part of the same\n</p>\n",
                markdown.accept(new HTMLTranslator()));
    }

    @Test
    public void parasAndBlockquote_basecase_test(){
        TextNode markdown = processor.parseMarkdown(new Scanner("Yo wassup I'm a para\n> And I'm blockquote\n> Me too\nNot me"));
        assertEquals("<p>\nYo wassup I'm a para\n</p>\n<blockquote>\n<p>\n" +
                        "And I'm blockquote\nMe too\n</p>\n</blockquote>\n<p>\nNot me\n</p>\n",
                markdown.accept(new HTMLTranslator()));
    }
}
