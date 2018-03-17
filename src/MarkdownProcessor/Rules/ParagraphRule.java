package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.LineNode;
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
        System.out.println("Paragraph applyStructure");
        List<TextNode> lines = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (line.equals("")) { //if empty line
                scanThroughEmptyLines(s);
                if (lines.isEmpty()) { //Paragraph body not read yet. Keep scanning.
                    continue;
                } else { //Paragraph body read - end of paragraph.
                    break;
                }
            }
            lines.add(new LineRule().applyStructure(new Scanner(line)));
        }
        return new ParagraphNode(lines);
    }

    private void scanThroughEmptyLines(Scanner scanner) {
        //TODO check this logic.
        while (scanner.hasNext("\n")) {
            System.out.println("scan through");
            scanner.nextLine();
        }
    }
}
