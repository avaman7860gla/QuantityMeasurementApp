package com.example.app;

import java.util.Objects;

public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    // Constructor
    public QuantityLength(double value, LengthUnit unit) {
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    // Getters
    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    private double toBaseUnit() {
        return unit.toFeet(value);
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {
        double converted = convert(this.value, this.unit, targetUnit);
        return new QuantityLength(converted, targetUnit);
    }

    public static double convert(double value,
                                 LengthUnit sourceUnit,
                                 LengthUnit targetUnit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        if (sourceUnit == null || targetUnit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        double valueInFeet = sourceUnit.toFeet(value);
        return valueInFeet / targetUnit.getFactor();
    }

    // Override methods
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuantityLength other = (QuantityLength) obj;
        return Double.compare(this.toBaseUnit(),
                              other.toBaseUnit()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toBaseUnit());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}