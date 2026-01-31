package cz.janburda03.numintegrator.parsing.expressions;

import cz.janburda03.numintegrator.parsing.tokenizing.Operation;

import java.util.Map;

/**
 * Represents a unary expression in the syntax tree.
 * Holds a unary operation and its single operand expression.
 */
public class UnaryExpression extends Expression
{
    protected final Operation operation;
    protected final Expression subExpression;

    /**
     * Creates a new UnaryExpression with the specified operation and operand.
     *
     * @param operation the unary operation
     * @param subExpression the operand expression
     * @throws IllegalArgumentException if the operation is not unary
     */
    public  UnaryExpression(Operation operation, Expression subExpression)
    {
        if (operation.getArity() != 1) {
            throw new IllegalArgumentException(
                    "UnaryExpression requires unary operation, got: " + operation
            );
        }

        this.subExpression = subExpression;
        this.operation = operation;
    }

    /**
     * Evaluates the unary expression given a map of variable values.
     *
     * @param variables a map containing variable names and their values
     * @return the result of applying the unary operation to the evaluated operand
     * @throws IllegalStateException if the operation is not implemented
     */
    @Override
    public double evaluate(Map<String, Double> variables) {
        double value = subExpression.evaluate(variables);

        return switch (operation) {
            case NEGATE -> -value;

            case SIN -> Math.sin(value);
            case COS -> Math.cos(value);
            case TAN -> Math.tan(value);
            case COT -> 1.0 / Math.tan(value);

            case LN -> Math.log(value);

            case ABS -> Math.abs(value);
            case SQRT -> Math.sqrt(value);
            case EXP -> Math.exp(value);

            default -> throw new IllegalStateException(
                    "Operation " + operation + " is not implemented"
            );
        };
    }
}

