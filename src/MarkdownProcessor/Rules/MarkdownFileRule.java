package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.MarkdownFileNode;
import MarkdownProcessor.Nodes.TextNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MarkdownFileRule implements StructureRule {

    public MarkdownFileRule(){}

    @Override
    public TextNode applyStructure(Scanner s) {
        System.out.println("Markdown applyStructure");
        List<TextNode> paragraphs = new ArrayList<>();
        while(s.hasNextLine()){
            paragraphs.add(new ParagraphRule().applyStructure(s));
        }
        return new MarkdownFileNode(paragraphs);
    }
}
