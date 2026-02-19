package com.scenerio.generic_length;

public class QuantityMeasurementAppUC3 {

    public static void main(String[] args) {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCH);
        System.out.println("1 ft is equal to 12 inch ? " + feet.equals(inch));

        QuantityLength inch1 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength inch2 = new QuantityLength(1.0, LengthUnit.INCH);
        System.out.println("1 inch is equal to 1 inch ? " + inch1.equals(inch2));
    }
}

