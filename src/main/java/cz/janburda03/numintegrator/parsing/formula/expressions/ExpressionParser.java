package cz.janburda03.numintegrator.parsing.formula.expressions;

import cz.janburda03.numintegrator.parsing.formula.exceptions.MissingRightParenthesisException;
import cz.janburda03.numintegrator.parsing.formula.exceptions.UnexpectedBinaryOperatorException;
import cz.janburda03.numintegrator.parsing.formula.exceptions.UnexpectedTokenException;
import cz.janburda03.numintegrator.parsing.formula.tokenizing.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Parses a list of tokens into an abstract syntax tree of Expression objects.
 * The parser respects operator precedence and supports unary and binary operations.
 */
public class ExpressionParser {

    private Token[] tokens;
    private int pos;

    /**
     * Parses a list of tokens into an Expression tree.
     *
     * @param tokenList the list of tokens produced by the tokenizer
     * @return the root Expression of the parsed tree
     */
    public Expression parse(List<Token> tokenList) {
        this.tokens = tokenList.toArray(new Token[0]);
        this.pos = 0;

        return parseExpression(0);
    }

    /**
     * Parses an expression with respect to operator precedence.
     *
     * @param minPrecedence minimum precedence to consider for current expression
     * @return parsed Expression
     */
    private Expression parseExpression(int minPrecedence) {
        // first formula left operand
        Expression left = parsePrimary();

        while (true) {
            // checking next binary operation
            OperationToken opToken = peekBinaryOperator();

            // ending if the operation is not binary or the operation precedence is lower than the current
            if (opToken == null || opToken.getOperation().getPrecedence() < minPrecedence) {
                break;
            }

            // getting the operation from the binary token
            Operation op = opToken.getOperation();
            next(); // moving next from the operator

            // precedence of the current binary operation
            int nextMinPrecedence = op.getPrecedence() + 1;

            // formula the right operand
            Expression right = parseExpression(nextMinPrecedence);

            // merging the operands using the given binary operation
            left = new BinaryExpression(op, left, right);
        }

        return left;
    }

    /**
     * Parses a primary expression: number, variable, parentheses, or unary operation.
     */
    private Expression parsePrimary() {
        Token token = next();

        return switch (token) {
            case NumberToken numberToken -> new NumberExpression(numberToken.getValue());
            case VariableToken variableToken -> new VariableExpression(variableToken.getVariable());
            case OperationToken opToken -> {
                if (opToken.getOperation().getArity() == 1) {
                    Expression sub = parsePrimary(); // formula the operation following the unary operation
                    yield new UnaryExpression(opToken.getOperation(), sub);
                }
                else
                {
                    // primary token must always be non-binary operation, since there is no left side
                    throw new UnexpectedBinaryOperatorException(opToken.getOperation());
                }
            }
            case LeftParenthesisToken ignored -> {
                Expression expr = parseExpression(0);
                expectRightParenthesis(); // parenthesis must always be closed
                yield expr;
            }
            default -> throw new UnexpectedTokenException(token);
        };
    }


    /**
     * Peeks the next token and returns it if it's a binary operator.
     */
    private OperationToken peekBinaryOperator() {
        if (pos >= tokens.length) return null;

        Token t = tokens[pos];
        if (t instanceof OperationToken op && op.getOperation().getArity() == 2) {
            return op;
        }
        return null;
    }

    /**
     * Returns the current token and advances the position.
     */
    private Token next() {
        if (pos >= tokens.length) throw new NoSuchElementException();
        return tokens[pos++];
    }

    /**
     * Checks that the next token is a right parenthesis.
     * Throws MissingRightParenthesisException if not.
     */
    private void expectRightParenthesis() {
        Token t = next();
        if (!(t instanceof RightParenthesisToken)) {
            throw new MissingRightParenthesisException();
        }
    }
}

