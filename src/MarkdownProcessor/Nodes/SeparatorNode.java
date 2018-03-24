package MarkdownProcessor.Nodes;

import MarkdownProcessor.Translators.Translator;

public class SeparatorNode implements TextNode {

    @Override
    public String accept(Translator t) {
        return t.translateSeparator(this);
    }

    @Override
    public String toString(){
        return "Separator|";
    }
}
