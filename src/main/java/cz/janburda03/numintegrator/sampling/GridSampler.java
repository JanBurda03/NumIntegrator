package cz.janburda03.numintegrator.sampling;

import cz.janburda03.numintegrator.parsing.input.VariableRange;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.pow;

/**
 * grid-based sampling
 */
public class GridSampler extends Sampler
{
    /** Step size for each variable. */
    private final Map<String, Double> shifts;

    /** Maximum number of steps for each variable. */
    private final Map<String, Integer> maxNumberOfShifts;

    /** Current step count for each variable. */
    private Map<String, Integer> variableCounts;

    /** Index of the currently updated variable. */
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

        maxNumberOfShifts = getMaxNumberOfShifts();
        // MUST BE COMPUTED AFTER variableMaxCounts IS ALLOCATED
        shifts = getShifts();

        variableCounts = new HashMap<>();
        values = new HashMap<>();

        // Initialize counters and values to minimums
        for (String variable : variables)
        {
            variableCounts.put(variable, 0);
            values.put(variable, variablesRanges.get(variable).getMin());
        }
    }

    /**
     * Returns the next grid sample.
     */
    @Override
    protected final Map<String, Double> getNext(int index)
    {
        // Advance state for all samples except the first one
        if (index != 0)
        {
            updateValues();
        }

        return new HashMap<>(values);
    }

    /**
     * Advances the internal grid state to the next point.
     */
    private void updateValues()
    {
        int i = currentVariableIndex;

        while (true)
        {
            String var = variables[i];
            int count = variableCounts.get(var);
            int max = maxNumberOfShifts.get(var);

            if (count < max)
            {
                // Increment current variable
                variableCounts.put(var, count + 1);

                // check that the shift never exceeds the max value (it is common for very small values)
                double newValue = values.get(var) + shifts.get(var);
                double maxValue = variablesRanges.get(var).getMax();
                values.put(var, Math.min(newValue, maxValue));

                currentVariableIndex = 0;
                return;
            }
            else
            {
                // Reset variable and carry to the next one
                variableCounts.put(var, 0);
                values.put(var, variablesRanges.get(var).getMin());

                i++;
                if (i >= variables.length)
                {
                    throw new IllegalStateException("All samples were already given");
                }
            }
        }
    }

    private Map<String, Integer> getMaxNumberOfShifts() {
        Map<String, Integer> result = new HashMap<>();

        int n = variables.length;

        // base samples per variable
        int k = (int) Math.floor(pow(maxSamples, 1.0 / n));
        k = Math.max(k, 1);

        int[] samples = new int[n];
        for (int i = 0; i < n; i++) {
            samples[i] = k;
        }

        // try to increment dimensions while product <= maxSamples
        int product;
        for (int i = 0; i < n; i++) {
            product = (int)(Math.pow(k+1, i) * Math.pow(k,n-i));
            if (product >= maxSamples) {
                break;
            }
            samples[i]++;
        }


        // convert samples maxCount = samples - 1
        for (int i = 0; i < n; i++) {
            result.put(variables[i], samples[i] - 1);
        }

        return result;
    }

    private Map<String, Double> getShifts() {
        Map<String, Double> shifts = new HashMap<>();

        for (String var : variables) {
            VariableRange range = variablesRanges.get(var);
            double min = range.getMin();
            double max = range.getMax();
            int maxCount = maxNumberOfShifts.get(var);

            double shift = maxCount > 0 ? (max - min) / maxCount : 0.0; // if there is only one state (maxCount) for variable, there is no shift
            shifts.put(var, shift);
        }

        return shifts;
    }
}
