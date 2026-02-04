package cz.janburda03.numintegrator.sampling;

import cz.janburda03.numintegrator.parsing.input.VariableRange;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GridSamplerTests {

    private static final double EPSILON = 1e-10;

    @Test
    void gridSampler_3Variables_8Samples() {
        Map<String, VariableRange> ranges = new HashMap<>();
        ranges.put("a", new VariableRange(-1.0, 1.0));
        ranges.put("b", new VariableRange(0.0, 1.0));
        ranges.put("c", new VariableRange(1.0, 3.0));

        GridSampler sampler = new GridSampler(8, ranges);

        Map<String, Double>[] expected = new Map[] {
                map(-1.0, 0.0, 1.0),
                map( 1.0, 0.0, 1.0),

                map( -1.0, 1.0, 1.0),
                map(1.0, 1.0, 1.0),


                map( -1.0, 0.0, 3.0),
                map(1.0, 0.0, 3.0),

                map( -1.0, 1.0, 3.0),
                map( 1.0, 1.0, 3.0),
        };

        int i = 0;
        while (sampler.hasNext()) {
            Map<String, Double> sample = sampler.next();
            Map<String, Double> exp = expected[i++];

            assertEquals(exp.get("a"), sample.get("a"), EPSILON, "a mismatch");
            assertEquals(exp.get("b"), sample.get("b"), EPSILON, "b mismatch");
            assertEquals(exp.get("c"), sample.get("c"), EPSILON, "c mismatch");
        }

        assertEquals(8, i, "Number of generated samples");
    }

    private static Map<String, Double> map(double a, double b, double c) {
        Map<String, Double> m = new HashMap<>();
        m.put("a", a);
        m.put("b", b);
        m.put("c", c);
        return m;
    }

    @Test
    void gridSampler_3Variables_9Samples() {
        Map<String, VariableRange> ranges = new HashMap<>();
        ranges.put("a", new VariableRange(-1.0, 1.0));
        ranges.put("b", new VariableRange(0.0, 1.0));
        ranges.put("c", new VariableRange(1.0, 2.0));

        GridSampler sampler = new GridSampler(9, ranges);

        Map<String, Double>[] expected = new Map[] {
                map(-1.0, 0.0, 1.0),
                map( 0.0, 0.0, 1.0),
                map( 1.0, 0.0, 1.0),

                map( -1.0, 1.0, 1.0),
                map( 0.0, 1.0, 1.0),
                map( 1.0, 1.0, 1.0),

                map( -1.0, 0.0, 2.0),
                map( 0.0, 0.0, 2.0),
                map( 1.0, 0.0, 2.0),
        };

        int i = 0;
        while (sampler.hasNext()) {
            Map<String, Double> sample = sampler.next();
            Map<String, Double> exp = expected[i++];

            assertEquals(exp.get("a"), sample.get("a"), EPSILON, "a mismatch");
            assertEquals(exp.get("b"), sample.get("b"), EPSILON, "b mismatch");
            assertEquals(exp.get("c"), sample.get("c"), EPSILON, "c mismatch");
        }

        assertEquals(9, i, "Number of generated samples");
    }

}
