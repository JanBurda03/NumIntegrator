/**
 * Contains classes representing mathematical expressions and the parser that converts
 * a list of tokens into an expression tree.
 * <p>
 * The main components in this package are:
 * </p>
 *
 * <ul>
 *     <li>{@link cz.janburda03.numintegrator.parsing.expressions.Expression}
 *         - The abstract base class for all expression nodes.</li>
 *     <li>{@link cz.janburda03.numintegrator.parsing.expressions.NumberExpression}
 *         - Represents a numeric constant.</li>
 *     <li>{@link cz.janburda03.numintegrator.parsing.expressions.VariableExpression}
 *         - Represents a variable, whose value is provided at evaluation time.</li>
 *     <li>{@link cz.janburda03.numintegrator.parsing.expressions.UnaryExpression}
 *         - Represents a unary operation (e.g., negation, sin, cos, ln) applied to a single subexpression.</li>
 *     <li>{@link cz.janburda03.numintegrator.parsing.expressions.BinaryExpression}
 *         - Represents a binary operation (e.g., addition, multiplication, power) applied to two subexpressions.</li>
 *     <li>{@link cz.janburda03.numintegrator.parsing.expressions.ExpressionParser}
 *         - Parses a list of tokens produced by the tokenizer into a structured expression tree,
 *           respecting operator precedence and parentheses.</li>
 * </ul>
 *
 * <p>
 * These classes together allow building and evaluating expression trees with variables,
 * constants, unary and binary operations, and proper operator precedence.
 * </p>
 */
package cz.janburda03.numintegrator.parsing.expressions;
