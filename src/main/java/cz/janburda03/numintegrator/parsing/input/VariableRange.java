package cz.janburda03.numintegrator.parsing.input;

/**
 * Represents a numeric range of a variable.
 */
public class VariableRange {

    private final double min;
    private final double max;

    /**
     * Creates a variable range with given bounds.
     *
     * @param min lower bound (inclusive)
     * @param max upper bound (exclusive)
     * @throws IllegalArgumentException if min is greater than or equal to max
     */
    public VariableRange(double min, double max) {
        if (min >= max) {
            throw new IllegalArgumentException("Invalid variable range");
        }
        this.min = min;
        this.max = max;
    }

    /**
     * @return the lower bound of the range
     */
    public double getMin() {
        return min;
    }

    /**
     * @return the upper bound of the range
     */
    public double getMax() {
        return max;
    }
}
