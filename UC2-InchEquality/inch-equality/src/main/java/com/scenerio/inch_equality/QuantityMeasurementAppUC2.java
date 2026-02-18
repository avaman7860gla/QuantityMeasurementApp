package com.scenerio.inch_equality;

public class QuantityMeasurementAppUC2 {
	// Feet static class
    static class Feet {
        private final double value;

        // Constructor
        public Feet(double value) {
            this.value=value;
        }

        // Override equals method
        @Override
        public boolean equals(Object obj) {
            if (this==obj) {
            	 return true;
            }
            if (obj==null || getClass()!=obj.getClass()) {
            	return false;
            }
            Feet other=(Feet)obj;
            return Double.compare(this.value, other.value)==0;
        }
    }

    // Inches static class
    static class Inches {
        private final double value;

        // Constructor
        public Inches(double value) {
            this.value = value;
        }

        // Override equals method
        @Override
        public boolean equals(Object obj) {
            if (this==obj) {
            	return true;
            }
            if (obj==null || getClass()!=obj.getClass()) {
            	return false;
            }
            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value)==0;
        }
    }

    // Static comparison methods
    public static boolean checkFeetEquality(double a, double b) {
        return new Feet(a).equals(new Feet(b));
    }

    public static boolean checkInchEquality(double a, double b) {
        return new Inches(a).equals(new Inches(b));
    }

    public static void main(String[] args) {
        System.out.println("Feet Equal: " +checkFeetEquality(1.0, 1.0));
        System.out.println("Inch Equal: " +checkInchEquality(1.0, 1.0));
    }
}
