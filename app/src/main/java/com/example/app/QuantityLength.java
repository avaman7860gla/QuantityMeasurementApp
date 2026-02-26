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

    // Convert to base unit
    private double toBaseUnit() {
        return unit.toFeet(value);
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {
        double converted = convert(this.value, this.unit, targetUnit);
        return new QuantityLength(converted, targetUnit);
    }

    public QuantityLength add(QuantityLength other) {
        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        double sumInFeet = this.toBaseUnit() + other.toBaseUnit();
        double resultValue = sumInFeet / this.unit.getFactor();
        return new QuantityLength(resultValue, this.unit);
    }

    public static QuantityLength add(QuantityLength a,QuantityLength b,LengthUnit targetUnit) {

        if (a == null || b == null)
            throw new IllegalArgumentException("Operands cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double sumInFeet = a.toBaseUnit() + b.toBaseUnit();
        double resultValue = sumInFeet / targetUnit.getFactor();
        return new QuantityLength(resultValue, targetUnit);
    }

    public static QuantityLength add(double v1,LengthUnit u1,double v2,LengthUnit u2,LengthUnit targetUnit) {

        return add(new QuantityLength(v1, u1),new QuantityLength(v2, u2),targetUnit);
    }

    public static double convert(double value,LengthUnit sourceUnit,LengthUnit targetUnit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        if (sourceUnit == null || targetUnit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        double valueInFeet = sourceUnit.toFeet(value);
        return valueInFeet / targetUnit.getFactor();
    }

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