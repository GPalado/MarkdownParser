package MarkdownProcessor.Translators;

import MarkdownProcessor.Nodes.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HTMLTranslator implements Translator {
    @Override
    public String translateHeader(HeaderNode node) {
        return "<h" + node.getDepth() + ">" +
                Stream.of(node).map(n -> n.accept(this)).collect(Collectors.joining()) +
                "</h" + node.getDepth() + ">";
    }

    @Override
    public String translateLine(LineNode node) {
        return Stream.of(node).map(n -> n.accept(this)).collect(Collectors.joining());
    }

    @Override
    public String translateEmphasis(EmphasisNode node) {
        return "<em>" + Stream.of(node).map(n -> n.accept(this)).collect(Collectors.joining()) + "</em>";
    }


    @Override
    public String translateBold(BoldNode node) {
        return "<strong>" + Stream.of(node).map(n -> n.accept(this)).collect(Collectors.joining()) + "</strong>";
    }

    @Override
    public String translateParagraph(ParagraphNode node) {
        return "<p>" + Stream.of(node).map(n -> n.accept(this)).collect(Collectors.joining()) + "</p>";
    }

    @Override
    public String translateMarkdownFile(MarkdownFileNode node) {
        return Stream.of(node).map(n -> n.accept(this) + "\n").collect(Collectors.joining());
    }

    @Override
    public String translateString(StringNode node) {
        return node.toString();
    }
}
