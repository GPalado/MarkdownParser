package MarkdownProcessor.Nodes;

import MarkdownProcessor.Translators.Translator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Paragraph Node specifying a paragraph break.
 */
public class ParagraphNode implements CollectorNode {
    private List<TextNode> lines;

    public ParagraphNode(Scanner scanner) {
        lines = new ArrayList<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.equals("")) { //if empty line
                scanThroughEmptyLines(scanner);
                return;
            }
            lines.add(new LineNode(line));
        }
    }

    private void scanThroughEmptyLines(Scanner scanner){
        //TODO check this logic.
        while(scanner.hasNext("")){
            scanner.nextLine();
        }
    }

    @Override
    public String toString() {
        return "\n";
    }

    @Override
    public String accept(Translator t) {
        return t.translateParagraph(this);
    }

    @Override
    public List<TextNode> getChildren() {
        return lines;
    }
}
