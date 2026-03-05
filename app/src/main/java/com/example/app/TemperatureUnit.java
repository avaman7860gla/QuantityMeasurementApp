package com.example.app;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS(c -> c,c -> c),
    FAHRENHEIT(f -> (f - 32) * 5 / 9, c -> (c * 9 / 5) + 32);

    private final Function<Double, Double> toCelsius;
    private final Function<Double, Double> fromCelsius;

    // Constructor
    TemperatureUnit(Function<Double, Double> toCelsius,Function<Double, Double> fromCelsius) {
        this.toCelsius = toCelsius;
        this.fromCelsius = fromCelsius;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return toCelsius.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return fromCelsius.apply(baseValue);
    }

    // Temperature does NOT support arithmetic
    private static final SupportsArithmetic supportsArithmetic = () -> false;

    @Override
    public boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException("Temperature does not support " + operation + " operation");
    }
}