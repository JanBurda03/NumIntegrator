package cz.janburda03.numintegrator.sampling;

import cz.janburda03.numintegrator.parsing.input.VariableRange;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Abstract base class for all sampling strategies.
 */
public abstract class Sampler implements Iterator<Map<String, Double>>
{
    protected final int maxSamples;
    private int sampleIndex = 0;

    protected final Map<String, VariableRange> variablesRanges;
    protected final String[] variables;

    /**
     * Creates a sampler that will generate the given number of samples.
     * @param maxSamples total number of samples to generate
     * @param variablesRanges map of ranges for all variables
     * @throws IllegalArgumentException if {@code maxSamples <= 0}
     */
    public Sampler(int maxSamples, Map<String, VariableRange> variablesRanges) {
        if (maxSamples <= 0)
        {
            throw new IllegalArgumentException("maxSamples must be positive");
        }

        if (variablesRanges.isEmpty())
        {
            throw new IllegalArgumentException("variables must be non empty");
        }

        this.maxSamples = maxSamples;
        this.variablesRanges = variablesRanges;
        variables = variablesRanges.keySet().toArray(new String[0]);
    }

    /**
     * @return {@code true} if {@link #next()} can be called
     */
    @Override
    public final boolean hasNext() {
        return sampleIndex < maxSamples;
    }

    /**
     * Returns the next sample point.
     * @return a map of variable names to sampled values
     * @throws NoSuchElementException if no more samples are available
     */
    @Override
    public final Map<String, Double> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return getNext(sampleIndex++);
    }

    /**
     * Computes the sample point for the given sample index.
     * @param index index of the sample in range {@code [0, maxSamples)}
     * @return a map of variable names to their sampled values
     */
    protected abstract Map<String, Double> getNext(int index);
}
