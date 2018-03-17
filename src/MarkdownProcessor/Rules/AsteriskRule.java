package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.StringNode;
import MarkdownProcessor.Nodes.TextNode;

import java.util.*;

/**
 * Implementation of the Asterisk Rule, in which one or more '*' followed by a non-space character
 * can denote bold or italics effects.
 */
public class AsteriskRule implements EffectRule {
    private final List<Integer> validAsteriskNums = new ArrayList<>(Arrays.asList(1, 2));

    private enum Side {
        LEFT,
        RIGHT,
        MIDDLE,
    }

    public AsteriskRule() {
    }

    @Override
    public boolean meetsCondition(Scanner s) {
        s.useDelimiter("\n");
        return s.hasNext("[\\\\*]+[a-zA-Z0-9]+");
    }

    @Override
    public TextNode applyAction(Scanner s) {
        List<TextNode> children = new ArrayList<>();
        if (meetsCondition(s)) {
            String line = s.nextLine();
            List<AsteriskGroup> asteriskGroups = findGroupings(line);
            if(asteriskGroups.size() == 1){ //cannot pair a single group of asterisks. It belongs to a string
                return new StringNode(line);
            }
            for (int i = 0; i < asteriskGroups.size() - 1; i++) {
                AsteriskGroup group1 = asteriskGroups.get(i);
                AsteriskGroup group2 = asteriskGroups.get(i + 1);
                if (group1.remainder != group2.remainder) { //pair group1 rightmost asterisks with leftmost group2 asterisks
                    int min = Math.min(group1.remainder, group2.remainder);
                    int numToMatch = findMaxValidNumber(min);
                    TextNode node = pairInternal(group1, group2, numToMatch, line);
                    children.add(node);
                    int remainder1 = group1.count - numToMatch;
                    group1.setRemainder(remainder1, remainder1 == 0 ? Side.MIDDLE : Side.LEFT);
                    int remainder2 = group2.count - numToMatch;
                    group2.setRemainder(remainder2, remainder2 == 0 ? Side.MIDDLE : Side.RIGHT);
                } else {

                }
            }
        }
        return null;
    }

    /**
     * Finds the maximum number of asterisks that can be paired, given the number of existing asterisks.
     * @param num
     * @return maximum number of asterisks that can be paired, with regards to the given number of existing
     * asterisks.
     */
    private int findMaxValidNumber(int num) {
        int max = 0;
        for(Integer i : validAsteriskNums){
            if(i <= num && i > max){
                max = i;
            }
        }
        return max;
    }

    /**
     * Pairs the given numberOfAsterisks in groups1 and 2 within the given line.
     * @param group1
     * @param group2
     * @param numberOfAsterisks
     * @param line
     * @return the TextNode representing the substring within the given line between group1 and group1, pairing the
     * given number of asterisks.
     */
    private TextNode pairInternal(AsteriskGroup group1, AsteriskGroup group2, int numberOfAsterisks, String line) {
        return applyAction(
                new Scanner(
                        line.substring(
                                group1.startingIdx + group1.count - numberOfAsterisks,
                                group2.startingIdx + numberOfAsterisks)
                )
        );

    }

    /**
     * Returns the list of asterisk groups contained within the given string.
     *
     * @param s
     * @return
     */
    private List<AsteriskGroup> findGroupings(String s) {
        List<AsteriskGroup> groupings = new ArrayList<>();
        int idx = 0;
        while (idx < s.length()) {
            if (s.charAt(idx) == '*') {
                int count = countAsterisks(s, idx);
                groupings.add(new AsteriskGroup(idx, count));
                idx += count;
            }
        }
        return groupings;
    }

    /**
     * Returns the number of asterisks in a row at the start of the given string.
     *
     * @param s
     * @param startingIdx
     * @return
     */
    private int countAsterisks(String s, int startingIdx) {
        int count = 0;
        int idx = startingIdx;
        while (idx < s.length()) {
            if (s.charAt(idx) == '*') {
                count++;
            }
        }
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
        public boolean hasRemainder(){
            return remainder != 0;
        }
    }
}
