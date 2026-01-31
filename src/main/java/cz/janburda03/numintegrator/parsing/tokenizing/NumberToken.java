package cz.janburda03.numintegrator.parsing.tokenizing;

/**
 * Represents a numeric literal token.
 * Holds a double value.
 */
public class NumberToken extends Token
{
    private final double value;

    /**
     * Creates a new NumberToken with the given value.
     *
     * @param value the numeric value of this token
     */
    public NumberToken(double value)
    {
        this.value = value;
    }

    /**
     * Returns the numeric value of this token.
     *
     * @return the value as a double
     */
    public double getValue()
    {
        return value;
    }
}

