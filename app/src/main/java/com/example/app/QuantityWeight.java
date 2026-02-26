package com.example.app;

import java.util.Objects;

public final class QuantityWeight {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final WeightUnit unit;

    // Constructor
    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        this.value = value;
        this.unit = unit;
    }

    // Getters
    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    public QuantityWeight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);
        return new QuantityWeight(converted, targetUnit);
    }

    public QuantityWeight add(QuantityWeight other) {
        if (other == null)
            throw new IllegalArgumentException("Other weight cannot be null");

        return add(this, other, this.unit);
    }

    public static QuantityWeight add(QuantityWeight w1,QuantityWeight w2,WeightUnit targetUnit) {
        if (w1 == null || w2 == null)
            throw new IllegalArgumentException("Operands cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base1 = w1.unit.convertToBaseUnit(w1.value);
        double base2 = w2.unit.convertToBaseUnit(w2.value);
        double sumBase = base1 + base2;
        double converted =targetUnit.convertFromBaseUnit(sumBase);
        return new QuantityWeight(converted, targetUnit);
    }

    // Override methods
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        QuantityWeight other = (QuantityWeight) obj;
        double thisBase =unit.convertToBaseUnit(value);
        double otherBase =other.unit.convertToBaseUnit(other.value);
        return Math.abs(thisBase - otherBase) < EPSILON;
    }

    @Override
    public int hashCode() {
        double baseValue =unit.convertToBaseUnit(value);
        return Objects.hash(Math.round(baseValue / EPSILON));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}