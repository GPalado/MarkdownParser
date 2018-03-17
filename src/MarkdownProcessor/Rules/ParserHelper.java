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
    public static List<TextNode> applyEffectRules(Scanner s){
        System.out.println("Parser applyEffectRules");
        List<TextNode> nodes = new ArrayList<>();
        while(s.hasNextLine()) {
            nodes.add(processLine(new Scanner(s.nextLine())));
        }
        return nodes;
    }

    private static TextNode processLine(Scanner s){
        for (EffectRule r : EFFECT_RULES) {
            System.out.println("Checking " + r.getClass());
            if (r.meetsCondition(s)) {
                System.out.println("Parser meets " + r.getClass());
                return r.applyAction(s);
            }
        }

        System.out.println("Parser no match");
        //TODO: if no rules can be applied, read as string? What will be the delimiter?
        return new StringNode(s.nextLine());
    }
}
