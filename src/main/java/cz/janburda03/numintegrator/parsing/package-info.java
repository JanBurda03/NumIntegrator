/**
 * Provides functionality for parsing mathematical expressions into an expression tree.
 *
 * <p>
 * This package contains the main components for parsing:
 * </p>
 *
 * <ul>
 *     <li><b>{@link cz.janburda03.numintegrator.parsing.exceptions}</b> – contains custom exceptions used during tokenization and parsing.</li>
 *
 *     <li><b>{@link cz.janburda03.numintegrator.parsing.tokenizing}</b> – converts input strings into tokens and defines supported operations.</li>
 *
 *     <li><b>{@link cz.janburda03.numintegrator.parsing.expressions}</b> – represents nodes of the expression tree and parses tokens into a tree.</li>
 *
 *     <li><b>{@link cz.janburda03.numintegrator.parsing.FormulaParser}</b> – top-level utility class providing a static method
 *         {@link cz.janburda03.numintegrator.parsing.FormulaParser#parse(String)}
 *         to convert a string directly into an {@link cz.janburda03.numintegrator.parsing.expressions.Expression}.</li>
 * </ul>
 *
 * <p>
 * Together, these components allow parsing and evaluating mathematical expressions
 * including numbers, variables, unary and binary operations, and parentheses.
 * </p>
 */
package cz.janburda03.numintegrator.parsing;
