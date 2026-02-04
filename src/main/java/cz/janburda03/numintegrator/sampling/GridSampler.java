package cz.janburda03.numintegrator.sampling;

import cz.janburda03.numintegrator.parsing.input.VariableRange;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for grid-based sampling strategies.
 */
public abstract class GridSampler extends Sampler
{
    /** Step size for each variable. */
    protected final Map<String, Double> shifts;

    /** Maximum number of steps for each variable. */
    protected final Map<String, Integer> variableMaxCounts;

    /** Index of the currently updated variable. */
    protected int currentVariableIndex = 0;

    /** Current step count for each variable. */
    protected Map<String, Integer> variableCounts;

    /** Current values of all variables. */
    protected Map<String, Double> values;

    /**
     * Creates a grid sampler that generates a fixed number of samples.
     *
     * @param maxSamples total number of samples to generate
     * @param variablesRanges map of ranges for all variables
     */
    public GridSampler(int maxSamples, Map<String, VariableRange> variablesRanges) {
        super(maxSamples, variablesRanges);

        variableMaxCounts = getVariableMaxCounts();
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
            int max = variableMaxCounts.get(var);

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

    /** @return step sizes for all variables */
    protected abstract Map<String, Double> getShifts();

    /** @return maximum step counts for all variables */
    protected abstract Map<String, Integer> getVariableMaxCounts();
}
