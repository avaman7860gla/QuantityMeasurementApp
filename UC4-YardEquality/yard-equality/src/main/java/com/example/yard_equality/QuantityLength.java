package com.example.yard_equality;

public class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    // Constructor
    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }

    // Method to return to base unit feet
    private double toBaseUnit() {
        return unit.toFeet(value);
    }
    
    // use Epsilon due to precision value of cm
    private static final double EPSILON = 0.0001;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof QuantityLength)) return false;
        QuantityLength other = (QuantityLength) obj;
        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

}

