package cz.janburda03.numintegrator.parsing.formula.expressions;

import cz.janburda03.numintegrator.parsing.formula.exceptions.UnknownVariableException;
import java.util.Map;

/**
 * Represents a variable expression in the ST.
 * Evaluates to the value of the variable provided in the variable map.
 */
public class VariableExpression extends Expression{
    protected final String variable;

    /**
     * Creates a VariableExpression for the given variable name.
     *
     * @param variable the name of the variable
     */
    public VariableExpression(String variable) {
        this.variable = variable;
    }

    /**
     * Evaluates the expression by looking up the variable's value in the provided map.
     *
     * @param variables a map containing variable names and their values
     * @return the value of the variable
     * @throws UnknownVariableException if the variable is not present in the map
     */
    @Override
    public double evaluate(Map<String, Double> variables)
    {
        Double value = variables.get(variable);
        if (value == null) {
            throw new UnknownVariableException(variable);
        }
        return value;
    }
}
