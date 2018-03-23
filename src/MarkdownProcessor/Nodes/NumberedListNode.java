package MarkdownProcessor.Nodes;

import MarkdownProcessor.Translators.Translator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NumberedListNode implements CollectorNode {
    private List<TextNode> children;

    public NumberedListNode(List<TextNode> children){
        this.children = children;
    }

    @Override
    public List<TextNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public String accept(Translator t) {
        return t.translateNumberedList(this);
    }

    @Override
    public String toString(){
        return "NumberedList[" +
                children.stream()
                        .map(child -> child.toString())
                        .collect(Collectors.joining()) +
                "]";
    }
}
