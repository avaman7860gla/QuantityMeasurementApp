package com.example.unit_conversion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthUC5Test {
    private static final double EPSILON =0.000001;
    @Test
    void testConversion_FeetToInches() {
        assertEquals(12.0,QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCH),EPSILON);
    }
    @Test
    void testConversion_InchesToFeet() {
        assertEquals(2.0,QuantityLength.convert(24.0, LengthUnit.INCH, LengthUnit.FEET),EPSILON);
    }
    @Test
    void testConversion_YardsToInches() {
    	assertEquals(36.0,QuantityLength.convert(1.0, LengthUnit.YARD, LengthUnit.INCH),EPSILON);
    }
    @Test
    void testConversion_InchesToYards() {
        assertEquals(2.0,QuantityLength.convert(72.0, LengthUnit.INCH, LengthUnit.YARD),EPSILON);
    }
    @Test
    void testConversion_CentimetersToInches() {
        assertEquals(1.0,QuantityLength.convert(2.54, LengthUnit.CENTIMETER, LengthUnit.INCH),EPSILON);
    }
    @Test
    void testConversion_FeetToYard() {
        assertEquals(2.0,QuantityLength.convert(6.0, LengthUnit.FEET, LengthUnit.YARD),EPSILON);
    }
    @Test
    void testConversion_RoundTrip_PreservesValue() {
        double original=5.5;
        double converted=QuantityLength.convert(original, LengthUnit.FEET, LengthUnit.INCH);
        double back=QuantityLength.convert(converted, LengthUnit.INCH, LengthUnit.FEET);
        assertEquals(original, back, EPSILON);
    }
    @Test
    void testConversion_ZeroValue(){
        assertEquals(0.0,QuantityLength.convert(0.0, LengthUnit.FEET, LengthUnit.INCH),EPSILON);
    }
    @Test
    void testConversion_NegativeValue() {
        assertEquals(-12.0,QuantityLength.convert(-1.0, LengthUnit.FEET, LengthUnit.INCH),EPSILON);
    }
    @Test
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () -> QuantityLength.convert(1.0, null, LengthUnit.FEET));
        assertThrows(IllegalArgumentException.class,() -> QuantityLength.convert(1.0, LengthUnit.FEET, null));
    }
    @Test
    void testConversion_NaNOrInfinite_Throws() {
        assertThrows(IllegalArgumentException.class,() -> QuantityLength.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCH));
        assertThrows(IllegalArgumentException.class,() -> QuantityLength.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCH));
        assertThrows(IllegalArgumentException.class,() -> QuantityLength.convert(Double.NEGATIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCH));
    }
    @Test
    void testConversion_PrecisionTolerance() {
        double result =QuantityLength.convert(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH);
        assertEquals(0.393701, result, 0.00001);
    }
}
