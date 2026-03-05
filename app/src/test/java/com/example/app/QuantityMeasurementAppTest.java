package com.example.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {
    private static final double EPS = 0.01;

    @Test
    void testTemperatureEquality_CelsiusToCelsius_SameValue() {
        assertTrue(new Quantity<>(0.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(0.0, TemperatureUnit.CELSIUS)));
    }

    @Test
    void testTemperatureEquality_FahrenheitToFahrenheit_SameValue() {
        assertTrue(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)
                .equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)));
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_0Celsius32Fahrenheit() {
        assertTrue(new Quantity<>(0.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)));
    }
    
    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_100Celsius212Fahrenheit() {
        assertTrue(new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT)));
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_Negative40Equal() {
        assertTrue(new Quantity<>(-40.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT)));
    }

    @Test
    void testTemperatureEquality_SymmetricProperty() {
        Quantity<TemperatureUnit> a = new Quantity<>(0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> b = new Quantity<>(32, TemperatureUnit.FAHRENHEIT);
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }

    @Test
    void testTemperatureEquality_ReflexiveProperty() {
        Quantity<TemperatureUnit> t = new Quantity<>(10, TemperatureUnit.CELSIUS);
        assertTrue(t.equals(t));
    }

    @Test
    void testTemperatureConversion_CelsiusToFahrenheit_VariousValues() {
        assertEquals(122,new Quantity<>(50, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT).getValue(), EPS);

        assertEquals(-4,new Quantity<>(-20, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT).getValue(), EPS);
    }

   
    @Test
    void testTemperatureConversion_FahrenheitToCelsius_VariousValues() {
        assertEquals(0,new Quantity<>(32, TemperatureUnit.FAHRENHEIT)
                        .convertTo(TemperatureUnit.CELSIUS).getValue(), EPS);
    }

    
    @Test
    void testTemperatureConversion_RoundTrip_PreservesValue() {
        Quantity<TemperatureUnit> original =new Quantity<>(25, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> converted =original.convertTo(TemperatureUnit.FAHRENHEIT)
                        .convertTo(TemperatureUnit.CELSIUS);
        assertEquals(original.getValue(), converted.getValue(), EPS);
    }

   
    @Test
    void testTemperatureConversion_SameUnit() {
        Quantity<TemperatureUnit> t =new Quantity<>(10, TemperatureUnit.CELSIUS);
        assertEquals(10, t.convertTo(TemperatureUnit.CELSIUS).getValue(), EPS);
    }

   
    @Test
    void testTemperatureConversion_ZeroValue() {
        assertEquals(32,new Quantity<>(0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT).getValue(), EPS);
    }

    
    @Test
    void testTemperatureConversion_NegativeValues() {
        assertEquals(-40,new Quantity<>(-40, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT).getValue(), EPS);
    }

     
    @Test
    void testTemperatureConversion_LargeValues() {
        assertEquals(1832,
                new Quantity<>(1000, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT).getValue(), EPS);
    }

     
    @Test
    void testTemperatureUnsupportedOperation_Add() {
        assertThrows(UnsupportedOperationException.class,
                () -> new Quantity<>(100, TemperatureUnit.CELSIUS)
                        .add(new Quantity<>(50, TemperatureUnit.CELSIUS)));
    }

     
    @Test
    void testTemperatureUnsupportedOperation_Subtract() {
        assertThrows(UnsupportedOperationException.class,
                () -> new Quantity<>(100, TemperatureUnit.CELSIUS)
                        .subtract(new Quantity<>(50, TemperatureUnit.CELSIUS)));
    }

     
    @Test
    void testTemperatureUnsupportedOperation_Divide() {
        assertThrows(UnsupportedOperationException.class,
                () -> new Quantity<>(100, TemperatureUnit.CELSIUS)
                        .divide(new Quantity<>(50, TemperatureUnit.CELSIUS)));
    }

     
    @Test
    void testTemperatureUnsupportedOperation_ErrorMessage() {
        Exception e = assertThrows(UnsupportedOperationException.class,
                () -> new Quantity<>(100, TemperatureUnit.CELSIUS)
                        .add(new Quantity<>(50, TemperatureUnit.CELSIUS)));
        assertTrue(e.getMessage().contains("Temperature"));
    }
 
    @Test
    void testTemperatureVsLengthIncompatibility() {
        assertFalse(new Quantity<>(100, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(100, LengthUnit.FEET)));
    }

    
    @Test
    void testTemperatureVsWeightIncompatibility() {
        assertFalse(new Quantity<>(50, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(50, WeightUnit.KILOGRAM)));
    }

     
    @Test
    void testTemperatureVsVolumeIncompatibility() {
        assertFalse(new Quantity<>(25, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(25, VolumeUnit.LITRE)));
    }

     
    @Test
    void testOperationSupportMethods_TemperatureUnitAddition() {
        assertFalse(TemperatureUnit.CELSIUS.supportsArithmetic());
    }

     
    @Test
    void testOperationSupportMethods_TemperatureUnitDivision() {
        assertFalse(TemperatureUnit.FAHRENHEIT.supportsArithmetic());
    }

     
    @Test
    void testOperationSupportMethods_LengthUnitAddition() {
        assertTrue(LengthUnit.FEET.supportsArithmetic());
    }

    
    @Test
    void testOperationSupportMethods_WeightUnitDivision() {
        assertTrue(WeightUnit.KILOGRAM.supportsArithmetic());
    }

     
    @Test
    void testIMeasurableInterface_Evolution_BackwardCompatible() {
        assertEquals(12,new Quantity<>(1, LengthUnit.FEET)
                        .convertTo(LengthUnit.INCHES).getValue(), EPS);
    }

     
    @Test
    void testTemperatureUnit_NonLinearConversion() {
        double result =new Quantity<>(100, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT).getValue();
        assertEquals(212, result, EPS);
    }

    
    @Test
    void testTemperatureUnit_AllConstants() {
        assertNotNull(TemperatureUnit.CELSIUS);
        assertNotNull(TemperatureUnit.FAHRENHEIT);
    }

     
    @Test
    void testTemperatureUnit_NameMethod() {
        assertEquals("CELSIUS", TemperatureUnit.CELSIUS.getUnitName());
    }
 
    @Test
    void testTemperatureUnit_ConversionFactor() {
        assertEquals(0,TemperatureUnit.CELSIUS.convertToBaseUnit(0), EPS);
    }

    @Test
    void testTemperatureNullUnitValidation() {
        assertThrows(IllegalArgumentException.class,() -> new Quantity<>(100, null));
    }

    @Test
    void testTemperatureNullOperandValidation_InComparison() {
        assertFalse(new Quantity<>(10, TemperatureUnit.CELSIUS).equals(null));
    }

    @Test
    void testTemperatureDifferentValuesInequality() {
        assertFalse(new Quantity<>(50, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(100, TemperatureUnit.CELSIUS)));
    }

    @Test
    void testTemperatureConversionPrecision_Epsilon() {
        double value =new Quantity<>(0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT).getValue();
        assertEquals(32, value, EPS);
    }

    @Test
    void testTemperatureConversionEdgeCase_VerySmallDifference() {
        double value =new Quantity<>(0.001, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT).getValue();
        assertNotEquals(32, value);
    }

    @Test
    void testTemperatureEnumImplementsIMeasurable() {
        assertTrue(IMeasurable.class.isAssignableFrom(TemperatureUnit.class));
    }

  
    @Test
    void testTemperatureDefaultMethodInheritance() {
        assertTrue(LengthUnit.FEET.supportsArithmetic());
    }

    
    @Test
    void testTemperatureCrossUnitAdditionAttempt() {
        assertThrows(UnsupportedOperationException.class,
                () -> new Quantity<>(100, TemperatureUnit.CELSIUS)
                        .add(new Quantity<>(122, TemperatureUnit.FAHRENHEIT)));
    }

     
    @Test
    void testTemperatureValidateOperationSupport_MethodBehavior() {
        assertThrows(UnsupportedOperationException.class,
                () -> TemperatureUnit.CELSIUS.validateOperationSupport("addition"));
    }

   
    @Test
    void testTemperatureIntegrationWithGenericQuantity() {
        Quantity<TemperatureUnit> q =new Quantity<>(25, TemperatureUnit.CELSIUS);
        assertEquals(77,q.convertTo(TemperatureUnit.FAHRENHEIT).getValue(), EPS);
    }
}