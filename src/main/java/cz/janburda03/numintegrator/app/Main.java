package cz.janburda03.numintegrator.app;

import cz.janburda03.numintegrator.parsing.input.IntegrationInputParser;

/**
 * Entry point for the numerical integrator application.
 *
 * <p>Parses integration parameters from command-line arguments and computes
 * the integral using the {@link Integrator}.</p>
 */
public class Main {

    /**
     * Main method executed from the command line.
     *
     * @param args command-line arguments specifying the integration input
     */
    public static void main(String[] args) {
        System.out.println(integrate(args));
    }

    /**
     * Parses the input from command-line arguments and computes the integral.
     *
     * @param args command-line arguments
     * @return approximate value of the integral
     */
    public static double integrate(String[] args) {
        var input = IntegrationInputParser.parse(args);
        return Integrator.integrate(input);
    }
}
