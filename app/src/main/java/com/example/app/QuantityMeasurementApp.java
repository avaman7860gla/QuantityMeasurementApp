package com.example.app;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength q3 = new QuantityLength(36.0, LengthUnit.INCH);
        QuantityLength q4 = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        QuantityLength q5 = new QuantityLength(0.393701, LengthUnit.INCH);
        System.out.println(q1.equals(q2));
        System.out.println(q1.equals(q3));
        System.out.println(q4.equals(q5));
    }
}