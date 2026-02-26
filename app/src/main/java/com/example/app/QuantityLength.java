package com.example.app;

import java.util.Objects;

public class QuantityLength {

    private static final double EPSILON = 1e-6;

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
        return unit.convertToBaseUnit(value);
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityLength(converted, targetUnit);
    }

    public QuantityLength add(QuantityLength other) {

        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        double result = this.unit.convertFromBaseUnit(sumBase);

        return new QuantityLength(result, this.unit);
    }

    public static QuantityLength add(QuantityLength a,QuantityLength b,LengthUnit targetUnit) {
        if (a == null || b == null)
            throw new IllegalArgumentException("Operands cannot be null");
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
        double sumBase =a.unit.convertToBaseUnit(a.value) +b.unit.convertToBaseUnit(b.value);
        double result =targetUnit.convertFromBaseUnit(sumBase);
        return new QuantityLength(result, targetUnit);
    }

    // Override methods
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityLength other = (QuantityLength) obj;

        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(toBaseUnit() / EPSILON));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}