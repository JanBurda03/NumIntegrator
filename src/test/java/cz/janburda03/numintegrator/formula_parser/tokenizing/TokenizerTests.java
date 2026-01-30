package cz.janburda03.numintegrator.formula_parser.tokenizing;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class TokenizerTests {

    @Test
    void testSingleNumber() throws Exception {
        Tokenizer tokenizer = new Tokenizer("42");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(1, tokens.size());
        assertTrue(tokens.get(0) instanceof NumberToken);
        assertEquals(42.0, ((NumberToken) tokens.getLast()).getValue(), 0.01);
    }

    @Test
    void testSingleVariable() throws Exception {
        Tokenizer tokenizer = new Tokenizer("x");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(1, tokens.size());
        assertTrue(tokens.get(0) instanceof VariableToken);
        assertEquals("x", ((VariableToken) tokens.get(0)).getVariable());
    }

    @Test
    void testAddition() throws Exception {
        Tokenizer tokenizer = new Tokenizer("3+2");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(3, tokens.size());
        assertTrue(tokens.get(1) instanceof OperationToken);
        assertTrue(tokens.get(0) instanceof NumberToken);
        assertTrue(tokens.get(2) instanceof NumberToken);

        assertEquals(Operation.ADD, ((OperationToken) tokens.get(1)).getOperation());
    }

    @Test
    void testUnaryMinus() throws Exception {
        Tokenizer tokenizer = new Tokenizer("-5");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(2, tokens.size());
        assertTrue(tokens.get(0) instanceof OperationToken);
        assertTrue(tokens.get(1) instanceof NumberToken);
        assertEquals(Operation.NEGATE, ((OperationToken) tokens.get(0)).getOperation());
    }

    @Test
    void testBinaryMinus() throws Exception {
        Tokenizer tokenizer = new Tokenizer("5-3");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(3, tokens.size());
        assertTrue(tokens.get(1) instanceof OperationToken);
        assertTrue(tokens.get(0) instanceof NumberToken);
        assertTrue(tokens.get(2) instanceof NumberToken);

        assertEquals(Operation.SUBTRACT, ((OperationToken) tokens.get(1)).getOperation());
    }

    @Test
    void testUnaryMinusAndAddition() throws Exception {
        Tokenizer tokenizer = new Tokenizer("-3+2");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(4, tokens.size());

        assertTrue(tokens.get(0) instanceof OperationToken);
        assertTrue(tokens.get(2) instanceof OperationToken);
        assertTrue(tokens.get(1) instanceof NumberToken);
        assertTrue(tokens.get(3) instanceof NumberToken);

        assertEquals(Operation.NEGATE, ((OperationToken) tokens.get(0)).getOperation());
        assertEquals(Operation.ADD, ((OperationToken) tokens.get(2)).getOperation());
    }

    @Test
    void testParenthesesAndNegate() throws Exception {
        Tokenizer tokenizer = new Tokenizer("-1*-(x+2)");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(9, tokens.size());

        assertEquals(Operation.NEGATE, ((OperationToken) tokens.get(0)).getOperation());
        assertEquals(Operation.NEGATE, ((OperationToken) tokens.get(3)).getOperation());
        assertEquals(Operation.MULTIPLY, ((OperationToken) tokens.get(2)).getOperation());

        assertTrue(tokens.get(4) instanceof LeftParenthesisToken);
        assertTrue(tokens.get(8) instanceof RightParenthesisToken);
    }

    @Test
    void testFunctionSin() throws Exception {
        Tokenizer tokenizer = new Tokenizer("sin(x)");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(4, tokens.size());
        assertTrue(tokens.get(0) instanceof OperationToken);
        assertEquals(Operation.SIN, ((OperationToken) tokens.get(0)).getOperation());
    }

    @Test
    void testFunctionNaturalLog() throws Exception {
        Tokenizer tokenizer = new Tokenizer("ln(10)");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(4, tokens.size());
        assertTrue(tokens.get(0) instanceof OperationToken);
        assertEquals(Operation.LN, ((OperationToken) tokens.get(0)).getOperation());
        assertTrue(tokens.get(1) instanceof LeftParenthesisToken);
        assertTrue(tokens.get(3) instanceof RightParenthesisToken);
    }

    @Test
    void testFunctionLog10() throws Exception {
        Tokenizer tokenizer = new Tokenizer("log10(-42)");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(6, tokens.size());

        assertTrue(tokens.get(0) instanceof OperationToken);
        assertEquals(Operation.LOG, ((OperationToken) tokens.get(0)).getOperation());

        assertTrue(tokens.get(1) instanceof NumberToken);
        assertEquals(10.0, ((NumberToken) tokens.get(1)).getValue(), 0.01);

        assertTrue(tokens.get(2) instanceof LeftParenthesisToken);
        assertTrue(tokens.get(5) instanceof RightParenthesisToken);

        assertEquals(Operation.NEGATE, ((OperationToken) tokens.get(3)).getOperation());


    }

    @Test
    void testPowerOperation() throws Exception {
        Tokenizer tokenizer = new Tokenizer("2^-3");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(4, tokens.size());
        assertEquals(Operation.POWER, ((OperationToken) tokens.get(1)).getOperation());
        assertEquals(Operation.NEGATE, ((OperationToken) tokens.get(2)).getOperation());
    }

    @Test
    void testComplexExpression() throws Exception {
        Tokenizer tokenizer = new Tokenizer("-3 - 2*sin(x)");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(9, tokens.size());

        assertEquals(Operation.NEGATE, ((OperationToken) tokens.get(0)).getOperation());
        assertEquals(Operation.SUBTRACT, ((OperationToken) tokens.get(2)).getOperation());

        assertEquals(Operation.MULTIPLY, ((OperationToken) tokens.get(4)).getOperation());
        assertEquals(Operation.SIN, ((OperationToken) tokens.get(5)).getOperation());
    }
}
