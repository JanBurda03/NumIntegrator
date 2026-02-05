package cz.janburda03.numintegrator.parsing.formula.tokenizing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Tokenizer for mathematical expressions.
 * Converts a string or character stream into a list of tokens
 * such as numbers, variables, constants, functions, operations, and parentheses.
 */
public class Tokenizer {

    private final Reader reader;
    private int currentChar;

    /**
     * Creates a new Tokenizer from a character stream.
     *
     * @param reader the input source to tokenize
     * @throws IOException if reading from the input fails
     */
    public Tokenizer(Reader reader) throws IOException
    {
        this.reader = new BufferedReader(reader);
        this.currentChar = this.reader.read();
    }

    /**
     * Creates a new Tokenizer from a string.
     *
     * @param input the string to tokenize
     * @throws IOException if reading fails (rare for StringReader)
     */
    public Tokenizer(String input) throws IOException
    {
        this(new StringReader(input));
    }

    /**
     * Tokenizes the input into a list of tokens.
     *
     * @return list of tokens representing numbers, variables, functions, operations, and parentheses
     * @throws IOException if reading from the input fails
     */
    public List<Token> tokenize() throws IOException {
        List<Token> tokens = new ArrayList<>();

        while (currentChar != -1) {
            if (Character.isWhitespace(currentChar))
            {
                advance();
            }
            else if (Character.isDigit(currentChar) || currentChar == '.')
            {
                tokens.add(readNumber());
            }
            else if (Character.isLetter(currentChar)) {
                tokens.add(readIdentifier());
            }
            else
            {
                Token token = readSymbol();

                // Convert '-' to unary minus if appropriate
                if (token instanceof OperationToken && ((OperationToken)token).getOperation()==Operation.SUBTRACT) {
                    if (tokens.isEmpty() || tokens.getLast() instanceof OperationToken || tokens.getLast() instanceof LeftParenthesisToken) {
                       token =  new OperationToken(Operation.NEGATE);
                    }
                }
                tokens.add(token);
            }
        }

        return tokens;
    }




    private void advance() throws IOException {
        currentChar = reader.read();
    }

    private Token readIdentifier() throws IOException {
        StringBuilder sb = new StringBuilder();

        sb.append((char) currentChar);
        advance();

        while (currentChar != -1 && Character.isLetter(currentChar)) {
            sb.append((char) currentChar);
            advance();
        }

        String letters = sb.toString();

        Token constantToken = analyzeConstant(letters);
        if (constantToken != null) {
            return constantToken;
        }

        Token functionToken = analyzeFunction(letters);
        if (functionToken != null) {
            return functionToken;
        }

        while (currentChar != -1 && Character.isDigit(currentChar)) {
            sb.append((char) currentChar);
            advance();
        }

        return new VariableToken(sb.toString());
    }

    private Token analyzeConstant(String constant) {
        return switch (constant) {
            case "pi" -> new NumberToken(Math.PI);
            case "e"  -> new NumberToken(Math.E);
            default   -> null; // it is not a constant
        };
    }

    private OperationToken analyzeFunction(String fun) {
        return switch (fun) {
            case "sin" -> new OperationToken(Operation.SIN);
            case "cos" -> new OperationToken(Operation.COS);
            case "tan" -> new OperationToken(Operation.TAN);
            case "cot" -> new OperationToken(Operation.COT);
            case "ln"  -> new OperationToken(Operation.LN);
            case "log" -> new OperationToken(Operation.LOG);
            case "exp" -> new OperationToken(Operation.EXP);
            case "abs" -> new OperationToken(Operation.ABS);
            case "sqrt"-> new OperationToken(Operation.SQRT);
            default -> null; // it is not a function
        };
    }

    private Token readSymbol() throws IOException {
        char ch = (char) currentChar;
        advance();

        return switch (ch) {
            case '+' -> new OperationToken(Operation.ADD);
            case '-' -> new OperationToken(Operation.SUBTRACT);
            case '*' -> new OperationToken(Operation.MULTIPLY);
            case '/' -> new OperationToken(Operation.DIVIDE);
            case '^' -> new OperationToken(Operation.POWER);
            case '(' -> new LeftParenthesisToken();
            case ')' -> new RightParenthesisToken();
            default  -> throw new IllegalArgumentException("Unknown symbol: " + ch);
        };
    }

    private NumberToken readNumber() throws IOException {
        StringBuilder sb = new StringBuilder();

        while (currentChar != -1 &&
                (Character.isDigit(currentChar) || currentChar == '.')) {
            sb.append((char) currentChar);
            advance();
        }

        return new NumberToken(Double.parseDouble(sb.toString()));
    }
}
