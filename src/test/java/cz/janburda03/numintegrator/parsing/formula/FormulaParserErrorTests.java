package cz.janburda03.numintegrator.parsing.formula;

import cz.janburda03.numintegrator.parsing.formula.exceptions.UnknownVariableException;
import cz.janburda03.numintegrator.parsing.formula.expressions.Expression;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FormulaParserErrorTests {

    @Test
    void divisionByZeroThrowsException()
    {
        Expression expr = FormulaParser.parse("1 / 0");

        assertThrows(IllegalArgumentException.class, () ->
                expr.evaluate(Map.of())
        );
    }

    @Test
    void lnOfZeroThrowsException()
    {
        Expression expr = FormulaParser.parse("ln(0)");

        assertThrows(IllegalArgumentException.class, () ->
                expr.evaluate(Map.of())
        );
    }

    @Test
    void lnOfNegativeThrowsException()
    {
        Expression expr = FormulaParser.parse("ln(-1)");

        assertThrows(IllegalArgumentException.class, () ->
                expr.evaluate(Map.of())
        );
    }

    @Test
    void sqrtOfNegativeThrowsException()
    {
        Expression expr = FormulaParser.parse("sqrt(-4)");

        assertThrows(IllegalArgumentException.class, () ->
                expr.evaluate(Map.of())
        );
    }

    @Test
    void cotOfZeroThrowsException()
    {
        Expression expr = FormulaParser.parse("cot(0)");

        assertThrows(IllegalArgumentException.class, () ->
                expr.evaluate(Map.of())
        );
    }

    @Test
    void unknownVariableThrowsException()
    {
        Expression expr = FormulaParser.parse("x + 1");

        assertThrows(UnknownVariableException.class, () ->
                expr.evaluate(Map.of())
        );
    }

    @Test
    void nestedInvalidExpressionThrowsException()
    {
        Expression expr = FormulaParser.parse("1 + sqrt(-9)");

        assertThrows(IllegalArgumentException.class, () ->
                expr.evaluate(Map.of())
        );
    }
}
