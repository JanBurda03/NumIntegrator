package cz.janburda03.numintegrator.parsing.tokenizing;

/**
 * Represents a variable token in the tokenizer.
 * Holds the name of the variable.
 */
public class VariableToken extends Token
{
    private final String variable;

    /**
     * Creates a new VariableToken with the given variable name.
     *
     * @param variable the name of the variable
     */
    public VariableToken(String variable) {
        this.variable = variable;
    }

    /**
     * Returns the name of the variable.
     *
     * @return the variable name
     */
    public String getVariable()
    {
        return variable;
    }
}

