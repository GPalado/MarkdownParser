package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.SeparatorNode;
import MarkdownProcessor.Nodes.TextNode;

import java.util.Scanner;

public class SeparatorRule implements StructureRule {

    @Override
    public TextNode applyStructure(Scanner s) {
        s.reset();
        s.nextLine();
        return new SeparatorNode();
    }

    @Override
    public boolean meetsCondition(Scanner s) {
        s.useDelimiter("\n");
        return s.hasNext("-{3,}");
    }
}
