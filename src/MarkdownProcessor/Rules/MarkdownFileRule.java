package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.MarkdownFileNode;
import MarkdownProcessor.Nodes.TextNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MarkdownFileRule implements StructureRule {

    private static final List<StructureRule> rules = Arrays.asList(new HashRule(), new BulletedListRule(), new ParagraphRule());

    public MarkdownFileRule(){}

    @Override
    public TextNode applyStructure(Scanner s) {
        /**
         * TODO: Note: lines with header and bulleted list are not in paras.
         */
        List<TextNode> paragraphs = new ArrayList<>();
        while(s.hasNextLine()){
            for(StructureRule r : rules) {
                if(r.meetsCondition(s)){
                    paragraphs.add(r.applyStructure(s));
                    break;
                }
            }
        }
        return new MarkdownFileNode(paragraphs);
    }

    @Override
    public boolean meetsCondition(Scanner s) {
        return true;
    }
}
