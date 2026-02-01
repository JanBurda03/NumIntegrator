package cz.janburda03.numintegrator.parsing.formula.exceptions;

/**
 * Thrown when a variable is not defined in the variable map.
 */
public class UnknownVariableException extends RuntimeException {
    public UnknownVariableException(String name) {
        super("Unknown variable: " + name);
    }
}
