package cz.janburda03.numintegrator.parsing.formula;

import cz.janburda03.numintegrator.parsing.formula.expressions.Expression;
import cz.janburda03.numintegrator.parsing.formula.expressions.ExpressionParser;
import cz.janburda03.numintegrator.parsing.formula.tokenizing.Token;
import cz.janburda03.numintegrator.parsing.formula.tokenizing.Tokenizer;

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
     */
    public static Expression parse(String input) {
        try {
            Tokenizer tokenizer = new Tokenizer(input);
            List<Token> tokens = tokenizer.tokenize();

            ExpressionParser parser = new ExpressionParser();
            return parser.parse(tokens);

        } catch (IOException e) {
            // This should never happen when parsing from a String
            throw new IllegalStateException(
                    "Unexpected IO error while parsing formula", e
            );
        }
    }
}
