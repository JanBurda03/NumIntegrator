package cz.janburda03.numintegrator.formula_parser.tokenizing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    private final Reader reader;
    private int currentChar;

    public Tokenizer(Reader reader) throws IOException
    {
        this.reader = new BufferedReader(reader);
        this.currentChar = this.reader.read();
    }

    public Tokenizer(String input) throws IOException
    {
        this(new StringReader(input));
    }

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

    private OperationToken analyzeFunction(String ident) {
        return switch (ident) {
            case "sin" -> new OperationToken(Operation.SIN);
            case "cos" -> new OperationToken(Operation.COS);
            case "tan" -> new OperationToken(Operation.TAN);
            case "cot" -> new OperationToken(Operation.COTAN);
            case "ln"  -> new OperationToken(Operation.LN);
            case "log" -> new OperationToken(Operation.LOG);
            case "exp" -> new OperationToken(Operation.EXP);
            case "abs" -> new OperationToken(Operation.ABS);
            case "sqrt"-> new OperationToken(Operation.SQRT);
            case "pow" -> new OperationToken(Operation.POWER);
            default -> null; // it is a variable
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
