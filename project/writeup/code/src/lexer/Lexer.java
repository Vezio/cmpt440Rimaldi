/**
 * file: Lexer.java
 * author: Tyler Rimaldi
 * course: CMPT 440
 * assignment: Project
 * due date: May/15/2020
 * version: 1.0
 * 
 * This file performers Lexical Analysis.
 */

package lexer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import token.*;

public class Lexer {
    private StringBuilder cleanedProgram;
    private Token token;
    private String lexema;
    private final Pattern ID_PATTERN = Pattern.compile("^([a-z]+)(=)");
    private boolean finished;

    public Lexer(String program) {
        cleanedProgram = new StringBuilder();
        program = cleanProgram(program);
        initialValidator(program);
        cleanedProgram.append(program);
    }

    /**
     * start
     * 
     * Begin lexical analysis!
     */
    public void start() {
        finished = false;
        lookAhead();
        while (!finished) {
            Matcher id = ID_PATTERN.matcher(lexema);
            if (id.find()) {
                System.out.printf("%-18s :  %s \n", id.group(1), "IDENTIFIER");
                System.out.printf("%-18s :  %s \n", id.group(2), "OP_ASSIGN");
            } else {
                System.out.printf("%-18s :  %s \n", lexema, token);
            }
            lookAhead();
        }
    }

    /**
     * lookAhead
     * 
     * Find the next character to start tokenizing, if any, else 
     * finish program.
     * Throw errors if we see an unexpected program "after" having
     * completed the tokenize
     */
    private void lookAhead() {
        if (cleanedProgram.length() == 0) {
            finished = true;
            return;
        }

        if (findNextToken()) { return; }

        finished = true;

        if (cleanedProgram.length() > 0) {
            showError("Unexpected symbol", cleanedProgram.charAt(0));
        }
    }

    /**
     * findNextToken
     * 
     * Grab next token via our Token Enum validator
     * 
     * @return boolean of status 
     */
    private boolean findNextToken() {
        for (Token t : Token.values()) {
            int length = t.lengthOfMatch(cleanedProgram.toString());
            if (length != -1) {
                token = t;
                lexema = cleanedProgram.substring(0, length);
                cleanedProgram.delete(0, length);
                return true;
            }
        }
        return false;
    }

    /**
     * removeComments
     * 
     * Strip the comments off of the program 
     */
    private String removeComments(String program) {
        String pattern = "\\/\\*[^\\*]*\\*\\/";
        return program.replaceAll(pattern, "");
    }

    /**
     * cleanSpacing
     * 
     * Remove all spacing between lines, and also 
     * prevent tab errors.
     */
    private String cleanSpacing(String program) {
        String pattern = "\\t";
        program = program.replaceAll(pattern, "    ");

        pattern = "\\s";
        program = program.replaceAll(pattern, "");

        pattern = "[\\\r\\\n]+";
        program = program.replaceAll(pattern, " ");

        return program;
    }

    /**
     * cleanProgram
     * 
     * Run all clean up methods on our program.
     * 
     * @param program Program Code to Clean.
     * @return
     */
    private String cleanProgram(String program) {
        return cleanSpacing(removeComments(program));
    }

    /**
     * initialValidator
     * 
     * Validate our program has an End of File marker
     * and open and close braces. Otherwise get out of there
     * before wasting time!
     */
    private void initialValidator(String program) {
        // Check for {}$ code block completeness
        if (!(program.charAt(0) == '{' 
            && program.charAt(program.length()-2)=='}'
            && program.charAt(program.length()-1) == '$')) {
                showError("Missing code block");
        }
    }

    /**
     * Overloaded showError
     * 
     * @param err The error to display.
     * @param context The error context to display.
     * 
     * Print verbose error message, then exit.
     */
    private void showError(String err, char context) {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Syntax Error: \r\n");
        System.err.println("Error: "+err);
        System.err.println("Context: "+context);
        System.out.println("----------------------------------------------------------------------------");
        System.exit(1);
    } 

    /**
     * Overloaded showError
     * 
     * @param err The error to display.
     * 
     * Print generic error, then exit.
     */
    private void showError(String err) {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Syntax Error: \r\n");
        System.err.println("Error: "+err);
        System.out.println("----------------------------------------------------------------------------");
        System.exit(1);
    }
}
