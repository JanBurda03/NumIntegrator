/**
 * Numerical integration framework using sampling-based methods.
 *
 * <p>This module provides a complete solution for approximating
 * multidimensional definite integrals based on user input and
 * different sampling strategies.</p>
 *
 * <p>It consists of three main layers/packages:</p>
 *
 * <ul>
 *     <li>{@link cz.janburda03.numintegrator.parsing}
 *         – parsing layer that converts textual user input (formulas,
 *         variable ranges, sample counts, sampling type) into structured
 *         internal representations. Independent of sampling algorithms.</li>
 *
 *     <li>{@link cz.janburda03.numintegrator.sampling}
 *         – sampling layer providing various strategies to generate sample points:
 *         <ul>
 *             <li>{@link cz.janburda03.numintegrator.sampling.Sampler} – abstract base class</li>
 *             <li>{@link cz.janburda03.numintegrator.sampling.GridSampler} – uniform grid sampling</li>
 *             <li>{@link cz.janburda03.numintegrator.sampling.RandomSampler} – independent random sampling</li>
 *             <li>{@link cz.janburda03.numintegrator.sampling.HaltonSampler} – low-discrepancy Halton sequences</li>
 *         </ul>
 *         All samplers expose iterators for traversing generated points.</li>
 *
 *     <li>{@link cz.janburda03.numintegrator.app}
 *         – application layer containing:
 *         <ul>
 *             <li>{@link cz.janburda03.numintegrator.app.Integrator} – performs numerical integration using chosen sampler</li>
 *             <li>{@link cz.janburda03.numintegrator.app.Main} – command-line entry point</li>
 *         </ul>
 *         Responsible for orchestrating parsing, sampling, and integration.</li>
 * </ul>
 *
 * <p>Together, these packages form a structured, modular framework for
 * performing sampling-based numerical integration with clear separation
 * between input parsing, sample generation, and computation.</p>
 */
package cz.janburda03.numintegrator;