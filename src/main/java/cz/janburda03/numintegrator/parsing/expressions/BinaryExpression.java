package cz.janburda03.numintegrator.parsing.expressions;

import cz.janburda03.numintegrator.parsing.tokenizing.Operation;

import java.util.Map;

/**
 * Represents a binary expression in the syntax tree.
 * Holds a binary operation and its two operand expressions.
 */
public class BinaryExpression extends Expression
{
    protected final Operation operation;
    protected final Expression leftExpression;
    protected final Expression rightExpression;

    /**
     * Creates a new BinaryExpression with the specified operation and operands.
     *
     * @param operation the binary operation (must have arity = 2)
     * @param leftExpression the left operand expression
     * @param rightExpression the right operand expression
     * @throws IllegalArgumentException if the operation is not binary
     */
    public  BinaryExpression(Operation operation, Expression leftExpression, Expression rightExpression)
    {
        if (operation.getArity() != 2) {
            throw new IllegalArgumentException(
                    "BinaryExpression requires binary operation, got: " + operation
            );
        }

        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.operation = operation;
    }

    /**
     * Evaluates the binary expression given a map of variable values.
     *
     * @param variables a map containing variable names and their values
     * @return the result of applying the operation to the evaluated operands
     * @throws IllegalStateException if the operation is not implemented
     */
    @Override
    public double evaluate(Map<String, Double> variables) {
        double l = leftExpression.evaluate(variables);
        double r = rightExpression.evaluate(variables);

        return switch (operation) {
            case ADD -> l + r;
            case SUBTRACT -> l - r;
            case MULTIPLY -> l * r;
            case DIVIDE -> l / r;
            case POWER -> Math.pow(l, r);
            default -> throw new IllegalStateException(
                    "Operation " + operation + " is not implemented"
            );
        };
    }
}
