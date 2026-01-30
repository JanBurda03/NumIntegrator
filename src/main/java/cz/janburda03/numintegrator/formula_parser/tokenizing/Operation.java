package cz.janburda03.numintegrator.formula_parser.tokenizing;

public enum Operation {

    ADD(2, 1),        // a + b
    SUBTRACT(2, 1),   // a - b
    MULTIPLY(2, 2),   // a * b
    DIVIDE(2, 2),     // a / b
    POWER(2, 3),      // a ^ b

    NEGATE(1, 4),     // -x

    LOG(2, 5),        // log_a(b)
    LN(1, 5),         // ln(x)

    SIN(1, 5),
    COS(1, 5),
    TAN(1, 5),
    COTAN(1, 5),

    ABS(1, 5),
    SQRT(1, 5),
    EXP(1, 5);        // e^x

    private final int arity;
    private final int precedence;

    Operation(int arity, int precedence) {
        this.arity = arity;
        this.precedence = precedence;
    }

    public int getArity() {
        return arity;
    }

    public int getPrecedence() {
        return precedence;
    }
}
