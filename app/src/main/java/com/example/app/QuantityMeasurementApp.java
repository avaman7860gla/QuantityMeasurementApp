package com.example.app;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable>
    void demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
        System.out.println(q1 + " equals " + q2 + " -> " + q1.equals(q2));
    }

    public static <U extends IMeasurable>
    void demonstrateConversion(Quantity<U> q, U targetUnit) {
        System.out.println(q + " convertTo " + targetUnit.getUnitName()
                + " -> " + q.convertTo(targetUnit));
    }

    public static <U extends IMeasurable>
    void demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        System.out.println(q1 + " add " + q2 + " -> "
                + q1.add(q2, targetUnit));
    }

    public static void main(String[] args) {
        Quantity<LengthUnit> l1 =new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 =new Quantity<>(12.0, LengthUnit.INCHES);
        demonstrateEquality(l1, l2);

        Quantity<WeightUnit> w1 =new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 =new Quantity<>(1000.0, WeightUnit.GRAM);
        demonstrateAddition(w1, w2, WeightUnit.KILOGRAM);

        Quantity<VolumeUnit> v1 =new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 =new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v3 =new Quantity<>(1.0, VolumeUnit.GALLON);
        demonstrateEquality(v1, v2);
        demonstrateConversion(v1, VolumeUnit.GALLON);
        demonstrateAddition(v1, v3, VolumeUnit.LITRE);
    }
}