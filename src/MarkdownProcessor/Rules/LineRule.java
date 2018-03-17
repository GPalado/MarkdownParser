package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.LineNode;
import MarkdownProcessor.Nodes.TextNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LineRule implements StructureRule {

    public LineRule(){}

    @Override
    public TextNode applyStructure(Scanner s) {
        System.out.println("Line applyStructure");
        List<TextNode> children = new ArrayList<>();
        while(s.hasNext()){
            System.out.println("Line s.hasNext");
            children.add(ParserHelper.applyEffectRules(s));
        }
        System.out.println("Line returned");
        return new LineNode(children);
    }
}
