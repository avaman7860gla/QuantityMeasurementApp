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

    private static double addBase(double aFeet, double bFeet) {
        return aFeet + bFeet;
    }

    public QuantityLength add(QuantityLength other) {
        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        double sumFeet = addBase(this.toBaseUnit(), other.toBaseUnit());
        double result = sumFeet / this.unit.getFactor();
        return new QuantityLength(result, this.unit);
    }

    public static QuantityLength add(QuantityLength a,QuantityLength b, LengthUnit targetUnit) {

        if (a == null || b == null)
            throw new IllegalArgumentException("Operands cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double sumFeet = addBase(a.toBaseUnit(), b.toBaseUnit());
        double result = sumFeet / targetUnit.getFactor();

        return new QuantityLength(result, targetUnit);
    }

    public static QuantityLength add(double v1,LengthUnit u1,double v2,LengthUnit u2,LengthUnit targetUnit) {
        return add(new QuantityLength(v1, u1),new QuantityLength(v2, u2),targetUnit);
    }

    public static double convert(double value,LengthUnit source,LengthUnit target) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        if (source == null || target == null)
            throw new IllegalArgumentException("Unit cannot be null");

        double valueInFeet = source.toFeet(value);
        return valueInFeet / target.getFactor();
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