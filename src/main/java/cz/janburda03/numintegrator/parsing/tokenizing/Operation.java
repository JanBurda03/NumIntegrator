package cz.janburda03.numintegrator.parsing.tokenizing;

/**
 * Represents a mathematical operation used in expressions.
 * Each operation has an arity (number of operands) and a precedence level.
 */
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
    COT(1, 5),

    ABS(1, 5),
    SQRT(1, 5),
    EXP(1, 5);        // e^x

    private final int arity;
    private final int precedence;

    /**
     * Constructs an operation with given arity and precedence.
     *
     * @param arity number of operands (1 for unary, 2 for binary)
     * @param precedence operator precedence (higher means binds tighter)
     */
    Operation(int arity, int precedence) {
        this.arity = arity;
        this.precedence = precedence;
    }

    /**
     * Returns the number of operands this operation takes.
     *
     * @return arity of the operation
     */
    public int getArity() {
        return arity;
    }

    /**
     * Returns the precedence level of this operation.
     *
     * @return operator precedence
     */
    public int getPrecedence() {
        return precedence;
    }
}
