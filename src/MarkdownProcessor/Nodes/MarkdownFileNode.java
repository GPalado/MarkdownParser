package MarkdownProcessor.Nodes;

import MarkdownProcessor.Translators.Translator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MarkdownFileNode implements CollectorNode {
    List<TextNode> children;

    public MarkdownFileNode(Scanner scanner) {
        children = new ArrayList<>();
        while (scanner.hasNextLine()) {
            children.add(new ParagraphNode(scanner));
        }
    }

    @Override
    public String accept(Translator t) {
        return t.translateMarkdownFile(this);
    }

    @Override
    public String toString(){
        return Stream.of(children)
                .map(node -> toString())
                .collect(Collectors.joining());
    }

    @Override
    public List<TextNode> getChildren() {
        return children;
    }
}
