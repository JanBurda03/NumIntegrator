package cz.janburda03.numintegrator.parsing.input;
import java.util.Map;
import cz.janburda03.numintegrator.parsing.formula.expressions.Expression;

/**
 * Holds all information required to perform numerical integration.
 */
public class IntegrationInput {

    private final Expression expression;
    private final int sampleCount;
    private final SamplingType samplingType;
    private final Map<String, VariableRange> variablesRanges;

    /**
     * Creates a new integration input
     *
     * @param expression parsed mathematical expression
     * @param sampleCount total number of samples to use
     * @param samplingType sampling strategy
     * @param variablesRanges map of variable names to their ranges
     * @throws IllegalArgumentException if sampleCount is not positive
     * @throws NullPointerException if any argument is null
     */
    public IntegrationInput(
            Expression expression,
            int sampleCount,
            SamplingType samplingType,
            Map<String, VariableRange> variablesRanges
    ) {
        if (sampleCount <= 0) {
            throw new IllegalArgumentException("Sample count must be positive");
        }

        this.expression = expression;
        this.sampleCount = sampleCount;
        this.samplingType = samplingType;
        this.variablesRanges = variablesRanges;
    }

    /**
     * @return the expression to be evaluated
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * @return total number of samples
     */
    public int getSampleCount() {
        return sampleCount;
    }

    /**
     * @return sampling strategy
     */
    public SamplingType getSamplingType() {
        return samplingType;
    }

    /**
     * @return map of variable names to their ranges
     */
    public Map<String, VariableRange> getVariablesRanges() {
        return variablesRanges;
    }
}
