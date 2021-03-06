package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.StringNode;
import MarkdownProcessor.Nodes.TextNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Stateless helper for parsing markdown. Contains a list of the rules that can be applied when parsing.
 */
public class ParserHelper {

    public static final List<EffectRule> EFFECT_RULES = Arrays.asList(new AsteriskRule());

    /**
     * Attempts to apply the structural rules (e.g. paragraphs and lines) to the text in the given scanner.
     * @param s
     * @returns Optional of TextNode corresponding to a structural rule if one matches, Optional.empty() otherwise.
     */
    public static TextNode applyStructureRules(Scanner s){
        return new MarkdownFileRule().applyStructure(s);
    }

    /**
     * Attempts to apply the effect rules (e.g. bold and headings) to the given input.
     * @param s
     * @returns TextNode corresponding to an effect rule if one matches.
     */
    static List<TextNode> applyEffectRules(Scanner s){
        List<TextNode> nodes = new ArrayList<>();
        while(s.hasNextLine()) {
            nodes.addAll(processLine(new Scanner(s.nextLine())));
        }
        return nodes;
    }

    private static List<TextNode> processLine(Scanner s){
        for (EffectRule r : EFFECT_RULES) {
            if (r.meetsCondition(s)) {
                return r.applyAction(s);
            }
        }
        return Arrays.asList(new StringNode(s.nextLine()));
    }
}
