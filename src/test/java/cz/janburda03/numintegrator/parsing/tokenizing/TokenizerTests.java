package cz.janburda03.numintegrator.parsing.tokenizing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTests {
    private static final double EPSILON = 1e-10;

    @Test
    void testSingleNumber() throws Exception {
        Tokenizer tokenizer = new Tokenizer("42");
        List<Token> tokens = tokenizer.tokenize();
        Assertions.assertEquals(1, tokens.size());
        assertInstanceOf(NumberToken.class, tokens.getFirst());
        Assertions.assertEquals(42.0, ((NumberToken) tokens.getLast()).getValue(), EPSILON);
    }

    @Test
    void testSingleVariable() throws Exception {
        Tokenizer tokenizer = new Tokenizer("x");
        List<Token> tokens = tokenizer.tokenize();
        Assertions.assertEquals(1, tokens.size());
        assertInstanceOf(VariableToken.class, tokens.getFirst());
        Assertions.assertEquals("x", ((VariableToken) tokens.getFirst()).getVariable());
    }

    @Test
    void testAddition() throws Exception {
        Tokenizer tokenizer = new Tokenizer("3+2");
        List<Token> tokens = tokenizer.tokenize();
        Assertions.assertEquals(3, tokens.size());
        assertInstanceOf(OperationToken.class, tokens.get(1));
        assertInstanceOf(NumberToken.class, tokens.get(0));
        assertInstanceOf(NumberToken.class, tokens.get(2));

        Assertions.assertEquals(Operation.ADD, ((OperationToken) tokens.get(1)).getOperation());
    }

    @Test
    void testUnaryMinus() throws Exception {
        Tokenizer tokenizer = new Tokenizer("-5");
        List<Token> tokens = tokenizer.tokenize();
        Assertions.assertEquals(2, tokens.size());
        assertInstanceOf(OperationToken.class, tokens.get(0));
        assertInstanceOf(NumberToken.class, tokens.get(1));
        Assertions.assertEquals(Operation.NEGATE, ((OperationToken) tokens.get(0)).getOperation());
    }

    @Test
    void testBinaryMinus() throws Exception {
        Tokenizer tokenizer = new Tokenizer("5-3");
        List<Token> tokens = tokenizer.tokenize();
        Assertions.assertEquals(3, tokens.size());
        assertInstanceOf(OperationToken.class, tokens.get(1));
        assertInstanceOf(NumberToken.class, tokens.get(0));
        assertInstanceOf(NumberToken.class, tokens.get(2));

        Assertions.assertEquals(Operation.SUBTRACT, ((OperationToken) tokens.get(1)).getOperation());
    }

    @Test
    void testUnaryMinusWithConstants() throws Exception {
        Tokenizer tokenizer = new Tokenizer("-pi -e");

        List<Token> tokens = tokenizer.tokenize();

        Assertions.assertEquals(4, tokens.size());

        assertInstanceOf(OperationToken.class, tokens.get(0));
        Assertions.assertEquals(Operation.NEGATE, ((OperationToken) tokens.get(0)).getOperation());

        assertInstanceOf(NumberToken.class, tokens.get(1));
        Assertions.assertEquals(Math.PI, ((NumberToken) tokens.get(1)).getValue(), EPSILON);

        assertInstanceOf(OperationToken.class, tokens.get(2));
        Assertions.assertEquals(Operation.SUBTRACT, ((OperationToken) tokens.get(2)).getOperation());

        assertInstanceOf(NumberToken.class, tokens.get(3));
        Assertions.assertEquals(Math.E, ((NumberToken) tokens.get(3)).getValue(), EPSILON);
    }

    @Test
    void testUnaryMinusAndAddition() throws Exception {
        Tokenizer tokenizer = new Tokenizer("-3+2");
        List<Token> tokens = tokenizer.tokenize();
        Assertions.assertEquals(4, tokens.size());

        assertInstanceOf(OperationToken.class, tokens.get(0));
        assertInstanceOf(OperationToken.class, tokens.get(2));
        assertInstanceOf(NumberToken.class, tokens.get(1));
        assertInstanceOf(NumberToken.class, tokens.get(3));

        Assertions.assertEquals(Operation.NEGATE, ((OperationToken) tokens.get(0)).getOperation());
        Assertions.assertEquals(Operation.ADD, ((OperationToken) tokens.get(2)).getOperation());
    }

    @Test
    void testParenthesesAndNegate() throws Exception {
        Tokenizer tokenizer = new Tokenizer("-1*-(x+2)");
        List<Token> tokens = tokenizer.tokenize();
        Assertions.assertEquals(9, tokens.size());

        Assertions.assertEquals(Operation.NEGATE, ((OperationToken) tokens.get(0)).getOperation());
        Assertions.assertEquals(Operation.NEGATE, ((OperationToken) tokens.get(3)).getOperation());
        Assertions.assertEquals(Operation.MULTIPLY, ((OperationToken) tokens.get(2)).getOperation());

        assertInstanceOf(LeftParenthesisToken.class, tokens.get(4));
        assertInstanceOf(RightParenthesisToken.class, tokens.get(8));
    }

    @Test
    void testFunctionSin() throws Exception {
        Tokenizer tokenizer = new Tokenizer("sin(x)");
        List<Token> tokens = tokenizer.tokenize();
        Assertions.assertEquals(4, tokens.size());
        assertInstanceOf(OperationToken.class, tokens.getFirst());
        Assertions.assertEquals(Operation.SIN, ((OperationToken) tokens.getFirst()).getOperation());
    }

    @Test
    void testFunctionNaturalLog() throws Exception {
        Tokenizer tokenizer = new Tokenizer("ln(10)");
        List<Token> tokens = tokenizer.tokenize();
        Assertions.assertEquals(4, tokens.size());
        assertInstanceOf(OperationToken.class, tokens.get(0));
        Assertions.assertEquals(Operation.LN, ((OperationToken) tokens.get(0)).getOperation());
        assertInstanceOf(LeftParenthesisToken.class, tokens.get(1));
        assertInstanceOf(RightParenthesisToken.class, tokens.get(3));
    }

    @Test
    void testFunctionLog10() throws Exception {
        Tokenizer tokenizer = new Tokenizer("log(-42)");
        List<Token> tokens = tokenizer.tokenize();
        Assertions.assertEquals(5, tokens.size());

        assertInstanceOf(OperationToken.class, tokens.get(0));
        Assertions.assertEquals(Operation.LOG, ((OperationToken) tokens.get(0)).getOperation());

        assertInstanceOf(NumberToken.class, tokens.get(3));
        Assertions.assertEquals(42.0, ((NumberToken) tokens.get(3)).getValue(), EPSILON);

        assertInstanceOf(LeftParenthesisToken.class, tokens.get(1));
        assertInstanceOf(RightParenthesisToken.class, tokens.get(4));

        Assertions.assertEquals(Operation.NEGATE, ((OperationToken) tokens.get(2)).getOperation());


    }

    @Test
    void testFunctionLog10NoParentheses() throws Exception {
        Tokenizer tokenizer = new Tokenizer("log-42");
        List<Token> tokens = tokenizer.tokenize();
        Assertions.assertEquals(3, tokens.size());

        assertInstanceOf(OperationToken.class, tokens.get(0));
        Assertions.assertEquals(Operation.LOG, ((OperationToken) tokens.get(0)).getOperation());

        assertInstanceOf(NumberToken.class, tokens.get(2));
        Assertions.assertEquals(42.0, ((NumberToken) tokens.get(2)).getValue(), EPSILON);

        Assertions.assertEquals(Operation.NEGATE, ((OperationToken) tokens.get(1)).getOperation());


    }

    @Test
    void testPowerOperation() throws Exception {
        Tokenizer tokenizer = new Tokenizer("2^-3");
        List<Token> tokens = tokenizer.tokenize();
        Assertions.assertEquals(4, tokens.size());
        Assertions.assertEquals(Operation.POWER, ((OperationToken) tokens.get(1)).getOperation());
        Assertions.assertEquals(Operation.NEGATE, ((OperationToken) tokens.get(2)).getOperation());
    }

    @Test
    void testComplexExpression() throws Exception {
        Tokenizer tokenizer = new Tokenizer("-3 - 2*sin(x)");
        List<Token> tokens = tokenizer.tokenize();
        Assertions.assertEquals(9, tokens.size());

        Assertions.assertEquals(Operation.NEGATE, ((OperationToken) tokens.get(0)).getOperation());
        Assertions.assertEquals(Operation.SUBTRACT, ((OperationToken) tokens.get(2)).getOperation());

        Assertions.assertEquals(Operation.MULTIPLY, ((OperationToken) tokens.get(4)).getOperation());
        Assertions.assertEquals(Operation.SIN, ((OperationToken) tokens.get(5)).getOperation());
    }
}
