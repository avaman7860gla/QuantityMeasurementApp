package com.example.app;

public class QuantityMeasurementApp {

    public static double demonstrateLengthConversion(
            double value,
            LengthUnit from,
            LengthUnit to) {

        return QuantityLength.convert(value, from, to);
    }

    public static double demonstrateLengthConversion(
            QuantityLength quantity,
            LengthUnit to) {

        return quantity.convertTo(to).getValue();
    }

    public static void main(String[] args) {

        System.out.println(
                demonstrateLengthConversion(1.0,
                        LengthUnit.FEET,
                        LengthUnit.INCH));

        System.out.println(
                demonstrateLengthConversion(3.0,
                        LengthUnit.YARD,
                        LengthUnit.FEET));

        System.out.println(
                demonstrateLengthConversion(36.0,
                        LengthUnit.INCH,
                        LengthUnit.YARD));

        System.out.println(
                demonstrateLengthConversion(1.0,
                        LengthUnit.CENTIMETER,
                        LengthUnit.INCH));
    }
}