package cz.janburda03.numintegrator.parsing.formula;

import cz.janburda03.numintegrator.parsing.formula.expressions.Expression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Map;


public class FormulaParserTest {
    private static final double EPSILON = 1e-10;

    @Test
    void testSimpleNumber() {
        Expression expr = FormulaParser.parse("0.42");
        Assertions.assertEquals(0.42, expr.evaluate(Map.of()), EPSILON);
    }

    @Test
    void testVariable()  {
        Expression expr = FormulaParser.parse("x");
        Assertions.assertEquals(5.0, expr.evaluate(Map.of("x", 5.0)), EPSILON);
    }

    @Test
    void testVariableCircle() {
        Expression expr = FormulaParser.parse("(1-x^2)^0.5");
        Assertions.assertEquals(0, expr.evaluate(Map.of("x", 1.0)), EPSILON);
    }

    @Test
    void testUnaryMinus() {
        Expression expr = FormulaParser.parse("-7");
        Assertions.assertEquals(-7.0, expr.evaluate(Map.of()), EPSILON);
    }

    @Test
    void testPiAndE() {
        Expression expr = FormulaParser.parse("pi + e");
        Assertions.assertEquals(Math.PI + Math.E, expr.evaluate(Map.of()), EPSILON);
    }

    @Test
    void testBinaryOperations() {
        Expression expr = FormulaParser.parse("3 + 4 * 2");
        Assertions.assertEquals(11.0, expr.evaluate(Map.of()), EPSILON);
    }

    @Test
    void testParentheses() {
        Expression expr = FormulaParser.parse("(3 + 4) * 2");
        Assertions.assertEquals(14.0, expr.evaluate(Map.of()), EPSILON);
    }

    @Test
    void testUnaryFunction() {
        Expression expr = FormulaParser.parse("sin(pi / 2)");
        Assertions.assertEquals(1.0, expr.evaluate(Map.of()), EPSILON);
    }

    @Test
    void testNestedFunctions() {
        Expression expr = FormulaParser.parse("sqrt(16) + abs(-3)");
        Assertions.assertEquals(7.0, expr.evaluate(Map.of()), EPSILON);
    }

    @Test
    void testLogarithm() {
        Expression expr = FormulaParser.parse("ln(e^-2)"); // log base 2 of 8 = 3
        Assertions.assertEquals(-2, expr.evaluate(Map.of()), EPSILON);
    }


    @Test
    void testPowerAndNegate() {
        Expression expr = FormulaParser.parse("-2^3"); // unary minus applies first?
        Assertions.assertEquals(-8.0, expr.evaluate(Map.of()), EPSILON);
    }


}
