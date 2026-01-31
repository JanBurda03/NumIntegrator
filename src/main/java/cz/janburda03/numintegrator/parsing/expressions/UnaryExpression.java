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
     * Evaluates the unary expression using the provided variable values.
     *
     * @param variables a map containing variable names and their values
     * @return the result of applying the unary operation to the evaluated operand
     *
     * @throws IllegalArgumentException if the operand value is outside the valid
     *         mathematical domain of the operation (e.g. ln(x) for x ≤ 0,
     *         sqrt(x) for x < 0, or cot(x) for x = 0)
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

            case COT -> {
                if (Math.tan(value) == 0.0) {
                    throw new IllegalArgumentException("cot(x) is undefined for x = " + value);
                }
                yield 1.0 / Math.tan(value);
            }

            case LN -> {
                if (value <= 0.0) {
                    throw new IllegalArgumentException("ln(x) is only defined for x > 0, got " + value);
                }
                yield Math.log(value);
            }

            case LOG -> {
                if (value <= 0.0) {
                    throw new IllegalArgumentException("log10(x) is only defined for x > 0, got " + value);
                }
                yield Math.log10(value);
            }

            case SQRT -> {
                if (value < 0.0) {
                    throw new IllegalArgumentException("sqrt(x) is only defined for x >= 0, got " + value);
                }
                yield Math.sqrt(value);
            }

            case ABS -> Math.abs(value);
            case EXP -> Math.exp(value);

            default -> throw new IllegalStateException(
                    "Operation " + operation + " is not implemented"
            );
        };
    }
}

