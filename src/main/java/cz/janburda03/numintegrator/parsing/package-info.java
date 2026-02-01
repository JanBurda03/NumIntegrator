/**
 * Provides parsing functionality for mathematical formulas and numerical integration input.
 *
 * <p>
 * This package groups all components responsible for transforming user input
 * into structured representations used by the numerical integration core.
 * </p>
 *
 * <p>
 * It consists of the following subpackages:
 * </p>
 *
 * <ul>
 *   <li>{@link cz.janburda03.numintegrator.parsing.formula}
 *       – parsing of mathematical expressions</li>
 *
 *   <li>{@link cz.janburda03.numintegrator.parsing.input}
 *       – parsing and representation of integration parameters
 *       (sampling type, variable ranges, sample count)</li>
 * </ul>
 *
 * <p>
 * The parsing layer is independent of the numerical integration algorithms
 * and serves as a clean interface between textual user input and internal
 * data structures.
 * </p>
 */
package cz.janburda03.numintegrator.parsing;