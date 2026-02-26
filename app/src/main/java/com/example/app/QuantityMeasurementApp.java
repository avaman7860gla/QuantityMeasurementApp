package com.example.app;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.INCH);
        System.out.println(q1.equals(q2));
        System.out.println(q3.equals(new QuantityLength(1.0, LengthUnit.INCH)));
    }
}