package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.MarkdownFileNode;
import MarkdownProcessor.Nodes.StringNode;
import MarkdownProcessor.Nodes.TextNode;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Stateless helper for parsing markdown. Contains a list of the rules that can be applied when parsing.
 */
public class ParserHelper {

    public static final List<EffectRule> EFFECT_RULES = Arrays.asList(new HashRule(), new AsteriskRule());

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
    public static TextNode applyEffectRules(Scanner s){
        System.out.println("Parser applyEffectRules");
        for(EffectRule r : EFFECT_RULES) {
            if(r.meetsCondition(s)) {
                System.out.println("Parser meets "+r.getClass());
                System.out.println(r.getClass());
                return r.applyAction(s);
            }
        }
        System.out.println("Parser no match");
        //TODO: if no rules can be applied, read as string? What will be the delimiter?
        return new StringNode(s.nextLine());
    }
}
