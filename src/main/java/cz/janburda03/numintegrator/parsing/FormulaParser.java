package cz.janburda03.numintegrator.parsing;

import cz.janburda03.numintegrator.parsing.expressions.Expression;
import cz.janburda03.numintegrator.parsing.expressions.ExpressionParser;
import cz.janburda03.numintegrator.parsing.tokenizing.Token;
import cz.janburda03.numintegrator.parsing.tokenizing.Tokenizer;

import java.io.IOException;
import java.util.List;

/**
 * Main parser for converting a mathematical expression string
 * into an {@link Expression} tree.
 */
public final class FormulaParser {
    private FormulaParser() {}

    /**
     * Parses the input string into an expression tree.
     *
     * @param input the mathematical expression as a string
     * @return the root {@link Expression} of the parsed expression tree
     * @throws IOException if tokenization fails
     */
    public static Expression parse(String input) throws IOException {
        Tokenizer tokenizer = new Tokenizer(input);
        List<Token> tokens = tokenizer.tokenize();

        ExpressionParser parser = new ExpressionParser();
        return parser.parse(tokens);
    }
}
