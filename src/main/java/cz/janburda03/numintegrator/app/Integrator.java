package cz.janburda03.numintegrator.app;

import cz.janburda03.numintegrator.parsing.input.IntegrationInput;
import cz.janburda03.numintegrator.parsing.input.VariableRange;
import cz.janburda03.numintegrator.sampling.Sampler;
import cz.janburda03.numintegrator.sampling.UniformSampler;

import java.util.Map;

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
            case UNIFORM -> new UniformSampler(
                    input.getSampleCount(),
                    input.getVariablesRanges()
            );

            case PROPORTIONAL ->
                    throw new UnsupportedOperationException("PROPORTIONAL sampling not implemented yet");

            case RANDOM ->
                    throw new UnsupportedOperationException("RANDOM sampling not implemented yet");

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
     * @return total hyper-rectangular volume
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

