package cz.janburda03.numintegrator.parsing.input;

import cz.janburda03.numintegrator.parsing.formula.FormulaParser;
import cz.janburda03.numintegrator.parsing.formula.expressions.Expression;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses a full integration input definition into an {@link IntegrationInput}.
 * Expected input format:
 * <pre>
 * formula
 * sampleCount
 * samplingType
 * varName1 min max
 * varName2 min max
 * ...
 * </pre>
 */
public class IntegrationInputParser {

    /**
     * Parses integration input from an array of strings.
     *
     * @param args input arguments
     * @return parsed {@link IntegrationInput}
     * @throws IllegalArgumentException if the input format is invalid
     */
    public static IntegrationInput parse(String[] args) {
        if (args.length < 6) {
            throw new IllegalArgumentException("Not enough arguments for integration input");
        }

        int index = 0;

        // parse formula
        String formula = args[index++];
        Expression expression = FormulaParser.parse(formula);

        // parse sample count
        int sampleCount;
        try {
            sampleCount = Integer.parseInt(args[index++]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid sample count", e);
        }

        // parse sampling type
        SamplingType samplingType;
        try {
            samplingType = SamplingType.valueOf(args[index++].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown sampling type", e);
        }

        // parse variable ranges
        Map<String, VariableRange> ranges = new HashMap<>();

        String varName;
        double min;
        double max;

        while (index < args.length) {
            if (index + 2 >= args.length) {
                throw new IllegalArgumentException("Incomplete variable range definition");
            }

            varName = args[index++];

            try {
                min = Double.parseDouble(args[index++]);
                max = Double.parseDouble(args[index++]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        "Invalid range for variable: " + varName, e
                );
            }

            if (ranges.containsKey(varName)) {
                throw new IllegalArgumentException(
                        "Duplicate variable definition: " + varName
                );
            }

            ranges.put(varName, new VariableRange(min, max));
        }

        return new IntegrationInput(
                expression,
                sampleCount,
                samplingType,
                ranges
        );
    }
}
