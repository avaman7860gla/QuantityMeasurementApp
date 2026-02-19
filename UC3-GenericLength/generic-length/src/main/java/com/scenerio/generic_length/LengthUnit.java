package com.scenerio.generic_length;

public enum LengthUnit {
    FEET(1.0),
    INCH(1.0 / 12.0);   // 12 inches=1 foot

    private final double toFeetFactor;

    // Constructor
    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    // Method to convert into feet
    public double toFeet(double value) {
        return value * toFeetFactor;
    }
}
