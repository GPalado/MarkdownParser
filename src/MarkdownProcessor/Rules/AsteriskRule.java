package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.BoldNode;
import MarkdownProcessor.Nodes.EmphasisNode;
import MarkdownProcessor.Nodes.StringNode;
import MarkdownProcessor.Nodes.TextNode;

import java.util.*;

/**
 * Implementation of the Asterisk Rule, in which one or more '*' followed by a non-space character
 * can denote bold or italics effects.
 */
public class AsteriskRule implements EffectRule {
    private static final HashMap<Integer, Class> ASTERISK_COUNT_TO_RULES = new HashMap<>();
    private List<AsteriskGroup> asteriskGroups = new ArrayList<>();

    private enum Side {
        //TODO: confirm usage
        LEFT,
        RIGHT,
        MIDDLE,
    }

    static {
        //TODO: confirm usage
        ASTERISK_COUNT_TO_RULES.put(1, EmphasisNode.class);
        ASTERISK_COUNT_TO_RULES.put(2, BoldNode.class);
    }

    public AsteriskRule() {
    }

    @Override
    public boolean meetsCondition(Scanner s) {
        s.useDelimiter("\n");
        return s.hasNext("[\\\\*]+[a-zA-Z0-9//\\\\`~!@#$%^&*()-=_+\\[\\]{}|'\",?><,.:;]+");
    }

    @Override
    public List<TextNode> applyAction(Scanner s) {
        if (meetsCondition(s)) {
            System.out.println("applying and meets");
            String line = s.nextLine();
            findGroupings(line);
            if (asteriskGroups.size() == 1) { //cannot pair a single group of asterisks. It belongs to a string
                return Arrays.asList(new StringNode(line));
            }
            List<TextNode> children = ParserHelper.applyEffectRules(
                    new Scanner(
                            line.substring(
                                    asteriskGroups.get(0).startingIdx + asteriskGroups.get(0).count,
                                    asteriskGroups.get(1).startingIdx
                            )
                    )
            );
            int idx1 = 0;
            int idx2 = 1;
            do {
                List<TextNode> tempKids = pairInternalAsterisks(idx1, idx2, line, children);
                children = tempKids;
                idx1 = findLeftmostRemainders();
                idx2 = findRightmostRemainders();
            } while (idx1 != -1 && idx2 != -1);
            if (idx1 == -1 && idx2 != -1) { //Left has been fully paired, leftovers on right.
                int idx;
                int rightmost = findRightmostRemainders();
                if (asteriskGroups.get(rightmost).isFull()) {
                    idx = rightmost - 1;
                } else {
                    idx = rightmost;
                }
                children.addAll(getChildrenToRightOf(idx, line));
            } else if (idx1 != -1 && idx2 == -1) { //Right has been fully paired, leftovers on left
                int idx;
                int leftmost = findLeftmostRemainders();
                if (asteriskGroups.get(leftmost).isFull()) {
                    idx = leftmost + 1;
                } else {
                    idx = leftmost;
                }
                //TODO check idx
                children.addAll(0, getChildrenToLeftOf(idx, line));
            }
            System.out.println("****done****");
            return children;
        }
        return null;
    }

    private int findRightmostRemainders() {
        int rightmost = -1;
        for (int i = asteriskGroups.size() - 1; i >= 0; i--) {
            if (asteriskGroups.get(i).hasRemainder()) {
                rightmost = i;
            } else {
                break;
            }
        }
        return rightmost;
    }

    private int findLeftmostRemainders() {
        int leftmost = -1;
        for (int i = 0; i < asteriskGroups.size(); i++) {
            if (asteriskGroups.get(i).hasRemainder()) {
                leftmost = i;
            } else {
                break;
            }
        }
        return leftmost;
    }

    /**
     * Finds the maximum number of asterisks that can be paired, given the number of existing asterisks.
     *
     * @param num
     * @return maximum number of asterisks that can be paired, with regards to the given number of existing
     * asterisks.
     */
    private int findMaxValidNumber(int num) {
        int max = 0;
        for (Integer i : ASTERISK_COUNT_TO_RULES.keySet()) {
            if (i <= num && i > max) {
                max = i;
            }
        }
        return max;
    }

    /**
     * Returns the TextNode corresponding to the paired asterisk groups in asteriskGroups specified by idx1 and idx2,
     * within the given line, and the parent of the given children.
     *
     * @param idx1
     * @param idx2
     * @param line
     * @param children
     * @return
     */
    private List<TextNode> pairInternalAsterisks(int idx1, int idx2, String line,
                                                 List<TextNode> children) {
        System.out.println(idx1 + " " + idx2);
        AsteriskGroup group1 = asteriskGroups.get(idx1);
        AsteriskGroup group2 = asteriskGroups.get(idx2);
        System.out.println("Remainders: " + group1.remainder + " " + group2.remainder);
        int numberOfAsterisks = findMaxValidNumber(Math.min(group1.remainder, group2.remainder));
        TextNode parent;
        if (numberOfAsterisks == 1) {
            parent = new EmphasisNode(children);
        } else {
            parent = new BoldNode(children);
        }
        List<TextNode> siblings = new ArrayList<>();
        int remainder1 = group1.remainder - numberOfAsterisks;
        asteriskGroups.get(idx1).setRemainder(remainder1, remainder1 == 0 ? Side.MIDDLE : Side.LEFT);
        System.out.println("group1 remainder = " + group1.remainder);
        if (remainder1 == 0) {
            System.out.println("getkidsleft");
            siblings.addAll(getChildrenToLeftOf(idx1, line));
        }
        siblings.add(parent);
        int remainder2 = group2.remainder - numberOfAsterisks;
        asteriskGroups.get(idx2).setRemainder(remainder2, remainder2 == 0 ? Side.MIDDLE : Side.RIGHT);
        System.out.println("group2 remainder = " + group2.remainder);
        if (remainder2 == 0) {
            System.out.println("getkidsright");
            siblings.addAll(getChildrenToRightOf(idx2, line));
        }
        return siblings;
    }

    private List<TextNode> getChildrenToRightOf(int idx, String line) {
        AsteriskGroup group = asteriskGroups.get(idx);
        int idxOfNextAsterisk;
        if (idx == asteriskGroups.size() - 1) {
            idxOfNextAsterisk = line.length();
        } else {
            AsteriskGroup followingGroup = asteriskGroups.get(idx + 1);
            idxOfNextAsterisk = followingGroup.startingIdx;
        }
        int startingIdx = group.startingIdx + group.count - group.remainder;
        System.out.println(group.startingIdx);
        System.out.println(group.count);
        System.out.println(group.remainder);
        System.out.println(startingIdx);
        return ParserHelper.applyEffectRules(new Scanner(line.substring(startingIdx, idxOfNextAsterisk)));
    }

    private List<TextNode> getChildrenToLeftOf(int idx, String line) {
        AsteriskGroup group = asteriskGroups.get(idx);
        int idxAfterPrevAsterisk;
        if (idx == 0) {
            idxAfterPrevAsterisk = 0;
        } else {
            AsteriskGroup previousGroup = asteriskGroups.get(idx - 1);
            idxAfterPrevAsterisk = previousGroup.startingIdx + previousGroup.count;
        }
        return ParserHelper.applyEffectRules(new Scanner(line.substring(idxAfterPrevAsterisk, group.startingIdx + group.remainder)));
    }

    /**
     * Returns the list of asterisk groups contained within the given string.
     *
     * @param s
     * @return
     */
    private void findGroupings(String s) {
        System.out.println("Finding groups");
        asteriskGroups = new ArrayList<>();
        int idx = 0;
        while (idx < s.length()) {
            if (s.charAt(idx) == '*') {
                int count = countAsterisks(s, idx);
                asteriskGroups.add(new AsteriskGroup(idx, count));
                idx += count;
            } else {
                idx++;
            }
        }
        System.out.println("Done finding groups");
    }

    /**
     * Returns the number of asterisks in a row at the start of the given string.
     *
     * @param s
     * @param startingIdx
     * @return
     */
    private int countAsterisks(String s, int startingIdx) {
        System.out.println("Counting");
        int count = 0;
        for (int i = startingIdx; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                count++;
            } else {
                break;
            }
        }
        System.out.println("Done counting");
        return count;
    }

    /**
     * Represents an asterisk group i.e. a number of asterisks one after the other, contained in a string.
     */
    private class AsteriskGroup {
        public final int startingIdx;
        public final int count;
        private int remainder;
        private Side side;

        /**
         * StartingIdx of where the group starts in the string, and count of how many asterisks there are in a row.
         *
         * @param startingIdx
         * @param count
         */
        public AsteriskGroup(int startingIdx, int count) {
            this.startingIdx = startingIdx;
            this.count = count;
            remainder = count;
        }

        /**
         * Sets the remainder to the given int on the given side i.e. to the right or left of the group.
         * The remainder is the number of asterisks that have not been paired.
         *
         * @param remainder
         */
        public void setRemainder(int remainder, Side side) {
            this.remainder = remainder;
            this.side = side;
        }

        /**
         * Returns the number of unpaired asterisks in the group.
         *
         * @return
         */
        public int getRemainder() {
            return remainder;
        }

        /**
         * @return Optional of the side on which the remainders exist, or Optional.empty() if there are none.
         */
        public Optional<Side> getSide() {
            return Optional.ofNullable(side);
        }

        /**
         * @return true if the group has remaining asterisks to be paired, false otherwise.
         */
        public boolean hasRemainder() {
            return remainder > 0;
        }

        /**
         * @return true if no asterisks have been paired yet within this group.
         */
        public boolean isFull() {
            return remainder == count;
        }
    }
}
