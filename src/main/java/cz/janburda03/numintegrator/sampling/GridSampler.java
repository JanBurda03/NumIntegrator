package cz.janburda03.numintegrator.sampling;

import cz.janburda03.numintegrator.parsing.input.VariableRange;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.pow;

/**
 * Base class for grid-based sampling.
 * Generates samples on a regular grid within variable ranges.
 */
public class GridSampler extends Sampler {

    /** Step size for each variable. */
    private final Map<String, Double> shifts;

    /** Maximum number of steps (shifts) for each variable. */
    private final Map<String, Integer> maxNumberOfShifts;

    /** Current step count for each variable. */
    private Map<String, Integer> variableCounts;

    /** Index of the currently updated variable (for advancing the grid). */
    private int currentVariableIndex = 0;

    /** Current values of all variables. */
    private Map<String, Double> values;

    /**
     * Creates a grid sampler that generates a fixed number of samples.
     *
     * @param maxSamples total number of samples to generate
     * @param variablesRanges map of ranges for all variables
     */
    public GridSampler(int maxSamples, Map<String, VariableRange> variablesRanges) {
        super(maxSamples, variablesRanges);

        // Determine maximum number of shifts for each variable
        maxNumberOfShifts = getMaxNumberOfShifts();

        // Compute step sizes for each variable
        shifts = getShifts();

        variableCounts = new HashMap<>();
        values = new HashMap<>();

        // Initialize counters and values to variable minimums
        for (String variable : variables) {
            variableCounts.put(variable, 0);
            values.put(variable, variablesRanges.get(variable).getMin());
        }
    }

    /**
     * Returns the next sample from the grid.
     *
     * @param index index of the sample (used internally to decide whether to advance)
     * @return map of variable names to their current values
     */
    @Override
    protected final Map<String, Double> getNext(int index) {
        // Advance grid state for all samples except the first one
        if (index != 0) {
            updateValues();
        }
        // Return a copy to prevent external modification
        return new HashMap<>(values);
    }

    /**
     * Advances the internal grid state to the next point.
     * Increments the current variable, carrying over to the next if needed.
     */
    private void updateValues() {
        int i = currentVariableIndex;

        while (true) {
            String var = variables[i];
            int count = variableCounts.get(var);
            int max = maxNumberOfShifts.get(var);

            if (count < max) {
                // Increment current variable
                variableCounts.put(var, count + 1);

                // Ensure value does not exceed the maximum (important for very small step sizes)
                double newValue = values.get(var) + shifts.get(var);
                double maxValue = variablesRanges.get(var).getMax();
                values.put(var, Math.min(newValue, maxValue));

                currentVariableIndex = 0;
                return;
            } else {
                // Reset current variable and carry to the next
                variableCounts.put(var, 0);
                values.put(var, variablesRanges.get(var).getMin());

                i++;
                if (i >= variables.length) {
                    throw new IllegalStateException("All samples have already been generated");
                }
            }
        }
    }

    /**
     * Computes the maximum number of shifts (steps) for each variable,
     * trying to distribute the total number of samples approximately evenly across the grid.
     *
     * @return map of variable names to their maximum step counts
     */
    private Map<String, Integer> getMaxNumberOfShifts() {
        Map<String, Integer> result = new HashMap<>();
        int n = variables.length;

        // Base samples per variable (approximate nth root of maxSamples)
        int k = (int) Math.floor(pow(maxSamples, 1.0 / n));
        k = Math.max(k, 1);

        int[] samples = new int[n];
        for (int i = 0; i < n; i++) {
            samples[i] = k;
        }

        // Increment individual dimensions while product <= maxSamples
        int product;
        for (int i = 0; i < n; i++) {
            product = (int)(Math.pow(k + 1, i) * Math.pow(k, n - i));
            if (product >= maxSamples) {
                break;
            }
            samples[i]++;
        }

        // Convert to maximum shifts (maxCount = samples - 1)
        for (int i = 0; i < n; i++) {
            result.put(variables[i], samples[i] - 1);
        }

        return result;
    }

    /**
     * Computes step sizes for each variable based on its range and maximum number of shifts.
     *
     * @return map of variable names to step sizes
     */
    private Map<String, Double> getShifts() {
        Map<String, Double> shifts = new HashMap<>();

        for (String var : variables) {
            VariableRange range = variablesRanges.get(var);
            double min = range.getMin();
            double max = range.getMax();
            int maxCount = maxNumberOfShifts.get(var);

            double shift = maxCount > 0 ? (max - min) / maxCount : 0.0; // if only one state, no shift
            shifts.put(var, shift);
        }

        return shifts;
    }
}
