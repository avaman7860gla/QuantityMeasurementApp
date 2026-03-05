package com.example.app;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {
    private static final double EPS = 1e-6;
    @Test
    void testRefactoring_Add_DelegatesViaHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.add(q2);
        assertEquals(2.0, result.getValue(), EPS);
    }

   
    @Test
    void testRefactoring_Subtract_DelegatesViaHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.subtract(q2);
        assertEquals(9.5, result.getValue(), EPS);
    }

   
    @Test
    void testRefactoring_Divide_DelegatesViaHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2, LengthUnit.FEET);
        assertEquals(5.0, q1.divide(q2), EPS);
    }

   
    @Test
    void testValidation_NullOperand_ConsistentAcrossOperations() {
        Quantity<LengthUnit> q = new Quantity<>(10, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q.add(null));
        assertThrows(IllegalArgumentException.class, () -> q.subtract(null));
        assertThrows(IllegalArgumentException.class, () -> q.divide(null));
    }

   
    @Test
    void testValidation_CrossCategory_ConsistentAcrossOperations() {
        Quantity<LengthUnit> length = new Quantity<>(10, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(5, WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> length.add((Quantity) weight));
        assertThrows(IllegalArgumentException.class, () -> length.subtract((Quantity) weight));
        assertThrows(IllegalArgumentException.class, () -> length.divide((Quantity) weight));
    }

   
    @Test
    void testValidation_FiniteValue_ConsistentAcrossOperations() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    
    @Test
    void testValidation_NullTargetUnit_AddSubtractReject() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q1.add(q2, null));
        assertThrows(IllegalArgumentException.class, () -> q1.subtract(q2, null));
    }


    @Test
    void testArithmeticOperation_Add_EnumComputation() throws Exception {
        Class<?> enumClass = Class.forName("com.example.app.Quantity$ArithmeticOperation");
        Object add = Enum.valueOf((Class) enumClass, "ADD");
        Method compute = enumClass.getDeclaredMethod("compute", double.class, double.class);
        compute.setAccessible(true);
        double result = (double) compute.invoke(add, 10.0, 5.0);
        assertEquals(15.0, result, EPS);
    }

    @Test
    void testArithmeticOperation_Subtract_EnumComputation() throws Exception {
        Class<?> enumClass = Class.forName("com.example.app.Quantity$ArithmeticOperation");
        Object sub = Enum.valueOf((Class) enumClass, "SUBTRACT");
        Method compute = enumClass.getDeclaredMethod("compute", double.class, double.class);
        compute.setAccessible(true);
        double result = (double) compute.invoke(sub, 10.0, 5.0);
        assertEquals(5.0, result, EPS);
    }

    @Test
    void testArithmeticOperation_Divide_EnumComputation() throws Exception {
        Class<?> enumClass = Class.forName("com.example.app.Quantity$ArithmeticOperation");
        Object div = Enum.valueOf((Class) enumClass, "DIVIDE");
        Method compute = enumClass.getDeclaredMethod("compute", double.class, double.class);
        compute.setAccessible(true);
        double result = (double) compute.invoke(div, 10.0, 5.0);
        assertEquals(2.0, result, EPS);
    }

    @Test
    void testArithmeticOperation_DivideByZero_EnumThrows() throws Exception {
        Class<?> enumClass = Class.forName("com.example.app.Quantity$ArithmeticOperation");
        Object div = Enum.valueOf((Class) enumClass, "DIVIDE");
        Method compute = enumClass.getDeclaredMethod("compute", double.class, double.class);
        compute.setAccessible(true);
        assertThrows(Exception.class, () -> compute.invoke(div, 10.0, 0.0));
    }

    @Test
    void testPerformBaseArithmetic_ConversionAndOperation() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.add(q2);
        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    void testAdd_UC12_BehaviorPreserved() {
        Quantity<WeightUnit> q1 = new Quantity<>(10, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(5000, WeightUnit.GRAM);
        Quantity<WeightUnit> result = q1.add(q2);
        assertEquals(15.0, result.getValue(), EPS);
    }

    @Test
    void testSubtract_UC12_BehaviorPreserved() {
        Quantity<VolumeUnit> q1 = new Quantity<>(5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(2, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = q1.subtract(q2);
        assertEquals(3.0, result.getValue(), EPS);
    }

    @Test
    void testDivide_UC12_BehaviorPreserved() {
        Quantity<VolumeUnit> q1 = new Quantity<>(10, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(5, VolumeUnit.LITRE);
        assertEquals(2.0, q1.divide(q2), EPS);
    }

    
    @Test
    void testRounding_Divide_NoRounding() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(3, LengthUnit.FEET);
        double result = q1.divide(q2);
        assertTrue(result > 3.333);
    }

    @Test
    void testImplicitTargetUnit_AddSubtract() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.add(q2);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testExplicitTargetUnit_AddSubtract_Overrides() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.add(q2, LengthUnit.INCHES);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void testImmutability_AfterAdd_ViaCentralizedHelper() {
        Quantity<LengthUnit> q = new Quantity<>(5, LengthUnit.FEET);
        q.add(new Quantity<>(5, LengthUnit.FEET));
        assertEquals(5, q.getValue(), EPS);
    }

    @Test
    void testImmutability_AfterSubtract_ViaCentralizedHelper() {
        Quantity<LengthUnit> q = new Quantity<>(5, LengthUnit.FEET);
        q.subtract(new Quantity<>(2, LengthUnit.FEET));
        assertEquals(5, q.getValue(), EPS);
    }

    @Test
    void testImmutability_AfterDivide_ViaCentralizedHelper() {
        Quantity<LengthUnit> q = new Quantity<>(10, LengthUnit.FEET);
        q.divide(new Quantity<>(2, LengthUnit.FEET));
        assertEquals(10, q.getValue(), EPS);
    }
    
    @Test
    void testAllOperations_AcrossAllCategories() {
        Quantity<LengthUnit> l = new Quantity<>(10, LengthUnit.FEET);
        Quantity<WeightUnit> w = new Quantity<>(10, WeightUnit.KILOGRAM);
        Quantity<VolumeUnit> v = new Quantity<>(10, VolumeUnit.LITRE);
        assertEquals(5, l.divide(new Quantity<>(2, LengthUnit.FEET)), EPS);
        assertEquals(2, w.divide(new Quantity<>(5, WeightUnit.KILOGRAM)), EPS);
        assertEquals(2, v.divide(new Quantity<>(5, VolumeUnit.LITRE)), EPS);
    }

    @Test
    void testHelper_PrivateVisibility() throws Exception {
        Method m = Quantity.class.getDeclaredMethod(
                "performBaseArithmetic",
                Quantity.class,
                Class.forName("com.example.app.Quantity$ArithmeticOperation"));
        assertTrue(Modifier.isPrivate(m.getModifiers()));
    }

    @Test
    void testValidation_Helper_PrivateVisibility() throws Exception {
        Method m = Quantity.class.getDeclaredMethod(
                "validateArithmeticOperands",
                Quantity.class,
                Object.class,
                boolean.class);
        assertTrue(Modifier.isPrivate(m.getModifiers()));
    }

    @Test
    void testRounding_Helper_Accuracy() {
        double rounded = Math.round(1.234567 * 100.0) / 100.0;
        assertEquals(1.23, rounded, EPS);
    }

    @Test
    void testArithmetic_Chain_Operations() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2, LengthUnit.FEET);
        Quantity<LengthUnit> q3 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q4 = new Quantity<>(1, LengthUnit.FEET);
        double result = q1.add(q2).subtract(q3).divide(q4);
        assertEquals(11.0, result, EPS);
    }

    @Test
    void testRefactoring_NoBehaviorChange_LargeDataset() {
        for (int i = 1; i <= 1000; i++) {
            Quantity<LengthUnit> q1 = new Quantity<>(i, LengthUnit.FEET);
            Quantity<LengthUnit> q2 = new Quantity<>(1, LengthUnit.FEET);
            assertEquals(i + 1, q1.add(q2).getValue(), EPS);
        }
    }

    @Test
    void testEnumConstant_ADD_CorrectlyAdds() {
        assertEquals(10.0, 7 + 3, EPS);
    }

    @Test
    void testEnumConstant_SUBTRACT_CorrectlySubtracts() {
        assertEquals(4.0, 7 - 3, EPS);
    }

    
    @Test
    void testEnumConstant_DIVIDE_CorrectlyDivides() {
        assertEquals(3.5, 7.0 / 2.0, EPS);
    }

    
    @Test
    void testHelper_BaseUnitConversion_Correct() {
        Quantity<LengthUnit> q1 = new Quantity<>(12, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.convertTo(LengthUnit.FEET);
        assertEquals(1.0, result.getValue(), EPS);
    }

    
    @Test
    void testHelper_ResultConversion_Correct() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> result = q1.convertTo(LengthUnit.INCHES);
        assertEquals(12.0, result.getValue(), EPS);
    }

   
    @Test
    void testRefactoring_Validation_UnifiedBehavior() {
        Quantity<LengthUnit> q = new Quantity<>(10, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q.add(null));
    }

    
    @Test
    void testAddition_CorrectAcrossUnits() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);
        assertEquals(2.0, q1.add(q2).getValue(), EPS);
    }

   
    @Test
    void testSubtraction_CorrectAcrossUnits() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(120, LengthUnit.INCHES);
        assertEquals(0.0, q1.subtract(q2).getValue(), EPS);
    }

    
    @Test
    void testDivision_CorrectAcrossUnits() {
        Quantity<LengthUnit> q1 = new Quantity<>(24, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(2, LengthUnit.FEET);
        assertEquals(1.0, q1.divide(q2), EPS);
    }

   
    @Test
    void testAddition_ExplicitTargetUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.add(q2, LengthUnit.INCHES);
        assertEquals(24.0, result.getValue(), EPS);
    }

    
    @Test
    void testSubtract_NegativeResult() {
        Quantity<LengthUnit> q1 = new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10, LengthUnit.FEET);
        assertEquals(-5.0, q1.subtract(q2).getValue(), EPS);
    }

    
    @Test
    void testDivide_ByZeroThrows() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0, LengthUnit.FEET);
        assertThrows(ArithmeticException.class, () -> q1.divide(q2));
    }

}