package MarkdownProcessor.Rules;

import java.util.Scanner;

public interface Rule {
    /**
     * Returns true if the given string conforms to the rule.
     * @param s
     * @return true if it fits the rule's conditions, false otherwise.
     */
    boolean meetsCondition(Scanner s);
}
