/**
 * file: Token.java
 * author: Tyler Rimaldi
 * course: CMPT 440
 * assignment: Project
 * due date: May/15/2020
 * version: 1.0
 * 
 * This file contains the Enum detailing the grammar
 * via Tokens.
 */

package token;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Token {

    TYPE_INT ("int"),
    TYPE_STRING ("string"),
    TYPE_BOOLEAN ("boolean"),
    TYPE_CHAR ("char"),

    OP_PLUS ("\\+"), 
    OP_EQ ("=="),
    OP_NOT_EQ ("!="),
    OP_ASSIGN ("="),

    KEY_IF ("if"), 
    KEY_PRINT ("print"),
    KEY_WHILE ("while"),

    OPEN_PAREN ("\\("),
    CLOSE_PAREN ("\\)"), 
    OPEN_BRACKET ("\\{"),
    CLOSE_BRACKET ("\\}"),
    EOF_MARKER ("\\$"),

    STRING ("\"[a-z]+\""),
    TRUE ("true"),
    FALSE ("false"),
    INTEGER ("\\d+"),
    IDENTIFIER ("[a-z]");

    private final Pattern pattern;

    /**
     * Token
     * 
     * 
     */
    private Token(String regex) {
        pattern = Pattern.compile("^" + regex);
    }

    /**
     * lengthOfMatch
     * 
     * 
     * @param programToken
     * @return
     */
    public int lengthOfMatch(String programToken) {
        Matcher match = pattern.matcher(programToken);
        int length = -1;
        if (match.find()) {
            length = match.end();
        }
        return length;
    }
}
