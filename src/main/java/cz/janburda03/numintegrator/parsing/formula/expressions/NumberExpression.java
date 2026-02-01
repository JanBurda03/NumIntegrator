package cz.janburda03.numintegrator.parsing.formula.expressions;

import java.util.Map;

/**
 * Represents a numeric literal expression in the tree.
 * Always evaluates to the stored constant value.
 */
public class NumberExpression extends Expression{
    double number;

    /**
     * Creates a new NumberExpression with the given value.
     *
     * @param number the numeric value of this expression
     */
    public NumberExpression(double number) {
        this.number = number;
    }

    /**
     * Evaluates the numeric expression.
     * Since this is a constant, the variable map is ignored.
     *
     * @param variables a map of variable names to their values (ignored)
     * @return the stored numeric value
     */
    @Override
    public double evaluate(Map<String, Double> variables) {
        return number;
    }
}
