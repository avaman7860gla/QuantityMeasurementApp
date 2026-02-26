package com.example.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testConversion_FeetToInches() {
        double result = QuantityLength.convert(1.0,
                LengthUnit.FEET,
                LengthUnit.INCH);
        assertEquals(12.0, result, EPSILON);
    }

    @Test
    void testConversion_InchesToFeet() {
        double result = QuantityLength.convert(24.0,
                LengthUnit.INCH,
                LengthUnit.FEET);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_YardsToInches() {
        double result = QuantityLength.convert(1.0,
                LengthUnit.YARD,
                LengthUnit.INCH);
        assertEquals(36.0, result, EPSILON);
    }

    @Test
    void testConversion_InchesToYards() {
        double result = QuantityLength.convert(72.0,
                LengthUnit.INCH,
                LengthUnit.YARD);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {
        double result = QuantityLength.convert(2.54,
                LengthUnit.CENTIMETER,
                LengthUnit.INCH);
        assertEquals(1.0, result, 1e-4);
    }

    @Test
    void testConversion_FeetToYards() {
        double result = QuantityLength.convert(6.0,
                LengthUnit.FEET,
                LengthUnit.YARD);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_RoundTrip_PreservesValue() {
        double original = 5.0;
        double converted = QuantityLength.convert(original,
                LengthUnit.FEET,
                LengthUnit.INCH);

        double back = QuantityLength.convert(converted,
                LengthUnit.INCH,
                LengthUnit.FEET);

        assertEquals(original, back, EPSILON);
    }

    @Test
    void testConversion_ZeroValue() {
        double result = QuantityLength.convert(0.0,
                LengthUnit.FEET,
                LengthUnit.INCH);
        assertEquals(0.0, result, EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        double result = QuantityLength.convert(-1.0,
                LengthUnit.FEET,
                LengthUnit.INCH);
        assertEquals(-12.0, result, EPSILON);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.convert(1.0, null, LengthUnit.FEET));
    }

    @Test
    void testConversion_NaNOrInfinite_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.convert(Double.NaN,
                        LengthUnit.FEET,
                        LengthUnit.INCH));

        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.convert(Double.POSITIVE_INFINITY,
                        LengthUnit.FEET,
                        LengthUnit.INCH));
    }

    @Test
    void testConversion_PrecisionTolerance() {
        double result = QuantityLength.convert(1.0,
                LengthUnit.CENTIMETER,
                LengthUnit.FEET);

        double expected =
                (0.393701 / 12.0);

        assertEquals(expected, result, EPSILON);
    }
}