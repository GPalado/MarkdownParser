package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.TextNode;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Stateless helper for parsing markdown. Contains a list of the rules that can be applied when parsing.
 */
public class ParserHelper {

    public static final List<ParsingRule> RULES = Arrays.asList(new HashRule());

    /**
     * Attempts to apply the rules to the given string and returns
     * TODO confirm return value - does idx reached in string or remaining string need to be returned? Asterisks case?
     * @param s
     * @returns Optional of Node corresponding to Rule if one matches, Optional.empty() otherwise.
     */
    public static Optional<TextNode> applyRules(String s){
        for(ParsingRule r : RULES) {
            if(r.meetsCondition(s)) {
                return Optional.of(r.action(s));
            }
        }
        return Optional.empty();
    }
}
