package cz.janburda03.numintegrator.parsing.tokenizing;

/**
 * Represents an operation token in the tokenizer.
 * Wraps a mathematical operation from the {@link Operation} enum.
 */
public class OperationToken extends Token {

    private final Operation operation;

    /**
     * Creates a new OperationToken for the given operation.
     *
     * @param operation the operation represented by this token
     */
    public OperationToken(Operation operation) {
        this.operation = operation;
    }

    /**
     * Returns the operation of this token.
     *
     * @return the operation
     */
    public Operation getOperation() {
        return operation;
    }
}