package com.example.app;

public class QuantityMeasurementApp {

    public static void main(String[] args) {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);
        System.out.println(QuantityLength.add(a, b, LengthUnit.FEET));
        System.out.println(QuantityLength.add(a, b, LengthUnit.INCH));
        System.out.println(QuantityLength.add(a, b, LengthUnit.YARD));
        System.out.println(QuantityLength.add(a, b, LengthUnit.CENTIMETER));
    }
}