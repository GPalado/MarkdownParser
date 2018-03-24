package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.BlockquoteNode;
import MarkdownProcessor.Nodes.LineNode;
import MarkdownProcessor.Nodes.TextNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlockquoteRule implements StructureRule {

    @Override
    public TextNode applyStructure(Scanner s) {
        List<TextNode> children = new ArrayList<>();
        while(meetsCondition(s)){
            s.reset();
            s.next();
            s.skip("\\s*");
            children.add(new LineNode(ParserHelper.applyEffectRules(new Scanner(s.nextLine()))));
        }
        return new BlockquoteNode(children);
    }

    @Override
    public boolean meetsCondition(Scanner s) {
        s.useDelimiter("\n");
        return s.hasNext(">{1}\\s{1}.*");
    }
}
