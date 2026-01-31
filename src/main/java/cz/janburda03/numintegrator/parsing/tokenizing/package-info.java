/**
 * Provides classes for tokenizing mathematical expressions.
 *
 * <p>This package contains:
 * <ul>
 *     <li>Base abstract token classes such as {@link cz.janburda03.numintegrator.parsing.tokenizing.Token}
 *         and {@link cz.janburda03.numintegrator.parsing.tokenizing.ParenthesisToken}.</li>
 *     <li>Concrete token types like {@link cz.janburda03.numintegrator.parsing.tokenizing.NumberToken},
 *         {@link cz.janburda03.numintegrator.parsing.tokenizing.VariableToken},
 *         {@link cz.janburda03.numintegrator.parsing.tokenizing.OperationToken},
 *         {@link cz.janburda03.numintegrator.parsing.tokenizing.LeftParenthesisToken}
 *         and {@link cz.janburda03.numintegrator.parsing.tokenizing.RightParenthesisToken}.</li>
 *     <li>The {@link cz.janburda03.numintegrator.parsing.tokenizing.Tokenizer} class
 *         for converting input strings or character streams into a list of tokens.</li>
 * </ul>
 *
 * <p>This package is part of the NumIntegrator project.
 */
package cz.janburda03.numintegrator.parsing.tokenizing;