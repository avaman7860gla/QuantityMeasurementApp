package com.example.measurement.measurement;

public interface IMeasurable {
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    String getUnitName();

    default boolean supportsAddition() {
        return true;
    }

    default boolean supportsSubtraction() {
        return true;
    }

    default boolean supportsDivision() {
        return true;
    }

    default void validateOperationSupport(String operation) {
        // default allow
    }

    static IMeasurable getUnit(String measurementType, String unitName) {
        switch (measurementType.toUpperCase()) {
            case "LENGTH":
                return LengthUnit.valueOf(unitName.toUpperCase());

            case "WEIGHT":
                return WeightUnit.valueOf(unitName.toUpperCase());

            case "VOLUME":
                return VolumeUnit.valueOf(unitName.toUpperCase());

            case "TEMPERATURE":
                return TemperatureUnit.valueOf(unitName.toUpperCase());

            default:
                throw new IllegalArgumentException("Invalid measurement type");
        }
    }
}
