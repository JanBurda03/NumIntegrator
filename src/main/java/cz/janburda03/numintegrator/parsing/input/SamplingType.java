package cz.janburda03.numintegrator.parsing.input;

/**
 * Defines available strategies for sampling variables during numerical integration.
 */
public enum SamplingType {

    // Grid-based sampling (deterministic)
    UNIFORM,      // same number of samples per variable, structured grid
    PROPORTIONAL, // samples per variable proportional to its range, structured grid

    // Probabilistic sampling (Monte Carlo style)
    RANDOM,            // standard random sampling
    HALTON,            // low-discrepancy Halton sequence
}