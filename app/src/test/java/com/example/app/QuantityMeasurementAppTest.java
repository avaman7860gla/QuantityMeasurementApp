package com.example.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.INCH);

        assertEquals(24.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.YARD);

        assertEquals(2.0 / 3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength b = new QuantityLength(1.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.CENTIMETER);

        assertEquals(5.08, result.getValue(), 1e-4);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        QuantityLength a = new QuantityLength(2.0, LengthUnit.YARD);
        QuantityLength b = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.YARD);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        QuantityLength a = new QuantityLength(2.0, LengthUnit.YARD);
        QuantityLength b = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.FEET);

        assertEquals(9.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength r1 =
                QuantityLength.add(a, b, LengthUnit.YARD);

        QuantityLength r2 =
                QuantityLength.add(b, a, LengthUnit.YARD);

        assertEquals(r1.getValue(), r2.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {
        QuantityLength a = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(0.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.YARD);

        assertEquals(5.0 / 3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {
        QuantityLength a = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(-2.0, LengthUnit.FEET);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.INCH);

        assertEquals(36.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.add(a, b, null));
    }

    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        QuantityLength a = new QuantityLength(1000.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(500.0, LengthUnit.FEET);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.INCH);

        assertEquals(18000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        QuantityLength a = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.YARD);

        assertEquals(2.0 / 3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_AllUnitCombinations() {
        for (LengthUnit u1 : LengthUnit.values()) {
            for (LengthUnit u2 : LengthUnit.values()) {
                for (LengthUnit target : LengthUnit.values()) {

                    QuantityLength a = new QuantityLength(1.0, u1);
                    QuantityLength b = new QuantityLength(2.0, u2);

                    QuantityLength result =
                            QuantityLength.add(a, b, target);

                    double expected =
                            QuantityLength.convert(
                                    QuantityLength.convert(1.0, u1, LengthUnit.FEET)
                                            + QuantityLength.convert(2.0, u2, LengthUnit.FEET),
                                    LengthUnit.FEET,
                                    target);

                    assertEquals(expected,
                                 result.getValue(),
                                 EPSILON);
                }
            }
        }
    }

    @Test
    void testAddition_ExplicitTargetUnit_PrecisionTolerance() {
        QuantityLength a = new QuantityLength(2.54, LengthUnit.CENTIMETER);
        QuantityLength b = new QuantityLength(1.0, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.CENTIMETER);

        assertEquals(5.08, result.getValue(), 1e-4);
    }
}