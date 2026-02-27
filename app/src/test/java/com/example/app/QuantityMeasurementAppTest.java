package com.example.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;


    @Test
    void testSubtraction_SameUnit_FeetMinusFeet() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET));

        assertEquals(5.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testSubtraction_SameUnit_LitreMinusLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(10.0, VolumeUnit.LITRE)
                        .subtract(new Quantity<>(3.0, VolumeUnit.LITRE));

        assertEquals(7.0, result.getValue(), EPSILON);
    }

    @Test
    void testSubtraction_CrossUnit_FeetMinusInches() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCHES));

        assertEquals(9.5, result.getValue(), EPSILON);
    }

    @Test
    void testSubtraction_CrossUnit_InchesMinusFeet() {
        Quantity<LengthUnit> result =
                new Quantity<>(120.0, LengthUnit.INCHES)
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET));

        assertEquals(60.0, result.getValue(), EPSILON);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Inches() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.INCHES);

        assertEquals(114.0, result.getValue(), EPSILON);
    }

    @Test
    void testSubtraction_ResultingInNegative() {
        Quantity<LengthUnit> result =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(10.0, LengthUnit.FEET));

        assertEquals(-5.0, result.getValue(), EPSILON);
    }

    @Test
    void testSubtraction_ResultingInZero() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(120.0, LengthUnit.INCHES));

        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    void testSubtraction_WithZeroOperand() {
        Quantity<LengthUnit> result =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(0.0, LengthUnit.INCHES));

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testSubtraction_NullOperand() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(10.0, LengthUnit.FEET).subtract(null));
    }

    @Test
    void testSubtraction_CrossCategory() {
        Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> length.subtract((Quantity) weight));
    }

    @Test
    void testSubtraction_ChainedOperations() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(2.0, LengthUnit.FEET))
                        .subtract(new Quantity<>(1.0, LengthUnit.FEET));

        assertEquals(7.0, result.getValue(), EPSILON);
    }

    @Test
    void testSubtraction_Immutability() {
        Quantity<LengthUnit> original =
                new Quantity<>(10.0, LengthUnit.FEET);

        original.subtract(new Quantity<>(5.0, LengthUnit.FEET));

        assertEquals(10.0, original.getValue(), EPSILON);
    }


    @Test
    void testDivision_SameUnit_FeetDividedByFeet() {
        double result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(5.0, result, EPSILON);
    }

    @Test
    void testDivision_CrossUnit_FeetDividedByInches() {
        double result =
                new Quantity<>(24.0, LengthUnit.INCHES)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(1.0, result, EPSILON);
    }

    @Test
    void testDivision_RatioLessThanOne() {
        double result =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .divide(new Quantity<>(10.0, LengthUnit.FEET));

        assertEquals(0.5, result, EPSILON);
    }

    @Test
    void testDivision_ByZero() {
        assertThrows(ArithmeticException.class,
                () -> new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(0.0, LengthUnit.FEET)));
    }

    @Test
    void testDivision_NullOperand() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(10.0, LengthUnit.FEET).divide(null));
    }

    @Test
    void testDivision_CrossCategory() {
        Quantity<LengthUnit> length =
                new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> length.divide((Quantity) weight));
    }

    @Test
    void testDivision_WithLargeRatio() {
        double result =
                new Quantity<>(1e6, WeightUnit.KILOGRAM)
                        .divide(new Quantity<>(1.0, WeightUnit.KILOGRAM));

        assertEquals(1e6, result, EPSILON);
    }

    @Test
    void testDivision_WithSmallRatio() {
        double result =
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .divide(new Quantity<>(1e6, WeightUnit.KILOGRAM));

        assertEquals(1e-6, result, EPSILON);
    }

    @Test
    void testDivision_Immutability() {
        Quantity<LengthUnit> original =
                new Quantity<>(10.0, LengthUnit.FEET);

        original.divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(10.0, original.getValue(), EPSILON);
    }


    @Test
    void testSubtractionAndDivision_Integration() {
        double result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET))
                        .divide(new Quantity<>(1.0, LengthUnit.FEET));

        assertEquals(5.0, result, EPSILON);
    }

    @Test
    void testSubtractionAddition_Inverse() {
        Quantity<LengthUnit> original =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                original.add(new Quantity<>(5.0, LengthUnit.FEET))
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET));

        assertEquals(original.getValue(), result.getValue(), EPSILON);
    }
}