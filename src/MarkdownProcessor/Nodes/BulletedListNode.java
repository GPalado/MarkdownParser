package MarkdownProcessor.Nodes;

import MarkdownProcessor.Translators.Translator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BulletedListNode implements CollectorNode {
    private List<TextNode> children;

    public BulletedListNode(List<TextNode> children){
        this.children = children;
    }

    @Override
    public List<TextNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public String accept(Translator t) {
        return t.translateBulletedList(this);
    }

    @Override
    public String toString(){
        return "BulletedList[" +
                children.stream()
                .map(child -> child.toString())
                .collect(Collectors.joining()) +
                "]";
    }
}
