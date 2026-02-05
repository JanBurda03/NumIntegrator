/**
 * Contains custom exceptions used by the parser and expression evaluator.
 * <p>
 * These exceptions provide more meaningful error reporting when formula
 * mathematical expressions or evaluating them with variables.
 * </p>
 *
 * <ul>
 *     <li>{@link cz.janburda03.numintegrator.parsing.formula.exceptions.UnexpectedTokenException}
 *         - Thrown when the parser encounters a token it does not expect in the current context.</li>
 *     <li>{@link cz.janburda03.numintegrator.parsing.formula.exceptions.UnexpectedBinaryOperatorException}
 *         - Thrown when a binary operator appears where only a unary operation is allowed.</li>
 *     <li>{@link cz.janburda03.numintegrator.parsing.formula.exceptions.MissingRightParenthesisException}
 *         - Thrown when a left parenthesis is not closed with a matching right parenthesis.</li>
 *     <li>{@link cz.janburda03.numintegrator.parsing.formula.exceptions.UnknownVariableException}
 *         - Thrown when evaluating an expression containing a variable that is not provided in the variable map.</li>
 *     <li>{@link cz.janburda03.numintegrator.parsing.formula.exceptions.UnexpectedEndOfInputException}
 *         - Thrown when there is a missing operand at the end of formula.</li>
 * </ul>
 *
 * <p>
 * Using these specific exceptions allows for easier debugging and clearer error messages
 * for the users of the parser and evaluator.
 * </p>
 */
package cz.janburda03.numintegrator.parsing.formula.exceptions;