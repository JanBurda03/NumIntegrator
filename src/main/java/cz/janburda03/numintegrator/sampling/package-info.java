/**
 * Provides sampling strategies for numerical integration and variable exploration.
 *
 * <p>This package contains:
 * <ul>
 *     <li>{@link cz.janburda03.numintegrator.sampling.Sampler} – abstract base class defining the interface for all samplers</li>
 *     <li>{@link cz.janburda03.numintegrator.sampling.GridSampler} – generates samples on a regular grid</li>
 *     <li>{@link cz.janburda03.numintegrator.sampling.RandomSampler} – generates random samples independently within variable ranges</li>
 *     <li>{@link cz.janburda03.numintegrator.sampling.HaltonSampler} – generates random samples using Halton low-discrepancy sequences</li>
 * </ul>
 *
 * <p>All samplers produce a finite number of samples and expose them through
 * {@link java.util.Iterator} and {@link java.lang.Iterable}.
 */
package cz.janburda03.numintegrator.sampling;
