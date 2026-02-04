package cz.janburda03.numintegrator.parsing.formula;

import cz.janburda03.numintegrator.parsing.formula.exceptions.*;
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


    @Test
    void missingRightParenthesisThrowsException() {
        assertThrows(
                MissingRightParenthesisException.class,
                () -> FormulaParser.parse("(x")
        );
    }

    @Test
    void extraRightParenthesisThrowsException() {
        assertThrows(
                UnexpectedTokenException.class,
                () -> FormulaParser.parse("x)")
        );
    }

    @Test
    void nestedMissingParenthesisThrowsException() {
        assertThrows(
                MissingRightParenthesisException.class,
                () -> FormulaParser.parse("(x+(y*2)")
        );
    }

    @Test
    void emptyParenthesesThrowsException() {
        assertThrows(
                UnexpectedTokenException.class,
                () -> FormulaParser.parse("()")
        );
    }

    @Test
    void binaryOperatorAtBeginningThrowsException() {
        assertThrows(
                UnexpectedBinaryOperatorException.class,
                () -> FormulaParser.parse("+x")
        );
    }

    @Test
    void doubleBinaryOperatorThrowsException() {
        assertThrows(
                UnexpectedBinaryOperatorException.class,
                () -> FormulaParser.parse("x++y")
        );
    }

    @Test
    void missingRightOperandThrowsException() {
        assertThrows(
                UnexpectedEndOfInputException.class,
                () -> FormulaParser.parse("x+")
        );
    }

    @Test
    void operatorBeforeRightParenthesisThrowsException() {
        assertThrows(
                UnexpectedTokenException.class,
                () -> FormulaParser.parse("(x+)")
        );
    }


    @Test
    void leftoverTokensAfterExpressionThrowsException() {
        assertThrows(
                UnexpectedTokenException.class,
                () -> FormulaParser.parse("x 1")
        );
    }

    @Test
    void extraTokensAfterValidExpressionThrowsException() {
        assertThrows(
                UnexpectedTokenException.class,
                () -> FormulaParser.parse("x+1 2")
        );
    }
}
