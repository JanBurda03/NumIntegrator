package cz.janburda03.numintegrator.parsing.formula.exceptions;

/**
 * Thrown when there is a missing operand at the end of formula.
 */
public class UnexpectedEndOfInputException extends RuntimeException {

    public UnexpectedEndOfInputException() {
        super("Unexpected end of input");
    }
}