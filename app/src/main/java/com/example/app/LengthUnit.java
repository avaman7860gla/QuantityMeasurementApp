package com.example.app;

public enum LengthUnit {
    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CENTIMETER((0.393701) / 12.0);

    private final double toFeetFactor;

    // Constructor
    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    // Convert to feet
    public double toFeet(double value) {
        return value * toFeetFactor;
    }

    // Getter
    public double getFactor() {
        return toFeetFactor;
    }
}