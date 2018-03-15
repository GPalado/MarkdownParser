import Nodes.HeaderNode;
import Nodes.LineNode;
import Nodes.ParaNode;
import Nodes.StringNode;

/**
 * Stateless Visitors class, containing visitor methods for the different components of markdown.
 * E.g. Strings and notations.
 */
public class Visitors {

    /**
     * Visitor method for strings consisting of letters, numbers, punctuation and spaces.
     * @return
     */
    public static StringNode visitString(){
        // TODO: process the string, special case of certain characters *, #, - etc.
        return null;
    }

    /**
     * Visitor method for lines starting with '#', representing header lines.
     * @return HeaderNode representing the Header
     */
    public static HeaderNode visitHeader(){
        return null;
    }

    /**
     * Visitor for empty lines, indicating new paragraphs. Multiple empty lines represent a single paragraph break.
     * @return ParaNode representing the paragraph break.
     */
    public static ParaNode visitPara(){
        return null;
    }

    /**
     * Visitor for the effects denoted by an '*' such as emphasis and bold. Extra calculations are required here for
     * parsing nested effects.
     * @return
     */
    public static LineNode visitAsterisk(){
        // TODO: confirm design?
        return null;
    }
}
