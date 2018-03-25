package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.ParagraphNode;
import MarkdownProcessor.Nodes.TextNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParagraphRule implements StructureRule {

    public ParagraphRule() {
    }

    @Override
    public TextNode applyStructure(Scanner s) {
        List<TextNode> lines = new ArrayList<>();
        while (s.hasNextLine() && meetsCondition(s)) {
            String line = s.nextLine();
            if (line.equals("")) {
                scanThroughEmptyLines(s);
                if (lines.isEmpty()) {
                    continue;
                } else {
                    break;
                }
            }
            lines.add(new LineRule().applyStructure(new Scanner(line)));
        }
        return new ParagraphNode(lines);
    }

    private void scanThroughEmptyLines(Scanner scanner) {
        while (scanner.hasNext(System.getProperty("line.separator"))) {
            scanner.nextLine();
        }
    }

    @Override
    public boolean meetsCondition(Scanner s) {
        for(StructureRule rule : MarkdownFileRule.STRUCTURE_RULES){
            if(rule.meetsCondition(s)){
                return false;
            }
        }
        return true;
    }
}
