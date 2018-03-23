package MarkdownProcessor.Rules;

import MarkdownProcessor.Nodes.BoldNode;
import MarkdownProcessor.Nodes.EmphasisNode;
import MarkdownProcessor.Nodes.StringNode;
import MarkdownProcessor.Nodes.TextNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AsteriskGroupList extends ArrayList<AsteriskGroupList.AsteriskGroup> {
    private final String string;

    private enum Side {
        LEFT,
        RIGHT,
        MIDDLE,
        NONE,
    }

    public AsteriskGroupList(String string) {
        super();
        this.string = string;
    }

    public List<TextNode> processGroups() {
        findAsteriskGroupings();
        if (size() <= 1) { //cannot pair a single group of asterisks. It belongs to a string
            return Arrays.asList(new StringNode(string));
        }
        List<TextNode> children = parseNodesWithin(get(0).startingIdx + get(0).count, get(1).startingIdx);
        int idx1 = 0;
        int idx2 = 1;
        do {
            List<TextNode> tempKids = parseNextLevelOfAsterisksWithin(idx1, idx2, children);
            children = tempKids;
            idx1 = findLeftmostGroupWithRemainders();
            idx2 = findRightmostGroupWithRemainders();
        } while (idx1 != -1 && idx2 != -1);
        if (idx1 == -1 && idx2 != -1) {
            children.addAll(processLeftoversOnRight());
        } else if (idx1 != -1 && idx2 == -1) {
            children.addAll(0, processLeftoversOnLeft());
        }
        return children;
    }

    private List<TextNode> processLeftoversOnLeft(){
        int endingIdx;
        int leftmost = findLeftmostGroupWithRemainders();
        if (get(leftmost).isFull()) {
            AsteriskGroup group = get(leftmost + 1);
            endingIdx = group.startingIdx;
        } else {
            AsteriskGroup group = get(leftmost);
            endingIdx = group.startingIdx + group.getRemainder();
        }
        return parseNodesWithin(0, endingIdx);
    }

    private List<TextNode> processLeftoversOnRight(){
        int startingIdx;
        int rightmost = findRightmostGroupWithRemainders();
        if (get(rightmost).isFull()) {
            AsteriskGroup group = get(rightmost - 1);
            startingIdx = group.startingIdx + group.count;
        } else {
            AsteriskGroup group = get(rightmost);
            startingIdx = group.startingIdx + group.count - group.getRemainder();
        }
        return parseNodesWithin(startingIdx, string.length());
    }

    private int findRightmostGroupWithRemainders() {
        int rightmost = -1;
        for (int i = size() - 1; i >= 0; i--) {
            AsteriskGroup group = get(i);
            if (group.hasRemaindersOnRight()) {
                rightmost = i;
            } else {
                break;
            }
        }
        return rightmost;
    }

    private int findLeftmostGroupWithRemainders() {
        int leftmost = -1;
        for (int i = 0; i < size(); i++) {
            AsteriskGroup group = get(i);
            if (group.hasRemaindersOnLeft()) {
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
     * @param num1 number of asterisks in one group
     * @param num2 number of asterisks in another group
     * @return maximum number of asterisks that can be paired, with regards to the given number of existing
     * asterisks.
     */
    private int findMaxValidNumber(int num1, int num2) {
        int minOfBoth = Math.min(num1, num2);
        int max = 0;
        for (Integer i : AsteriskRule.ASTERISK_COUNT_TO_RULES.keySet()) {
            if (i <= minOfBoth && i > max) {
                max = i;
            }
        }
        return max;
    }

    /**
     * Returns the TextNode corresponding to the paired asterisk groups in asteriskGroups specified by idx1 and idx2,
     * and the parent of the given children. Assumes the group referenced by idx1 exists
     * to the left of the group referenced by idx2.
     *
     * @param idx1
     * @param idx2
     * @param children
     * @return
     */
    private List<TextNode> parseNextLevelOfAsterisksWithin(int idx1, int idx2, List<TextNode> children) {
        assert (idx1 < idx2);
        AsteriskGroup group1 = get(idx1);
        AsteriskGroup group2 = get(idx2);
        int numberOfAsterisks = findMaxValidNumber(group1.getRemainder(), group2.getRemainder());
        TextNode parent;
        if (numberOfAsterisks == 1) {
            parent = new EmphasisNode(children);
        } else {
            parent = new BoldNode(children);
        }
        List<TextNode> siblings = new ArrayList<>();
        int remainder1 = group1.getRemainder() - numberOfAsterisks;
        get(idx1).setRemainder(remainder1, remainder1 == 0 ? Side.MIDDLE : Side.LEFT);
        if (remainder1 == 0) {
            siblings.addAll(getChildrenLeftOfGroupAt(idx1));
            get(idx1).setRemainder(0, Side.NONE);
        }
        siblings.add(parent);
        int remainder2 = group2.getRemainder() - numberOfAsterisks;
        get(idx2).setRemainder(remainder2, remainder2 == 0 ? Side.MIDDLE : Side.RIGHT);
        if (remainder2 == 0) {
            siblings.addAll(getChildrenRightOfGroupAt(idx2));
            get(idx2).setRemainder(0, Side.NONE);
        }
        return siblings;
    }

    private List<TextNode> parseNodesWithin(int startingIdx, int endingIdx){
        return ParserHelper.applyEffectRules(new Scanner(string.substring(startingIdx, endingIdx)));
    }

    private List<TextNode> getChildrenRightOfGroupAt(int idx) {
        AsteriskGroup group = get(idx);
        int idxOfNextAsterisk;
        if (idx == size() - 1) {
            idxOfNextAsterisk = string.length();
        } else {
            AsteriskGroup followingGroup = get(idx + 1);
            idxOfNextAsterisk = followingGroup.startingIdx;
        }
        int startingIdx = group.startingIdx + group.count - group.getRemainder();
        return parseNodesWithin(startingIdx, idxOfNextAsterisk);
    }

    private List<TextNode> getChildrenLeftOfGroupAt(int idx) {
        AsteriskGroup group = get(idx);
        int idxAfterPrevAsterisk;
        if (idx == 0) {
            idxAfterPrevAsterisk = 0;
        } else {
            AsteriskGroup previousGroup = get(idx - 1);
            idxAfterPrevAsterisk = previousGroup.getRightIndex();
        }
        int endingIdx = group.startingIdx + group.getRemainder();
        return parseNodesWithin(idxAfterPrevAsterisk, endingIdx);
    }

    /**
     * Returns the list of asterisk groups contained within the given string related to this list.
     */
    private void findAsteriskGroupings() {
        int count = 0;
        int idx;
        for (idx = 0; idx < string.length(); idx++) {
            if (string.charAt(idx) == '*') {
                count++;
            }
            if (count != 0) {
                int startingIdx = idx == string.length() - 1 ? idx - count + 1 : idx - count;
                Side validSide = calculateValidSide(startingIdx, count);
                if (string.charAt(idx) != '*' || idx == string.length() - 1) {
                    if (validSide.equals(Side.NONE)) {
                        count = 0;
                        continue;
                    }
                    addNewGroup(startingIdx, count, validSide);
                    count = 0;
                }
            }
        }
    }

    private boolean isValidAsRightAsteriskGroup(int startingIdx, int count) {
        if (startingIdx <= 0) return false;
        if (string.charAt(startingIdx - 1) != ' ') return true;
        return false;
    }

    private boolean isValidAsLeftAsteriskGroup(int startingIdx, int count) {
        if (startingIdx + count >= string.length() - 1) return false;
        if (string.charAt(startingIdx + count) != ' ') return true;
        return false;
    }

    private Side calculateValidSide(int startingIdx, int count) {
        boolean validAsLeft = isValidAsLeftAsteriskGroup(startingIdx, count);
        boolean validAsRight = isValidAsRightAsteriskGroup(startingIdx, count);
        if (validAsLeft && validAsRight) return Side.MIDDLE;
        else if (validAsLeft) return Side.LEFT;
        else if (validAsRight) return Side.RIGHT;
        else return Side.NONE;
    }

    private void addNewGroup(int startingIndex, int count, Side validSide) {
        add(new AsteriskGroup(startingIndex, count, validSide));
    }

    /**
     * Represents an asterisk group i.e. a number of asterisks one after the other
     * (neither preceded nor followed by a space), contained in a string.
     */
    class AsteriskGroup {
        final int startingIdx;
        final int count;
        private int remainder;
        private AsteriskGroupList.Side remainderSide;
        private AsteriskGroupList.Side validSide;

        /**
         * StartingIdx of where the group starts in the string, and count of how many asterisks there are in a row.
         *
         * @param startingIdx
         * @param count
         */
        public AsteriskGroup(int startingIdx, int count, AsteriskGroupList.Side validSide) {
            this.startingIdx = startingIdx;
            this.count = count;
            this.validSide = validSide;
            remainder = count;
            remainderSide = AsteriskGroupList.Side.MIDDLE;
        }

        /**
         * @return the starting index of the left side of this group, taking into account already paired asterisks,
         * -1 if the leftmost asterisks have been paired.
         */
        public int getLeftIndex(){
            if(remainderSide.equals(Side.LEFT) || remainderSide.equals(Side.MIDDLE)){
                return startingIdx + (count - 1);
            } else {
                return -1;
            }
        }

        /**
         * @return the starting index of the right side of this group, taking into account already paired asterisks,
         * -1 if the rightmost asterisks have been paired.
         */
        public int getRightIndex() {
            if(remainderSide.equals(Side.RIGHT) || remainderSide.equals(Side.MIDDLE)){
                return startingIdx + (count - 1);
            } else {
                return -1;
            }
        }

        /**
         * Sets the remainder to the given int on the given side i.e. to the right or left of the group.
         * The remainder is the number of asterisks that have not been paired.
         *
         * @param remainder
         */
        public void setRemainder(int remainder, AsteriskGroupList.Side side) {
            this.remainder = remainder;
            this.remainderSide = side;
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
         * @return the side on which the remainders exist, or Middle if there are none.
         */
        public AsteriskGroupList.Side getSideOfRemainders() {
            return remainderSide;
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

        /**
         * @return boolean representing whether the group is valid as the group on the left of a string.
         */
        public boolean isValidAsLeft() {
            return validSide.equals(AsteriskGroupList.Side.LEFT) || validSide.equals(AsteriskGroupList.Side.MIDDLE);
        }

        /**
         * @return boolean representing whether the group is valid as the group on the right of a string.
         */
        public boolean isValidAsRight() {
            return validSide.equals(AsteriskGroupList.Side.RIGHT) || validSide.equals(AsteriskGroupList.Side.MIDDLE);
        }

        /**
         * @return the side on which this group can be valid: left, right, or middle (both).
         */
        public Side getValidSide() {
            return validSide;
        }

        public boolean hasRemaindersOnLeft(){
             return remainderSide.equals(Side.LEFT) || remainderSide.equals(Side.MIDDLE);
        }

        public boolean hasRemaindersOnRight(){
            return remainderSide.equals(Side.RIGHT) || remainderSide.equals(Side.MIDDLE);
        }
    }
}