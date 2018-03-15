package Nodes;

import Translators.Translator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MarkdownFileNode implements TextNode {
    List<LineNode> fileContent;

    public MarkdownFileNode(String line) {
        // TODO: create children
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
