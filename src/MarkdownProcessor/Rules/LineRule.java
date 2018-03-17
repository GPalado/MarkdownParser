package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.LineNode;
import MarkdownProcessor.Nodes.TextNode;

import java.util.List;
import java.util.Scanner;

public class LineRule implements StructureRule {

    public LineRule(){}

    @Override
    public TextNode applyStructure(Scanner s) {
        List<TextNode> children =  ParserHelper.applyEffectRules(s);
        return new LineNode(children);
    }
}
