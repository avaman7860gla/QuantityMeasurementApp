package com.example.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0 / 12.0,
                LengthUnit.INCHES.getConversionFactor(),
                EPSILON);
    }

    @Test
    void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0,
                LengthUnit.YARDS.getConversionFactor(),
                EPSILON);
    }

    @Test
    void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(1.0 / 30.48,
                LengthUnit.CENTIMETERS.getConversionFactor(),
                EPSILON);
    }

    @Test
    void testConvertToBaseUnit_FeetToFeet() {
        assertEquals(5.0,
                LengthUnit.FEET.convertToBaseUnit(5.0),
                EPSILON);
    }

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0,
                LengthUnit.INCHES.convertToBaseUnit(12.0),
                EPSILON);
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0,
                LengthUnit.YARDS.convertToBaseUnit(1.0),
                EPSILON);
    }

    @Test
    void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0,
                LengthUnit.CENTIMETERS.convertToBaseUnit(30.48),
                EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToFeet() {
        assertEquals(2.0,
                LengthUnit.FEET.convertFromBaseUnit(2.0),
                EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0,
                LengthUnit.INCHES.convertFromBaseUnit(1.0),
                EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0,
                LengthUnit.YARDS.convertFromBaseUnit(3.0),
                EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48,
                LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0),
                1e-4);
    }

    @Test
    void testQuantityLengthRefactored_Equality() {
        QuantityLength a =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength b =
                new QuantityLength(12.0, LengthUnit.INCHES);

        assertTrue(a.equals(b));
    }

    @Test
    void testQuantityLengthRefactored_ConvertTo() {
        QuantityLength a =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result =
                a.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void testQuantityLengthRefactored_Add() {
        QuantityLength a =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength b =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = a.add(b);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testQuantityLengthRefactored_AddWithTargetUnit() {
        QuantityLength a =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength b =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.YARDS);

        assertEquals(2.0 / 3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    void testQuantityLengthRefactored_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null));
    }

    @Test
    void testQuantityLengthRefactored_InvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void testBackwardCompatibility_UC1EqualityTests() {
        QuantityLength a =
                new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(a.equals(b));
    }

    @Test
    void testBackwardCompatibility_UC5ConversionTests() {
        QuantityLength a =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertEquals(12.0,
                a.convertTo(LengthUnit.INCHES).getValue(),
                EPSILON);
    }

    @Test
    void testBackwardCompatibility_UC6AdditionTests() {
        QuantityLength a =
                new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength b =
                new QuantityLength(2.0, LengthUnit.FEET);

        assertEquals(7.0, a.add(b).getValue(), EPSILON);
    }

    @Test
    void testBackwardCompatibility_UC7AdditionWithTargetUnitTests() {
        QuantityLength a =
                new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPSILON);
    }

    

    @Test
    void testRoundTripConversion_RefactoredDesign() {
        QuantityLength original =
                new QuantityLength(5.0, LengthUnit.FEET);

        double roundTrip =
                original.convertTo(LengthUnit.INCHES)
                        .convertTo(LengthUnit.FEET)
                        .getValue();

        assertEquals(5.0, roundTrip, EPSILON);
    }

    @Test
    void testUnitImmutability() {
        assertTrue(LengthUnit.FEET instanceof Enum);
    }
}