package MarkdownProcessor.Nodes;

import MarkdownProcessor.Translators.Translator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Header node representing a line of text with the header effect. Header level is specified in markdown by the number
 * of hashes.
 */
public class HeaderNode implements CollectorNode {
    private List<TextNode> children;
    private int depth;

    public HeaderNode(String line) {
        children = new ArrayList<>();
        // TODO: count hashes to know level of header. other effects?
        // header different to line node? Or can headers also have multiple children?
    }

    public int getDepth(){
        return depth;
    }

    @Override
    public String toString() {
        return repeat("#", depth)  + " " +
                Stream.of(children)
                .map(child -> toString())
                .collect(Collectors.joining());
    }

    @Override
    public String accept(Translator t) {
        return t.translateHeader(this);
    }

    @Override
    public List<TextNode> getChildren() {
        return children;
    }

    private String repeat(String s, int amount) {
        String result = "";
        for (int i = 0; i < amount; i++){
            result += s;
        }
        return result;
    }
}
