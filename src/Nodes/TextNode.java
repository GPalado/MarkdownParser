package Nodes;

import Translators.Translator;

/**
 * Generic TextNode interface. TextNodes can be translated to a different language by accepting Translator. E.g. HTML.
 */
public interface TextNode {

    /**
     * Returns the equivalent of this node's content in a certain language, depending on the translator.
     * @param t Translator
     * @return String representation of this node corresponding to the given Visitor.
     */
    String accept(Translator t);


}
