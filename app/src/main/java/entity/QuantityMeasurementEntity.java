package entity;

import java.io.Serializable;
import dto.QuantityDTO;

public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private QuantityDTO operand1;
    private QuantityDTO operand2;
    private String operation;
    private QuantityDTO result;
    private boolean error;
    private String errorMessage;

    // Constructors
    public QuantityMeasurementEntity(QuantityDTO operand1, String operation, QuantityDTO result) {
        this.operand1 = operand1;
        this.operation = operation;
        this.result = result;
    }

    public QuantityMeasurementEntity(QuantityDTO operand1, QuantityDTO operand2, String operation, QuantityDTO result) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
        this.result = result;
    }

    public QuantityMeasurementEntity(String errorMessage) {
        this.error = true;
        this.errorMessage = errorMessage;
    }

    public boolean hasError() {
        return error;
    }

    // Getters
    public String getErrorMessage() {
        return errorMessage;
    }

    public QuantityDTO getResult() {
        return result;
    }

    // Override toString method
    @Override
    public String toString() {
        if (error) {
        	return "Error: " + errorMessage;
        }
        return "Operation: " + operation +" Result: " + result.getValue() + " " + result.getUnit();
    }
}
