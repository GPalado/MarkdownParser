package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.BoldNode;
import MarkdownProcessor.Nodes.EmphasisNode;
import MarkdownProcessor.Nodes.TextNode;

import java.util.*;

/**
 * Implementation of the Asterisk Rule, in which a line contains '*' can denote
 * bulleted list, bold or italics effects.
 */
public class AsteriskRule implements EffectRule {
    public static final HashMap<Integer, Class> ASTERISK_COUNT_TO_RULES = new HashMap<>();

    static {
        //TODO: confirm usage
        ASTERISK_COUNT_TO_RULES.put(1, EmphasisNode.class);
        ASTERISK_COUNT_TO_RULES.put(2, BoldNode.class);
    }

    public AsteriskRule() {
    }

    @Override
    public boolean meetsCondition(Scanner s) {
        s.useDelimiter("\r\n");
        return s.hasNext(".*\\*.*");
    }

    @Override
    public List<TextNode> applyAction(Scanner s) {
        if (meetsCondition(s)) {
            String line = s.nextLine().trim();
            AsteriskGroupList asteriskGroupList = new AsteriskGroupList(line);
            return asteriskGroupList.processGroups();
        }
        return null;
    }
}
