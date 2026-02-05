package cz.janburda03.numintegrator.sampling;

import cz.janburda03.numintegrator.parsing.input.VariableRange;

import java.util.HashMap;
import java.util.Map;

/**
 * Sampler that generates random samples within variable ranges.
 * Each variable is sampled independently and uniformly in [min, max).
 */
public class RandomSampler extends Sampler {

    /**
     * Creates a random sampler.
     *
     * @param maxSamples      total number of samples to generate
     * @param variablesRanges map of ranges for all variables
     * @throws IllegalArgumentException if {@code maxSamples <= 0}
     */
    public RandomSampler(int maxSamples, Map<String, VariableRange> variablesRanges) {
        super(maxSamples, variablesRanges);
    }

    /**
     * Generates the next random sample for all variables.
     * Each variable value is drawn independently and uniformly from its range [min, max).
     *
     * @param index the index of the sample (ignored in RandomSampler)
     * @return map of variable names to their random values
     */
    @Override
    protected Map<String, Double> getNext(int index) {
        Map<String, Double> values = new HashMap<>();

        for (String variable : variables) {
            VariableRange range = variablesRanges.get(variable);
            double min = range.getMin();
            double max = range.getMax();

            // Random value in [min, max)
            double value = min + Math.random() * (max - min);
            values.put(variable, value);
        }

        return values;
    }
}

