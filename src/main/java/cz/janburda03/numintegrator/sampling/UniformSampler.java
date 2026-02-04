package cz.janburda03.numintegrator.sampling;

import cz.janburda03.numintegrator.parsing.input.VariableRange;

import java.util.Map;

import java.util.HashMap;

/**
 * Uniform grid sampler.
 * Every variable has the same number of samples.
 */
public class UniformSampler extends GridSampler
{
    /**
     * Creates a uniform sampler.
     *
     * @param maxSamples      total number of samples to generate
     * @param variablesRanges map of ranges for all variables
     */
    public UniformSampler(int maxSamples, Map<String, VariableRange> variablesRanges) {
        super(maxSamples, variablesRanges);
    }

    @Override
    protected Map<String, Integer> getVariableMaxCounts() {
        Map<String, Integer> result = new HashMap<>();

        int n = variables.length;
        int base = maxSamples / n;
        int remainder = maxSamples % n;

        for (int i = 0; i < n; i++) {
            // first remainder variables get one extra sample
            int steps = base + (i < remainder ? 1 : 0);
            result.put(variables[i], Math.max(steps - 1, 0)); // maxCount = steps - 1
        }

        return result;
    }

    @Override
    protected Map<String, Double> getShifts() {
        Map<String, Double> shifts = new HashMap<>();

        for (String var : variables) {
            VariableRange range = variablesRanges.get(var);
            double min = range.getMin();
            double max = range.getMax();
            int maxCount = variableMaxCounts.get(var);

            double shift = maxCount > 0 ? (max - min) / maxCount : 0.0; // if there is only one state (maxCount) for variable, there is no shift
            shifts.put(var, shift);
        }

        return shifts;
    }
}

