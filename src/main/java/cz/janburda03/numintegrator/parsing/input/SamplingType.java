package cz.janburda03.numintegrator.parsing.input;

/**
 * Defines available strategies for sampling variables during numerical integration.
 */
public enum SamplingType {

    /**
     * Uses the same number of samples for each variable,
     * regardless of its range.
     */
    UNIFORM,

    /**
     * Allocates the number of samples for each variable
     * proportionally to its numeric range.
     */
    RANGE_BASED,

    /**
     * Uses probabilistic (random) sampling of variables.
     */
    PROBABILISTIC
}