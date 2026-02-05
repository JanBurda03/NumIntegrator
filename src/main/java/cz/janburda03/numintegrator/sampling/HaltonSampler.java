package cz.janburda03.numintegrator.sampling;

import cz.janburda03.numintegrator.parsing.input.VariableRange;

import java.util.HashMap;
import java.util.Map;

/**
 * Sampler that generates samples using the Halton low-discrepancy sequence.
 * Produces quasi-random, evenly distributed points within variable ranges.
 */
public class HaltonSampler extends Sampler {

    /** Prime bases used for each dimension to generate Halton sequence. */
    private final int[] bases;

    /**
     * Creates a Halton sampler.
     *
     * @param maxSamples      total number of samples to generate
     * @param variablesRanges map of ranges for all variables
     */
    public HaltonSampler(int maxSamples, Map<String, VariableRange> variablesRanges) {
        super(maxSamples, variablesRanges);

        // Generate first n primes for bases, one per variable
        bases = firstNPrimes(variables.length);
    }

    /**
     * Generates the next Halton sample for all variables.
     *
     * @param index the index of the sample (used to compute Halton sequence)
     * @return map of variable names to their current values
     */
    @Override
    protected Map<String, Double> getNext(int index) {
        Map<String, Double> values = new HashMap<>();

        for (int dim = 0; dim < variables.length; dim++) {
            String variable = variables[dim];
            VariableRange range = variablesRanges.get(variable);

            // Halton value in [0,1)
            double h = halton(index + 1, bases[dim]);

            // Map to variable range
            double value = range.getMin() + h * (range.getMax() - range.getMin());
            values.put(variable, value);
        }

        return values;
    }

    /**
     * Computes the Halton sequence value for a given index and base.
     *
     * @param index index in the sequence (starting from 1)
     * @param base prime base for the dimension
     * @return quasi-random number in [0,1)
     */
    private double halton(int index, int base) {
        double result = 0.0;
        double f = 1.0 / base;
        int i = index;

        while (i > 0) {
            result += f * (i % base);
            i = i / base;
            f = f / base;
        }

        return result;
    }

    /**
     * Generates the first n prime numbers (2, 3, 5, ...).
     * Each prime is used as a base for one dimension of the Halton sequence.
     *
     * @param n number of primes to generate
     * @return array of first n primes
     */
    private int[] firstNPrimes(int n) {
        int[] primes = new int[n];
        int count = 0;
        int candidate = 2;

        while (count < n) {
            boolean isPrime = true;
            for (int i = 2; i * i <= candidate; i++) {
                if (candidate % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes[count++] = candidate;
            }
            candidate++;
        }

        return primes;
    }
}
