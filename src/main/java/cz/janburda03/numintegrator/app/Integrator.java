package cz.janburda03.numintegrator.app;

import cz.janburda03.numintegrator.parsing.input.IntegrationInput;
import cz.janburda03.numintegrator.parsing.input.VariableRange;
import cz.janburda03.numintegrator.sampling.RandomSampler;
import cz.janburda03.numintegrator.sampling.Sampler;
import cz.janburda03.numintegrator.sampling.GridSampler;

import java.util.Map;
import java.util.Random;

/**
 * Numerical integrator using sampling-based methods.
 */
public class Integrator
{
    private Integrator(){}

    /**
     * Approximates a multidimensional definite integral using the sampling
     * strategy specified in the input.
     *
     * <p>The integral is computed as the average value of the integrand
     * evaluated at sampled points multiplied by the total volume of
     * the integration domain.</p>
     *
     * @param input integration configuration (function, variables, sampling)
     * @return approximate value of the integral
     */
    public static double integrate(IntegrationInput input)
    {
        Sampler sampler = switch (input.getSamplingType()) {
            case GRID -> new GridSampler(
                    input.getSampleCount(),
                    input.getVariablesRanges()
            );

            case RANDOM ->new RandomSampler(
                    input.getSampleCount(),
                    input.getVariablesRanges());

            case HALTON ->
                    throw new UnsupportedOperationException("HALTON sampling not implemented yet");
        };

        double sum = 0.0;
        for (Map<String, Double> sample : sampler)
        {
            sum += input.getExpression().evaluate(sample);
        }

        double average = sum / input.getSampleCount();
        return average * computeVolume(input.getVariablesRanges());
    }

    /**
     * Computes the total volume of the integration domain.
     *
     * @param ranges variable ranges defining the integration domain
     * @return total volume
     */
    private static double computeVolume(Map<String, VariableRange> ranges)
    {
        double volume = 1.0;
        for (VariableRange r : ranges.values()) {
            volume *= (r.getMax() - r.getMin());
        }
        return volume;
    }
}

