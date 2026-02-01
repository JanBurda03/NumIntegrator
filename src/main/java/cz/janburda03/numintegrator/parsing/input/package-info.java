/**
 * Provides classes for parsing and representing integration input parameters.
 *
 * <p>
 * This package defines data structures and utilities used to describe a complete
 * numerical integration task. It includes:
 * </p>
 *
 * <ul>
 *   <li>{@link cz.janburda03.numintegrator.parsing.input.IntegrationInput}
 *       – an immutable container holding the parsed integration configuration</li>
 *   <li>{@link cz.janburda03.numintegrator.parsing.input.IntegrationInputParser}
 *       – a parser that transforms textual input into an {@code IntegrationInput}</li>
 *   <li>{@link cz.janburda03.numintegrator.parsing.input.VariableRange}
 *       – a representation of a variable domain used for sampling</li>
 *   <li>{@link cz.janburda03.numintegrator.parsing.input.SamplingType}
 *       – supported strategies for distributing samples across variables</li>
 * </ul>
 *
 * <p>
 * The classes in this package are independent of the actual numerical integration
 * algorithm and serve as a high-level interface between user input and the
 * integration core.
 * </p>
 */
package cz.janburda03.numintegrator.parsing.input;