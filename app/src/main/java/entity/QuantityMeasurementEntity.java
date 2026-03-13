package entity;

import dto.QuantityDTO;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private QuantityDTO operand1;
    private QuantityDTO operand2;
    private String operationType;
    private QuantityDTO result;
    private String errorMessage;



    // Constructor for conversion operation
    public QuantityMeasurementEntity(QuantityDTO operand1,
                                     String operationType,
                                     QuantityDTO result) {

        this.operand1 = operand1;
        this.operationType = operationType;
        this.result = result;
    }



    // Constructor for binary operations (ADD, SUBTRACT, COMPARE, DIVIDE)
    public QuantityMeasurementEntity(QuantityDTO operand1,
                                     QuantityDTO operand2,
                                     String operationType,
                                     QuantityDTO result) {

        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operationType = operationType;
        this.result = result;
    }



    // Constructor for error case
    public QuantityMeasurementEntity(String operationType,
                                     String errorMessage) {

        this.operationType = operationType;
        this.errorMessage = errorMessage;
    }



    public QuantityDTO getOperand1() {
        return operand1;
    }



    public QuantityDTO getOperand2() {
        return operand2;
    }



    public String getOperationType() {
        return operationType;
    }



    public QuantityDTO getResult() {
        return result;
    }



    public String getErrorMessage() {
        return errorMessage;
    }



    public boolean hasError() {
        return errorMessage != null && !errorMessage.isEmpty();
    }



    @Override
    public String toString() {

        if (hasError()) {
            return "Operation: " + operationType +
                    " | ERROR: " + errorMessage;
        }

        return "Operation: " + operationType +
                " | Operand1: " + operand1 +
                " | Operand2: " + operand2 +
                " | Result: " + result;
    }
}