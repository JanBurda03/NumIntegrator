package cz.janburda03.numintegrator.formula_parser.tokenizing;

public class NumberToken extends Token
{
    private final double value;

    public NumberToken(double value)
    {
        this.value = value;
    }

    public double getValue()
    {
        return value;
    }
}

