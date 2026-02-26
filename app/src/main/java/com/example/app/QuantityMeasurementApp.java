package com.example.app;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        QuantityLength f1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength i1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength y1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength c1 = new QuantityLength(2.54, LengthUnit.CENTIMETER);
        System.out.println(f1.add(i1));
        System.out.println(i1.add(f1));
        System.out.println(y1.add(new QuantityLength(3.0, LengthUnit.FEET)));
        System.out.println(c1.add(new QuantityLength(1.0, LengthUnit.INCH)));
    }
}