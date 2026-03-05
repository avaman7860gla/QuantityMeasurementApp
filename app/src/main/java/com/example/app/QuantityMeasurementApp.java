package com.example.app;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        Quantity<TemperatureUnit> temp1 =new Quantity<>(0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> temp2 =new Quantity<>(32, TemperatureUnit.FAHRENHEIT);
        System.out.println("Equality: " + temp1.equals(temp2));

        Quantity<TemperatureUnit> converted =temp1.convertTo(TemperatureUnit.FAHRENHEIT);
        System.out.println("0°C in Fahrenheit: " + converted);

        try {
            temp1.add(new Quantity<>(10, TemperatureUnit.CELSIUS));
        } 
        catch (UnsupportedOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}