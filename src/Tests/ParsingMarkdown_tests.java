package Tests;

import MarkdownProcessor.Rules.ParserHelper;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ParsingMarkdown_tests {

    @Test
    public void parserHelper_parseString() {
        assertEquals("Markdown[Paragraph[Line[\"String\"]]]",
                ParserHelper.applyStructureRules(new Scanner("String")).toString());
    }

    @Test
    public void parserHelper_parseBold() {
        assertEquals("Markdown[Paragraph[Line[Bold[\"Bold\"]]]]",
                ParserHelper.applyStructureRules(new Scanner("**Bold**")).toString());
    }

    @Test
    public void parserHelper_parseEmphasis() {
        assertEquals("Markdown[Paragraph[Line[Italics[\"Italics\"]]]]",
                ParserHelper.applyStructureRules(new Scanner("*Italics*")).toString());
    }

    @Test
    public void parserHelper_parseHeader() {
        assertEquals("Markdown[Header1[\"Header1\"]]",
                ParserHelper.applyStructureRules(new Scanner("# Header1")).toString());
    }

    @Test
    public void parserHelper_parseLineParaAndMarkdown() {
        assertEquals("Markdown[Paragraph[Line[\"I am a Line\"]]]",
                ParserHelper.applyStructureRules(new Scanner("I am a Line")).toString());
    }

    @Test
    public void parserHelper_parseBulletedList() {
        assertEquals("Markdown[BulletedList[Line[\"I am a Line\"]Line[\"And another\"]]]",
                ParserHelper.applyStructureRules(new Scanner("* I am a Line\n* And another")).toString());
    }

    @Test
    public void parserHelper_parseNumberedList() {
        assertEquals("Markdown[NumberedList[Line[\"I am a Line\"]Line[\"And another\"]]]",
                ParserHelper.applyStructureRules(new Scanner("1. I am a Line\n1. And another")).toString());
    }

    @Test
    public void parserHelper_parseSeparator(){
        assertEquals("Markdown[Paragraph[Line[\"Hello\"]]Separator|Paragraph[Line[\"There\"]]]",
                ParserHelper.applyStructureRules(new Scanner("Hello\n---\nThere")).toString());
    }

    @Test
    public void parserHelper_parseBlockquote(){
        assertEquals("Markdown[Paragraph[Line[\"Hello\"]]Blockquote[Paragraph[Line[\"Hello\"]]]Paragraph[Line[\"There\"]]]",
                ParserHelper.applyStructureRules(new Scanner("Hello\n> Hello\nThere")).toString());
    }

    @Test
    public void parserHelper_parseMultiParagraphs(){
        assertEquals("Markdown[Paragraph[Line[\"Hello\"]]Paragraph[Line[\"There\"]]]",
                ParserHelper.applyStructureRules(new Scanner("Hello\n\nThere")).toString());
    }
}
