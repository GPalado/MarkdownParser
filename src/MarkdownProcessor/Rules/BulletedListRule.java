package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.BulletedListNode;
import MarkdownProcessor.Nodes.TextNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BulletedListRule implements StructureRule {
    @Override
    public boolean meetsCondition(Scanner s) {
        //TODO: figure out nested?
        s.useDelimiter("\n");
        return s.hasNext("[\\\\*]{1}[\\s]+.+");
    }

    @Override
    public TextNode applyStructure(Scanner s) {
        List<TextNode> children = new ArrayList<>();
        while(s.hasNextLine() && meetsCondition(s)){
            s.useDelimiter("[\\\\s]*");
            s.next();
            String line = s.nextLine().trim();
            children.add(new LineRule().applyStructure(new Scanner(line)));
        }
        return new BulletedListNode(children);
    }
}
