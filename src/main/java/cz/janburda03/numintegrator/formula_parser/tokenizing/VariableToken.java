package cz.janburda03.numintegrator.formula_parser.tokenizing;

public class VariableToken extends Token
{
    private final String variable;


    public VariableToken(String variable) {
        this.variable = variable;
    }
    
    public String getVariable()
    {
        return variable;
    }
}

