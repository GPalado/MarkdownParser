package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.NumberedListNode;
import MarkdownProcessor.Nodes.TextNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumberedListRule implements StructureRule {
    @Override
    public TextNode applyStructure(Scanner s) {
        List<TextNode> children = new ArrayList<>();
        while(s.hasNextLine() && meetsCondition(s)){
            //TODO confirm number of spaces
            s.reset();
            s.next();
            String line = s.nextLine().trim();
            children.add(new LineRule().applyStructure(new Scanner(line)));
        }
        return new NumberedListNode(children);
    }

    @Override
    public boolean meetsCondition(Scanner s) {
        //TODO: figure out nested?
        s.useDelimiter("\n");
        return s.hasNext("[0-9]{1}.{1}[\\s]+.+");
    }
}
