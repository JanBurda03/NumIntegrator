/**
 * Provides functionality for formula mathematical expressions into an expression tree.
 *
 * <p>
 * This package contains the main components for formula:
 * </p>
 *
 * <ul>
 *     <li><b>{@link cz.janburda03.numintegrator.parsing.formula.exceptions}</b> – contains custom exceptions used during tokenization and formula.</li>
 *
 *     <li><b>{@link cz.janburda03.numintegrator.parsing.formula.tokenizing}</b> – converts input strings into tokens and defines supported operations.</li>
 *
 *     <li><b>{@link cz.janburda03.numintegrator.parsing.formula.expressions}</b> – represents nodes of the expression tree and parses tokens into a tree.</li>
 *
 *     <li><b>{@link cz.janburda03.numintegrator.parsing.formula.FormulaParser}</b> – top-level utility class providing a static method
 *         {@link cz.janburda03.numintegrator.parsing.formula.FormulaParser#parse(String)}
 *         to convert a string directly into an {@link cz.janburda03.numintegrator.parsing.formula.expressions.Expression}.</li>
 * </ul>
 *
 * <p>
 * Together, these components allow formula and evaluating mathematical expressions
 * including numbers, variables, unary and binary operations, and parentheses.
 * </p>
 */
package cz.janburda03.numintegrator.parsing.formula;
