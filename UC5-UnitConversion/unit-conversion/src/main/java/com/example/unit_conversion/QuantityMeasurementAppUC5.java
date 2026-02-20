package com.example.unit_conversion;

public class QuantityMeasurementAppUC5 {
    public static void main(String[] args) {
        System.out.println("1 ft to inches: "+QuantityLength.convert(1, LengthUnit.FEET, LengthUnit.INCH));
        System.out.println("3 yards to feet: "+QuantityLength.convert(3, LengthUnit.YARD, LengthUnit.FEET));
        System.out.println("36 inches to yard: "+QuantityLength.convert(36, LengthUnit.INCH, LengthUnit.YARD));
        System.out.println("1 cm to inch: "+QuantityLength.convert(1, LengthUnit.CENTIMETER, LengthUnit.INCH));
    }
}
