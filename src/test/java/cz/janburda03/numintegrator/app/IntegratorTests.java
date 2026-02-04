package cz.janburda03.numintegrator.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegratorTests
{
    private static final double EPSILON = 1e-3;

    @Test
    void integrateGridUnitSemicircle() {
        // given
        String[] args = {
                "sqrt(1 - x^2)",
                "10000000",
                "GRID",
                "x", "-1", "1"
        };

        double result = Main.integrate(args);

        double expected = Math.PI / 2.0;

        assertEquals(expected, result, EPSILON);
    }

    @Test
    void integrateGridHalfCuboid() {
        // given
        String[] args = {
                "x+y",
                "10000000",
                "GRID",
                "x", "0", "1",
                "y", "0", "1"
        };

        double result = Main.integrate(args);

        double expected = 1;

        assertEquals(expected, result, EPSILON);
    }

    @Test
    void integrateRandomUnitSemicircle() {
        // given
        String[] args = {
                "sqrt(1 - x^2)",
                "10000000",
                "RANDOM",
                "x", "-1", "1"
        };

        double result = Main.integrate(args);

        double expected = Math.PI / 2.0;

        assertEquals(expected, result, EPSILON);
    }

    @Test
    void integrateRandomHalfCuboid() {
        // given
        String[] args = {
                "x+y",
                "10000000",
                "RANDOM",
                "x", "0", "1",
                "y", "0", "1"
        };

        double result = Main.integrate(args);

        double expected = 1;

        assertEquals(expected, result, EPSILON);
    }



}
