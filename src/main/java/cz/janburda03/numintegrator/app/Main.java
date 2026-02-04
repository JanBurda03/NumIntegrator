package cz.janburda03.numintegrator.app;

import cz.janburda03.numintegrator.parsing.input.IntegrationInputParser;

public class Main {
    public static void main(String[] args)
    {
        System.out.println(integrate(args));
    }

    public static double integrate(String[] args)
    {
        var input = IntegrationInputParser.parse(args);
        return Integrator.integrate(input);
    }
}
