package com.example.app;

public class QuantityMeasurementApp {

    public static void main(String[] args) {
    	// Length operations
        Quantity<LengthUnit> length1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(6.0, LengthUnit.INCHES);

        System.out.println("Length Operations:");

        Quantity<LengthUnit> lengthAdd = length1.add(length2);
        System.out.println("Addition: " + lengthAdd);

        Quantity<LengthUnit> lengthSub = length1.subtract(length2);
        System.out.println("Subtraction: " + lengthSub);

        double lengthDiv = length1.divide(new Quantity<>(2.0, LengthUnit.FEET));
        System.out.println("Division: " + lengthDiv);


        // Weight operations
        Quantity<WeightUnit> weight1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weight2 = new Quantity<>(5000.0, WeightUnit.GRAM);

        System.out.println("\nWeight Operations:");

        Quantity<WeightUnit> weightAdd = weight1.add(weight2);
        System.out.println("Addition: " + weightAdd);

        Quantity<WeightUnit> weightSub = weight1.subtract(weight2);
        System.out.println("Subtraction: " + weightSub);

        double weightDiv = weight1.divide(new Quantity<>(5.0, WeightUnit.KILOGRAM));
        System.out.println("Division: " + weightDiv);


        // Volume operations
        Quantity<VolumeUnit> volume1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        System.out.println("\nVolume Operations:");

        Quantity<VolumeUnit> volumeAdd = volume1.add(volume2);
        System.out.println("Addition: " + volumeAdd);

        Quantity<VolumeUnit> volumeSub = volume1.subtract(volume2);
        System.out.println("Subtraction: " + volumeSub);

        double volumeDiv = volume1.divide(new Quantity<>(2.0, VolumeUnit.LITRE));
        System.out.println("Division: " + volumeDiv);


        // Explicit target unit
        System.out.println("\nExplicit Target Unit Example:");

        Quantity<LengthUnit> result =length1.subtract(length2, LengthUnit.INCHES);
        System.out.println("10 FEET - 6 INCHES = " + result);


        // Chain operations
        System.out.println("\nChained Operations:");
        double chainResult =length1.add(length2).subtract(new Quantity<>(1.0, LengthUnit.FEET))
                       .divide(new Quantity<>(2.0, LengthUnit.FEET));

        System.out.println("Chain Result: " + chainResult);
    }
}