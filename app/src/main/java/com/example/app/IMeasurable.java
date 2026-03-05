package com.example.app;

@FunctionalInterface
interface SupportsArithmetic {
    boolean isSupported();
}

public interface IMeasurable {
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    default String getUnitName() {
        return this.toString();
    }

    //Default lambda-arithmetic supported
    SupportsArithmetic supportsArithmetic = () -> true;

    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    // Default validation-do nothing
    default void validateOperationSupport(String operation) {
    }
}