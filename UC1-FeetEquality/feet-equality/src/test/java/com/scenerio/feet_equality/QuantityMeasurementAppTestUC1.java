package com.scenerio.feet_equality;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTestUC1 {
	@Test
    void testEquality_SameValue() {
        QuantityMeasurementAppUC1.Feet f1=new QuantityMeasurementAppUC1.Feet(1.0);
        QuantityMeasurementAppUC1.Feet f2=new QuantityMeasurementAppUC1.Feet(1.0);
        assertTrue(f1.equals(f2));
    }

    @Test
    void testEquality_DifferentValue() {
    	QuantityMeasurementAppUC1.Feet f1=new QuantityMeasurementAppUC1.Feet(1.0);
        QuantityMeasurementAppUC1.Feet f2=new QuantityMeasurementAppUC1.Feet(2.0);
        assertFalse(f1.equals(f2));
    }

    @Test
    void testEquality_NullComparison() {
    	QuantityMeasurementAppUC1.Feet f1=new QuantityMeasurementAppUC1.Feet(1.0);
        assertFalse(f1.equals(null));
    }

    @Test
    void testEquality_NonNumericInput() {
    	QuantityMeasurementAppUC1.Feet f1=new QuantityMeasurementAppUC1.Feet(1.0);
        String str = "1.0";
        assertFalse(f1.equals(str));
    }

    @Test
    void testEquality_SameReference() {
    	QuantityMeasurementAppUC1.Feet f1=new QuantityMeasurementAppUC1.Feet(1.0);
        assertTrue(f1.equals(f1));
    }
}
