package dto;

public class QuantityDTO {
    private double value;
    private String unit;
    private String measurementType;

    public QuantityDTO() {}

    // Constructor
    public QuantityDTO(double value, String unit, String measurementType) {
        this.value = value;
        this.unit = unit;
        this.measurementType = measurementType;
    }

    // Getters and Setters
    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }
}
