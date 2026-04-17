package com.example.measurement.measurement;

import java.util.function.DoubleBinaryOperator;

public final class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    // Constructor
    public Quantity(double value, U unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");
        this.value = value;
        this.unit = unit;
    }

    // Getters
    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
        if (!unit.getClass().equals(targetUnit.getClass()))
            throw new IllegalArgumentException("Incompatible unit categories");
        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);
        return new Quantity<>(converted, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double converted = targetUnit.convertFromBaseUnit(baseResult);
        // rounding fix
        converted = roundToTwoDecimals(converted);
        return new Quantity<>(converted, targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double converted = targetUnit.convertFromBaseUnit(baseResult);
        // rounding fix
        converted = roundToTwoDecimals(converted);
        return new Quantity<>(converted, targetUnit);
    }

    public double divide(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    private void validateArithmeticOperands(Quantity<U> other, Object targetUnit, boolean targetRequired) {
        if (other == null)
            throw new IllegalArgumentException("Operand cannot be null");
        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Incompatible measurement categories");
        if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Values must be finite");
        if (targetRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
        if (targetUnit != null) {
            U castedUnit = (U) targetUnit;
            if (!this.unit.getClass().equals(castedUnit.getClass()))
                throw new IllegalArgumentException("Target unit incompatible with quantity category");
        }
    }

    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        this.unit.validateOperationSupport(operation.name());
        other.unit.validateOperationSupport(operation.name());
        double baseThis = unit.convertToBaseUnit(this.value);
        double baseOther = other.unit.convertToBaseUnit(other.value);
        return operation.compute(baseThis, baseOther);
    }

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0)
                throw new ArithmeticException("Division by zero");
            return a / b;
        });
        private final DoubleBinaryOperator operator;
        
        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        double compute(double a, double b) {
            return operator.applyAsDouble(a, b);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Quantity<?>))
            return false;
        Quantity<?> other = (Quantity<?>) obj;
        if (!this.unit.getClass().equals(other.unit.getClass()))
            return false;
        double baseThis = unit.convertToBaseUnit(this.value);
        double baseOther = ((IMeasurable) other.unit).convertToBaseUnit(other.value);
        return Math.abs(baseThis - baseOther) < 0.0001;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}
