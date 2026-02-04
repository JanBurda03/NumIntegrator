package cz.janburda03.numintegrator.parsing.formula.exceptions;

public class UnexpectedEndOfInputException extends RuntimeException {

    public UnexpectedEndOfInputException() {
        super("Unexpected end of input");
    }
}