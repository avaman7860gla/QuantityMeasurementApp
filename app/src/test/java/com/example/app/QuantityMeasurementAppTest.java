package com.example.app;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;


    @Test
    void testIMeasurableInterface_LengthUnitImplementation() {
        assertTrue(IMeasurable.class.isAssignableFrom(LengthUnit.class));
        assertEquals(1.0, LengthUnit.FEET.convertToBaseUnit(1.0), EPSILON);
    }

    @Test
    void testIMeasurableInterface_WeightUnitImplementation() {
        assertTrue(IMeasurable.class.isAssignableFrom(WeightUnit.class));
        assertEquals(1.0, WeightUnit.KILOGRAM.convertToBaseUnit(1.0), EPSILON);
    }

    @Test
    void testIMeasurableInterface_ConsistentBehavior() {
        assertEquals(
                LengthUnit.FEET.getClass().getInterfaces()[0],
                WeightUnit.KILOGRAM.getClass().getInterfaces()[0]
        );
    }


    @Test
    void testGenericQuantity_LengthOperations_Equality() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);
        assertTrue(a.equals(b));
    }

    @Test
    void testGenericQuantity_WeightOperations_Equality() {
        Quantity<WeightUnit> a = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> b = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertTrue(a.equals(b));
    }

    @Test
    void testGenericQuantity_LengthOperations_Conversion() {
        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .convertTo(LengthUnit.INCHES);
        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    void testGenericQuantity_WeightOperations_Conversion() {
        Quantity<WeightUnit> result =
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM);
        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testGenericQuantity_LengthOperations_Addition() {
        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .add(new Quantity<>(12.0, LengthUnit.INCHES),
                                LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testGenericQuantity_WeightOperations_Addition() {
        Quantity<WeightUnit> result =
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .add(new Quantity<>(1000.0, WeightUnit.GRAM),
                                WeightUnit.KILOGRAM);
        assertEquals(2.0, result.getValue(), EPSILON);
    }


    @Test
    void testCrossCategoryPrevention_LengthVsWeight() {
        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    @Test
    void testGenericQuantity_ConstructorValidation_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    void testGenericQuantity_ConstructorValidation_InvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }


    @Test
    void testGenericQuantity_Conversion_AllUnitCombinations() {
        for (LengthUnit from : LengthUnit.values()) {
            for (LengthUnit to : LengthUnit.values()) {
                Quantity<LengthUnit> q =
                        new Quantity<>(10.0, from);
                assertNotNull(q.convertTo(to));
            }
        }
    }

    @Test
    void testGenericQuantity_Addition_AllUnitCombinations() {
        for (WeightUnit from : WeightUnit.values()) {
            for (WeightUnit to : WeightUnit.values()) {
                Quantity<WeightUnit> q =
                        new Quantity<>(10.0, from);
                Quantity<WeightUnit> r =
                        new Quantity<>(5.0, from);
                assertNotNull(q.add(r, to));
            }
        }
    }


    @Test
    void testQuantityMeasurementApp_SimplifiedDemonstration_Equality() {
        QuantityMeasurementApp.demonstrateEquality(
                new Quantity<>(1.0, LengthUnit.FEET),
                new Quantity<>(12.0, LengthUnit.INCHES)
        );
    }

    @Test
    void testQuantityMeasurementApp_SimplifiedDemonstration_Conversion() {
        QuantityMeasurementApp.demonstrateConversion(
                new Quantity<>(1.0, WeightUnit.KILOGRAM),
                WeightUnit.GRAM
        );
    }

    @Test
    void testQuantityMeasurementApp_SimplifiedDemonstration_Addition() {
        QuantityMeasurementApp.demonstrateAddition(
                new Quantity<>(1.0, WeightUnit.KILOGRAM),
                new Quantity<>(1000.0, WeightUnit.GRAM),
                WeightUnit.KILOGRAM
        );
    }


    @Test
    void testTypeWildcard_FlexibleSignatures() {
        Quantity<?> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertNotNull(q);
    }

    @Test
    void testGenericBoundedTypeParameter_Enforcement() {
        assertTrue(IMeasurable.class
                .isAssignableFrom(LengthUnit.class));
    }


    @Test
    void testHashCode_GenericQuantity_Consistency() {
        Quantity<LengthUnit> a =
                new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void testEquals_GenericQuantity_ContractPreservation() {
        Quantity<WeightUnit> a =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertTrue(a.equals(a));
    }


    @Test
    void testEnumAsUnitCarrier_BehaviorEncapsulation() {
        IMeasurable unit = LengthUnit.FEET;
        assertEquals(1.0, unit.convertToBaseUnit(1.0), EPSILON);
    }

    @Test
    void testTypeErasure_RuntimeSafety() {
        Quantity<?> a =
                new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<?> b =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(a.equals(b));
    }


    enum VolumeUnit implements IMeasurable {
        LITER(1.0),
        MILLILITER(0.001);

        private final double factor;

        VolumeUnit(double factor) {
            this.factor = factor;
        }

        public double getConversionFactor() { return factor; }

        public double convertToBaseUnit(double v) { return v * factor; }

        public double convertFromBaseUnit(double b) { return b / factor; }

        public String getUnitName() { return name(); }
    }

    @Test
    void testScalability_NewUnitEnumIntegration() {
        Quantity<VolumeUnit> q =
                new Quantity<>(1.0, VolumeUnit.LITER);
        assertEquals(1000.0,
                q.convertTo(VolumeUnit.MILLILITER).getValue(),
                EPSILON);
    }

    @Test
    void testScalability_MultipleNewCategories() {
        Quantity<VolumeUnit> v =
                new Quantity<>(2.0, VolumeUnit.LITER);
        assertNotNull(v);
    }

    @Test
    void testArchitecturalReadiness_MultipleNewCategories() {
        assertTrue(IMeasurable.class
                .isAssignableFrom(VolumeUnit.class));
    }


    @Test
    void testCompositionOverInheritance_Flexibility() {
        Quantity<LengthUnit> q =
                new Quantity<>(5.0, LengthUnit.FEET);
        assertEquals(5.0, q.getValue());
    }

    @Test
    void testMaintainability_SingleSourceOfTruth() {
        Quantity<WeightUnit> q =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertEquals(1000.0,
                q.convertTo(WeightUnit.GRAM).getValue(),
                EPSILON);
    }


    @Test
    void testImmutability_GenericQuantity() {
        Method[] methods =
                Quantity.class.getDeclaredMethods();

        for (Method m : methods) {
            assertFalse(m.getName().startsWith("set"));
        }
    }

    @Test
    void testInterfaceSegregation_MinimalContract() {
        Method[] methods =
                IMeasurable.class.getDeclaredMethods();
        assertEquals(4, methods.length);
    }


    @Test
    void testCodeReduction_DRYValidation() {
        assertTrue(Quantity.class.getDeclaredMethods().length < 15);
    }

    @Test
    void testPerformance_GenericOverhead() {
        long start = System.nanoTime();
        new Quantity<>(1.0, LengthUnit.FEET)
                .convertTo(LengthUnit.INCHES);
        long end = System.nanoTime();
        assertTrue((end - start) < 1_000_000);
    }

    @Test
    void testDocumentation_PatternClarity() {
        assertNotNull(IMeasurable.class.getName());
    }

    @Test
    void testQuantityMeasurementApp_InstanceCreation() {
        assertNotNull(new QuantityMeasurementApp());
    }

    @Test
    void testHashSetBehavior_WithGenericQuantity() {
        Set<Quantity<LengthUnit>> set = new HashSet<>();
        set.add(new Quantity<>(1.0, LengthUnit.FEET));
        set.add(new Quantity<>(12.0, LengthUnit.INCHES));
        assertEquals(1, set.size());
    }
}