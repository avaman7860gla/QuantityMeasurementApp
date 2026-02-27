package com.example.app;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable>
    void demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
        System.out.println(q1 + " equals " + q2 + " → " + q1.equals(q2));
    }

    public static <U extends IMeasurable>
    void demonstrateConversion(Quantity<U> q, U target) {
        System.out.println(q + " convertTo " + target.getUnitName()
                + " → " + q.convertTo(target));
    }

    public static <U extends IMeasurable>
    void demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U target) {
        System.out.println(q1 + " + " + q2 + " → " + q1.add(q2, target));
    }

    public static <U extends IMeasurable>
    void demonstrateSubtraction(Quantity<U> q1, Quantity<U> q2, U target) {
        System.out.println(q1 + " - " + q2 + " → " + q1.subtract(q2, target));
    }

    public static <U extends IMeasurable>
    void demonstrateDivision(Quantity<U> q1, Quantity<U> q2) {
        System.out.println(q1 + " ÷ " + q2 + " → " + q1.divide(q2));
    }

    public static void main(String[] args) {

        Quantity<LengthUnit> l1 =
                new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 =
                new Quantity<>(6.0, LengthUnit.INCHES);

        demonstrateSubtraction(l1, l2, LengthUnit.FEET);
        demonstrateDivision(l1, new Quantity<>(2.0, LengthUnit.FEET));

        Quantity<VolumeUnit> v1 =
                new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 =
                new Quantity<>(2.0, VolumeUnit.LITRE);

        demonstrateSubtraction(v1, v2, VolumeUnit.LITRE);
        demonstrateDivision(v1, v2);
    }
}