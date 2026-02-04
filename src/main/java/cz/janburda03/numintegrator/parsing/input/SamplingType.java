package cz.janburda03.numintegrator.parsing.input;

/**
 * Defines available strategies for sampling variables during numerical integration.
 */
public enum SamplingType {


    GRID,      // Grid-based sampling (deterministic)

    // Probabilistic sampling (Monte Carlo style)
    RANDOM,            // standard random sampling
    HALTON,            // low-discrepancy Halton sequence
}