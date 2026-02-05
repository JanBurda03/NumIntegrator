package cz.janburda03.numintegrator.sampling;

import cz.janburda03.numintegrator.parsing.input.VariableRange;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Abstract base class for all sampling strategies.
 * Implements {@link Iterator} and {@link Iterable} over sample points.
 * Concrete subclasses define how samples are generated.
 */
public abstract class Sampler implements Iterator<Map<String, Double>>, Iterable<Map<String, Double>> {

    /** Total number of samples to generate. */
    protected final int maxSamples;

    /** Current index of the next sample to generate. */
    private int sampleIndex = 0;

    /** Map of variable names to their ranges. */
    protected final Map<String, VariableRange> variablesRanges;

    /** Array of variable names for ordered access. */
    protected final String[] variables;

    /**
     * Creates a sampler that will generate the given number of samples.
     *
     * @param maxSamples total number of samples to generate (must be positive)
     * @param variablesRanges map of ranges for all variables (must be non-empty)
     * @throws IllegalArgumentException if {@code maxSamples <= 0} or {@code variablesRanges} is empty
     */
    public Sampler(int maxSamples, Map<String, VariableRange> variablesRanges) {
        if (maxSamples <= 0) {
            throw new IllegalArgumentException("maxSamples must be positive");
        }

        if (variablesRanges.isEmpty()) {
            throw new IllegalArgumentException("variables must be non-empty");
        }

        this.maxSamples = maxSamples;
        this.variablesRanges = variablesRanges;
        this.variables = variablesRanges.keySet().toArray(new String[0]);
    }

    /**
     * Returns this sampler as an {@link Iterable}.
     *
     * @return this iterator
     */
    @Override
    public final Iterator<Map<String, Double>> iterator() {
        return this;
    }

    /**
     * @return {@code true} if more samples are available
     */
    @Override
    public final boolean hasNext() {
        return sampleIndex < maxSamples;
    }

    /**
     * Returns the next sample point.
     *
     * @return a map of variable names to sampled values
     * @throws NoSuchElementException if no more samples are available
     */
    @Override
    public final Map<String, Double> next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more samples available");
        }
        return getNext(sampleIndex++);
    }

    /**
     * Computes the sample point for the given sample index.
     * Subclasses implement the actual sampling strategy.
     *
     * @param index index of the sample in range {@code [0, maxSamples)}
     * @return a map of variable names to their sampled values
     */
    protected abstract Map<String, Double> getNext(int index);
}
