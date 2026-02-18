package com.scenerio.inch_equality;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTestUC2 {
	
	// Feet test
    @Test
    void testFeetEquality_SameValue() {
        QuantityMeasurementAppUC2.Feet f1=new QuantityMeasurementAppUC2.Feet(1.0);
        QuantityMeasurementAppUC2.Feet f2 = new QuantityMeasurementAppUC2.Feet(1.0);
        assertTrue(f1.equals(f2), "1.0 ft should equal 1.0 ft");
    }

    @Test
    void testFeetEquality_DifferentValue() {
    	QuantityMeasurementAppUC2.Feet f1 = new QuantityMeasurementAppUC2.Feet(1.0);
    	QuantityMeasurementAppUC2.Feet f2 =new QuantityMeasurementAppUC2.Feet(2.0);
        assertFalse(f1.equals(f2), "1.0 ft should not equal 2.0 ft");
    }

    @Test
    void testFeetEquality_NullComparison() {
    	QuantityMeasurementAppUC2.Feet f1 = new QuantityMeasurementAppUC2.Feet(1.0);
        assertFalse(f1.equals(null), "Feet should not equal null");
    }

    @Test
    void testFeetEquality_NonNumericInput() {
    	QuantityMeasurementAppUC2.Feet f1 =new QuantityMeasurementAppUC2.Feet(1.0);
        String text = "1.0";
        assertFalse(f1.equals(text), "Feet should not equal non-feet object");
    }

    @Test
    void testFeetEquality_SameReference() {
    	QuantityMeasurementAppUC2.Feet f1 = new QuantityMeasurementAppUC2.Feet(1.0);
        assertTrue(f1.equals(f1), "Same reference must be equal");
    }


    // Inches test
    @Test
    void testInchEquality_SameValue() {
    	QuantityMeasurementAppUC2.Inches i1 = new QuantityMeasurementAppUC2.Inches(1.0);
    	QuantityMeasurementAppUC2.Inches i2 = new QuantityMeasurementAppUC2.Inches(1.0);
        assertTrue(i1.equals(i2), "1.0 inch should equal 1.0 inch");
    }

    @Test
    void testInchEquality_DifferentValue() {
    	QuantityMeasurementAppUC2.Inches i1 = new QuantityMeasurementAppUC2.Inches(1.0);
    	QuantityMeasurementAppUC2.Inches i2 = new QuantityMeasurementAppUC2.Inches(2.0);
        assertFalse(i1.equals(i2), "1.0 inch should not equal 2.0 inch");
    }

    @Test
    void testInchEquality_NullComparison() {
    	QuantityMeasurementAppUC2.Inches i1 =new QuantityMeasurementAppUC2.Inches(1.0);
        assertFalse(i1.equals(null), "Inches should not equal null");
    }

    @Test
    void testInchEquality_NonNumericInput() {
    	QuantityMeasurementAppUC2.Inches i1 = new QuantityMeasurementAppUC2.Inches(1.0);
        Integer number = 1;
        assertFalse(i1.equals(number), "Inches should not equal non-inch object");
    }

    @Test
    void testInchEquality_SameReference() {
    	QuantityMeasurementAppUC2.Inches i1= new QuantityMeasurementAppUC2.Inches(1.0);
        assertTrue(i1.equals(i1), "Same reference must be equal");
    }
}
