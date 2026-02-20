package com.example.unit_conversion;

public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    // For floating point comparison
    private static final double EPSILON = 0.0001;

    // Constructor
    public QuantityLength(double value, LengthUnit unit) {
        if (!Double.isFinite(value)) {
        	throw new IllegalArgumentException("Value must be a finite number");
        }
            
        if (unit == null) {
        	throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    // Method to convert current object to target unit
    public double convertTo(LengthUnit targetUnit) {
        return convert(this.value, this.unit, targetUnit);
    }

    // Static conversion API
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (!Double.isFinite(value)) {
        	throw new IllegalArgumentException("Value must be finite");
        }
        if (source == null || target == null) {
        	throw new IllegalArgumentException("Units cannot be null");
        }

        // Normalize to base unit feet
        double valueInFeet = source.toFeet(value);

        return target.fromFeet(valueInFeet);
    }

    // helper: convert object to base unit
    private double toBaseUnit() {
        return unit.toFeet(value);
    }

    // Approximate equality for measurement comparison
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || !(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;

        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    // Recommended when equals() overridden
    @Override
    public int hashCode() {
        return Double.valueOf(toBaseUnit()).hashCode();
    }

    // Human readable output
    @Override
    public String toString() {
        return value + " " + unit.name().toLowerCase();
    }
}
