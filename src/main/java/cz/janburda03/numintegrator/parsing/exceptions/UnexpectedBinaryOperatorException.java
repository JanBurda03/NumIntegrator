package cz.janburda03.numintegrator.parsing.exceptions;

import cz.janburda03.numintegrator.parsing.tokenizing.Operation; /**
 * Thrown when the parser finds a binary operator where a unary operator was expected.
 */
public class UnexpectedBinaryOperatorException extends RuntimeException {
    public UnexpectedBinaryOperatorException(Operation operation) {
        super("Unexpected binary operator in primary context: " + operation);
    }
}

