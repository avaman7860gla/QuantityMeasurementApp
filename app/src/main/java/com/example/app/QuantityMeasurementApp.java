package com.example.app;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println("WEIGHT EQUALITY");
        QuantityWeight kg =new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight gram =new QuantityWeight(1000.0, WeightUnit.GRAM);
        QuantityWeight pound =new QuantityWeight(2.20462, WeightUnit.POUND);
        System.out.println("1 kg equals 1000 g: " + kg.equals(gram));
        System.out.println("1 kg equals ~2.20462 lb: " + kg.equals(pound));

        System.out.println("\nWEIGHT CONVERSION");
        System.out.println("1 kg to grams: "+ kg.convertTo(WeightUnit.GRAM));
        System.out.println("2.20462 lb to kg: "+ pound.convertTo(WeightUnit.KILOGRAM));
        
        System.out.println("\nWEIGHT ADDITION");
        QuantityWeight sum1 = kg.add(gram);
        System.out.println("1 kg + 1000 g = " + sum1);

        System.out.println("\nWEIGHT ADDITION");
        QuantityWeight sum2 =QuantityWeight.add(kg, gram, WeightUnit.GRAM);
        System.out.println("1 kg + 1000 g in grams = " + sum2);
        QuantityWeight sum3 =QuantityWeight.add(pound, kg, WeightUnit.POUND);
        System.out.println("2.20462 lb + 1 kg in pounds = " + sum3);

        System.out.println("\nLENGTH STILL WORKS");
        QuantityLength foot =new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inch =new QuantityLength(12.0, LengthUnit.INCHES);
        System.out.println("1 ft equals 12 in: " + foot.equals(inch));

        System.out.println("\nCATEGORY INCOMPATIBILITY");
        System.out.println("Weight equals Length? "+ kg.equals(foot));
    }
}