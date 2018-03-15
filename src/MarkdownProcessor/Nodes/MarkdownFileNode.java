package MarkdownProcessor.Nodes;

import MarkdownProcessor.Translators.Translator;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MarkdownFileNode implements TextNode {
    List<LineNode> fileContent;

    public MarkdownFileNode(Scanner scanner) {
        // TODO: create children
        while (scanner.hasNextLine()) {
            fileContent.add(new LineNode(scanner.nextLine()));
        }
    }

    @Override
    public String accept(Translator t) {
        return t.translateMarkdownFile(this);
    }

    @Override
    public String toString(){
        return Stream.of(fileContent)
                .map(node -> toString())
                .collect(Collectors.joining());
    }
}
