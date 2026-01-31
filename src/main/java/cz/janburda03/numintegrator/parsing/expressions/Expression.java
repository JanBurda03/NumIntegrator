package cz.janburda03.numintegrator.parsing.expressions;

import java.util.Map;

/**
 * Abstract base class for all expressions in the syntax tree.
 * Provides the interface for evaluating expressions given a set of variable values.
 */
public abstract class Expression {
    /**
     * Evaluates the expression using the provided variable values.
     *
     * @param variables a map of variable names to their values
     * @return the result of evaluating the expression
     */
    public abstract double evaluate(Map<String, Double> variables);
}
