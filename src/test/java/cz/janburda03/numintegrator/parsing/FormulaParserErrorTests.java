package cz.janburda03.numintegrator.parsing;

import cz.janburda03.numintegrator.parsing.exceptions.UnknownVariableException;
import cz.janburda03.numintegrator.parsing.expressions.Expression;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FormulaParserErrorTests {

    @Test
    void divisionByZeroThrowsException() throws Exception
    {
        Expression expr = FormulaParser.parse("1 / 0");

        assertThrows(IllegalArgumentException.class, () ->
                expr.evaluate(Map.of())
        );
    }

    @Test
    void lnOfZeroThrowsException() throws Exception
    {
        Expression expr = FormulaParser.parse("ln(0)");

        assertThrows(IllegalArgumentException.class, () ->
                expr.evaluate(Map.of())
        );
    }

    @Test
    void lnOfNegativeThrowsException() throws Exception
    {
        Expression expr = FormulaParser.parse("ln(-1)");

        assertThrows(IllegalArgumentException.class, () ->
                expr.evaluate(Map.of())
        );
    }

    @Test
    void sqrtOfNegativeThrowsException() throws Exception
    {
        Expression expr = FormulaParser.parse("sqrt(-4)");

        assertThrows(IllegalArgumentException.class, () ->
                expr.evaluate(Map.of())
        );
    }

    @Test
    void cotOfZeroThrowsException() throws Exception
    {
        Expression expr = FormulaParser.parse("cot(0)");

        assertThrows(IllegalArgumentException.class, () ->
                expr.evaluate(Map.of())
        );
    }

    @Test
    void unknownVariableThrowsException() throws Exception
    {
        Expression expr = FormulaParser.parse("x + 1");

        assertThrows(UnknownVariableException.class, () ->
                expr.evaluate(Map.of())
        );
    }

    @Test
    void nestedInvalidExpressionThrowsException() throws Exception
    {
        Expression expr = FormulaParser.parse("1 + sqrt(-9)");

        assertThrows(IllegalArgumentException.class, () ->
                expr.evaluate(Map.of())
        );
    }
}
