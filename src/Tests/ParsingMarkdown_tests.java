package Tests;

import MarkdownProcessor.Rules.ParserHelper;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

import static Tests.NodeFactory.*;
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
}
