package Nodes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Line Node specifying a line of text. Can consist of several {@link ParserNode}s.
 */
public class LineNode implements TextNode {
    protected List<ParserNode> contentNodes;

    public LineNode(String line) {
        int idx = 0;
        while (idx < line.length()) {
            // TODO: check what's next and create appropriate "children"
            // cases: *, **, *** etc., #, ---, [0-9]+." ", empty line, etc.
            // maybe figure out a sort of visitor?
            // check next character(s) and call corresponding Runnable to get the constituent ParserNodes belonging to this line.
            idx++;
        }
    }

    @Override
    public String toHTML() {
        return Stream.of(contentNodes)
                .map(node -> toHTML())
                .collect(Collectors.joining());
    }

    @Override
    public String toString() {
        return Stream.of(contentNodes)
                .map(node -> toString())
                .collect(Collectors.joining());
    }
}
