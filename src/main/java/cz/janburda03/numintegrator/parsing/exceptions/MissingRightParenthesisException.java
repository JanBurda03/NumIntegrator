package cz.janburda03.numintegrator.parsing.exceptions;

/**
 * Thrown when a left parenthesis is not properly closed with a right parenthesis.
 */
public class MissingRightParenthesisException extends RuntimeException {
    public MissingRightParenthesisException() {
        super("Expected closing right parenthesis ')'");
    }
}
