package com.example.yard_equality;

public class QuantityMeasurementAppUC4 {

    public static void main(String[] args) {

        // Feet to Inch
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCH);
        System.out.println("1 Foot == 12 Inches : " + feet.equals(inch));

        // Yard to Feet
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength feet3 = new QuantityLength(3.0, LengthUnit.FEET);
        System.out.println("1 Yard == 3 Feet : " + yard.equals(feet3));

        // Yard to Inches
        QuantityLength inch36 = new QuantityLength(36.0, LengthUnit.INCH);
        System.out.println("1 Yard == 36 Inches : " + yard.equals(inch36));

        // Centimeter to Inches
        QuantityLength cm = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        QuantityLength inchValue = new QuantityLength(0.393701, LengthUnit.INCH);
        System.out.println("1 cm == 0.393701 inch : " + cm.equals(inchValue));

        // Same unit comparison
        QuantityLength yard2 = new QuantityLength(2.0, LengthUnit.YARD);
        QuantityLength yard2Copy = new QuantityLength(2.0, LengthUnit.YARD);
        System.out.println("2 Yard == 2 Yard : " + yard2.equals(yard2Copy));

        // Different values
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(2.0, LengthUnit.FEET);
        System.out.println("1 Foot == 2 Feet : " + feet1.equals(feet2));

        // Null check
        System.out.println("Compare with null : " + feet.equals(null));

    }
}

