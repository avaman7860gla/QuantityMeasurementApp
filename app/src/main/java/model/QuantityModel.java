package model;

import com.example.app.IMeasurable;

public class QuantityModel<U extends IMeasurable> {
    private double value;
    private U unit;

    // Constructor
    public QuantityModel(double value, U unit) {
        this.value = value;
        this.unit = unit;
    }

    // Getters
    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }
}
