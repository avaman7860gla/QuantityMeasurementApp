package com.example.yard_equality;

public enum LengthUnit {
    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CENTIMETER(0.0328084);

    private final double toFeetFactor;

    // Constructor
    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    // Method to convert to feet
    public double toFeet(double value) {
        return value * toFeetFactor;
    }
}

