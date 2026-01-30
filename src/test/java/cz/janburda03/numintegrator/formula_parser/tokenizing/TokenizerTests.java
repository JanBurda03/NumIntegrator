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
        assertEquals(42.0, ((NumberToken) tokens.get(0)).getValue());
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
        assertEquals(Operation.ADD, ((OperationToken) tokens.get(1)).getOperation());
    }

    @Test
    void testUnaryMinus() throws Exception {
        Tokenizer tokenizer = new Tokenizer("-5");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(1, tokens.size());
        assertTrue(tokens.get(0) instanceof OperationToken);
        assertEquals(Operation.NEGATE, ((OperationToken) tokens.get(0)).getOperation());
    }

    @Test
    void testBinaryMinus() throws Exception {
        Tokenizer tokenizer = new Tokenizer("5-3");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(3, tokens.size());
        assertEquals(Operation.SUBTRACT, ((OperationToken) tokens.get(1)).getOperation());
    }

    @Test
    void testParentheses() throws Exception {
        Tokenizer tokenizer = new Tokenizer("(x+2)");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(5, tokens.size());
        assertTrue(tokens.get(0) instanceof LeftParenthesisToken);
        assertTrue(tokens.get(4) instanceof RightParenthesisToken);
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
    void testFunctionLog() throws Exception {
        Tokenizer tokenizer = new Tokenizer("log(10)");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(3, tokens.size());
        assertTrue(tokens.get(0) instanceof OperationToken);
        assertEquals(Operation.LOG, ((OperationToken) tokens.get(0)).getOperation());
    }

    @Test
    void testPowerOperation() throws Exception {
        Tokenizer tokenizer = new Tokenizer("2^3");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(3, tokens.size());
        assertEquals(Operation.POWER, ((OperationToken) tokens.get(1)).getOperation());
    }

    @Test
    void testComplexExpression() throws Exception {
        Tokenizer tokenizer = new Tokenizer("-3 + 2*sin(x)");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(9, tokens.size());

        assertEquals(Operation.NEGATE, ((OperationToken) tokens.get(0)).getOperation());
        assertEquals(Operation.ADD, ((OperationToken) tokens.get(2)).getOperation());
        assertEquals(Operation.SIN, ((OperationToken) tokens.get(6)).getOperation());
    }
}
