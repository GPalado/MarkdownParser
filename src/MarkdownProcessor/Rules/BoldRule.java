package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.BoldNode;
import MarkdownProcessor.Nodes.TextNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoldRule implements EffectRule {

    public BoldRule(){}

    @Override
    public boolean meetsCondition(Scanner s) {
        return s.hasNext("\\*\\*[a-zA-Z0-9//\\\\`~!@#$%^&*()-=_+\\[\\]{}|'\",?><,.:;\\s]+\\*\\*");
    }

    @Override
    public TextNode applyAction(Scanner s) {
        String line = s.nextLine();
        line = line.substring(2, line.length()-1);
        Scanner newScanner = new Scanner(line);
        List<TextNode> children = new ArrayList<>();
        while(newScanner.hasNext()){
            children.addAll(ParserHelper.applyEffectRules(newScanner));
        }
        return new BoldNode(children);
    }
}
