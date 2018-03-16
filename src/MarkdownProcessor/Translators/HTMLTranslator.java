package MarkdownProcessor.Translators;

import MarkdownProcessor.Nodes.*;

import java.util.stream.Collectors;

public class HTMLTranslator implements Translator {
    @Override
    public String translateHeader(HeaderNode node) {
        return "<h" + node.getDepth() + ">" +
                node.getChildren().stream()
                        .map(n -> n.accept(this))
                        .collect(Collectors.joining()) +
                "</h" + node.getDepth() + ">";
    }

    @Override
    public String translateLine(LineNode node) {
        return node.getChildren().stream()
                .map(n -> n.accept(this))
                .collect(Collectors.joining());
    }

    @Override
    public String translateEmphasis(EmphasisNode node) {
        return "<em>" +
                node.getChildren().stream()
                        .map(n -> n.accept(this))
                        .collect(Collectors.joining()) +
                "</em>";
    }


    @Override
    public String translateBold(BoldNode node) {
        return "<strong>" +
                node.getChildren().stream()
                        .map(n -> n.accept(this))
                        .collect(Collectors.joining()) +
                "</strong>";
    }

    @Override
    public String translateParagraph(ParagraphNode node) {
        return "<p>" +
                node.getChildren().stream()
                        .map(n -> n.accept(this))
                        .collect(Collectors.joining()) +
                "</p>";
    }

    @Override
    public String translateMarkdownFile(MarkdownFileNode node) {
        return node.getChildren().stream()
                .map(n -> n.accept(this) + "\n") //TODO: confirm \n is needed here
                .collect(Collectors.joining());
    }

    @Override
    public String translateString(StringNode node) {
        return node.toString();
    }
}
