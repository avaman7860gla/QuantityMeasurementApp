package com.example.app;

import java.util.Objects;

public final class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

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

        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        long normalized = Math.round(base / EPSILON);
        return Objects.hash(unit.getClass(), normalized);
    }

    // =====================================
    // CONVERSION
    // =====================================

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(converted, targetUnit);
    }

    // =====================================
    // ADDITION
    // =====================================

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmetic(other, targetUnit);

        double resultBase =
                unit.convertToBaseUnit(value)
                        + other.unit.convertToBaseUnit(other.value);

        return new Quantity<>(
                targetUnit.convertFromBaseUnit(resultBase),
                targetUnit);
    }

    // =====================================
    // SUBTRACTION (UC12)
    // =====================================

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmetic(other, targetUnit);

        double resultBase =
                unit.convertToBaseUnit(value)
                        - other.unit.convertToBaseUnit(other.value);

        return new Quantity<>(
                targetUnit.convertFromBaseUnit(resultBase),
                targetUnit);
    }

    // =====================================
    // DIVISION (UC12)
    // =====================================

    public double divide(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cannot divide different categories");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        if (Math.abs(base2) < EPSILON)
            throw new ArithmeticException("Division by zero");

        return base1 / base2;
    }

    private void validateArithmetic(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different measurement categories");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}