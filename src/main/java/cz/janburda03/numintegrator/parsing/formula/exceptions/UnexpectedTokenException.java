package cz.janburda03.numintegrator.parsing.formula.exceptions;

import cz.janburda03.numintegrator.parsing.formula.tokenizing.Token;

/**
 * Thrown when the parser encounters an unexpected token.
 */
public class UnexpectedTokenException extends RuntimeException {
    public UnexpectedTokenException(Token token) {
        super("Unexpected token: " + token);
    }
}

