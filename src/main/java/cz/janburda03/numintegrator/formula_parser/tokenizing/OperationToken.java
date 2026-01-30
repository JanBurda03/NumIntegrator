package cz.janburda03.numintegrator.formula_parser.tokenizing;

public class OperationToken extends Token {

    private final Operation operation;

    public OperationToken(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }
}