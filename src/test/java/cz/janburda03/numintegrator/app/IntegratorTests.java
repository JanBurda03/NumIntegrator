package cz.janburda03.numintegrator.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegratorTests
{
    private static final double EPSILON = 1e-6;

    @Test
    void integratesUnitSemicircle() {
        // given
        String[] args = {
                "sqrt(1 - x^2)",
                "10000000",
                "UNIFORM",
                "x", "-1", "1"
        };

        double result = Main.integrate(args);

        double expected = Math.PI / 2.0;

        assertEquals(expected, result, EPSILON);
    }



}
