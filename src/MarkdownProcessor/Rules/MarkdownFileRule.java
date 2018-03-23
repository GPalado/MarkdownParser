package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.MarkdownFileNode;
import MarkdownProcessor.Nodes.TextNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MarkdownFileRule implements StructureRule {

    public static final List<StructureRule> STRUCTURE_RULES =
            Arrays.asList(new HashRule(), new BulletedListRule(), new NumberedListRule());

    public MarkdownFileRule(){}

    @Override
    public TextNode applyStructure(Scanner s) {
        List<TextNode> paragraphs = new ArrayList<>();
        boolean foundRuleMatch;
        while(s.hasNextLine()){
            foundRuleMatch = false;
            for(StructureRule r : STRUCTURE_RULES) {
                if(r.meetsCondition(s)){
                    System.out.println("Matched " + r.getClass());
                    paragraphs.add(r.applyStructure(s));
                    foundRuleMatch = true;
                    break;
                }
            }
            if(!foundRuleMatch){
                System.out.println("Matched none - para");
                paragraphs.add(new ParagraphRule().applyStructure(s));
            }
        }
        return new MarkdownFileNode(paragraphs);
    }

    @Override
    public boolean meetsCondition(Scanner s) {
        return true;
    }
}
