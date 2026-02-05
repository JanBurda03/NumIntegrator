package cz.janburda03.numintegrator.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegratorTests
{
    private static final double EPSILON = 1e-3;

    @Test
    void integrateGridUnitSemicircle() {
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

    @Test
    void integrateHaltonUnitSemicircle() {
        String[] args = {
                "sqrt(1 - x^2)",
                "10000000",
                "HALTON",
                "x", "-1", "1"
        };

        double result = Main.integrate(args);

        double expected = Math.PI / 2.0;

        assertEquals(expected, result, EPSILON);
    }

    @Test
    void integrateHaltonHalfCuboid() {
        String[] args = {
                "x+y",
                "10000000",
                "HALTON",
                "x", "0", "1",
                "y", "0", "1"
        };

        double result = Main.integrate(args);

        double expected = 1;

        assertEquals(expected, result, EPSILON);
    }



}
