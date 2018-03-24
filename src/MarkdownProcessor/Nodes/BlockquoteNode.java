package MarkdownProcessor.Nodes;

import MarkdownProcessor.Translators.Translator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BlockquoteNode implements CollectorNode {
    private List<TextNode> children;

    public BlockquoteNode(List<TextNode> children){
        this.children = new ArrayList<>();
        this.children.add(new ParagraphNode(children));
    }

    @Override
    public List<TextNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public String accept(Translator t) {
        return t.translateBlockquote(this);
    }

    @Override
    public String toString(){
        return "Blockquote[" +
                children.stream()
                .map(child -> child.toString())
                .collect(Collectors.joining()) +
                "]";
    }
}
