package com.example.app;

import java.util.Objects;

public final class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // =====================================
    // EQUALITY
    // =====================================

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        // Prevent cross-category comparison
        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double baseValue1 = unit.convertToBaseUnit(value);
        double baseValue2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(baseValue1 - baseValue2) < EPSILON;
    }

    @Override
    public int hashCode() {

        // Use normalized base value for consistent hashing
        double baseValue = unit.convertToBaseUnit(value);

        // Normalize to EPSILON precision
        long normalized = Math.round(baseValue / EPSILON);

        return Objects.hash(unit.getClass(), normalized);
    }

    // =====================================
    // CONVERSION
    // =====================================

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(convertedValue, targetUnit);
    }

    // =====================================
    // ADDITION
    // =====================================

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }

        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Cannot add different measurement categories");
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue1 = unit.convertToBaseUnit(value);
        double baseValue2 = other.unit.convertToBaseUnit(other.value);

        double sumBase = baseValue1 + baseValue2;

        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Quantity<>(result, targetUnit);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}