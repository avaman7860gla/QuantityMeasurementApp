package com.example.app;

public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactor; 

    // Constructor
    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    // Getter
    public double getConversionFactor() {
        return conversionFactor;
    }

    // Conversions
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}