package cz.janburda03.numintegrator.sampling;

import cz.janburda03.numintegrator.parsing.input.VariableRange;

import java.util.HashMap;
import java.util.Map;

public class RandomSampler extends Sampler
{

    /**
     * Creates a sampler that will generate the given number of samples using randomness.
     *
     * @param maxSamples      total number of samples to generate
     * @param variablesRanges map of ranges for all variables
     * @throws IllegalArgumentException if {@code maxSamples <= 0}
     */
    public RandomSampler(int maxSamples, Map<String, VariableRange> variablesRanges) {
        super(maxSamples, variablesRanges);
    }

    @Override
    protected Map<String, Double> getNext(int index) {
        Map<String, Double> values = new HashMap<>();

        double value;
        double min;
        double max;
        for(String variable:variables)
        {
            min = variablesRanges.get(variable).getMin();
            max = variablesRanges.get(variable).getMax();
            value = min + Math.random() * (max - min);
            values.put(variable, value);
        }
        return values;
    }
}
