package controller;

import dto.QuantityDTO;
import service.IQuantityMeasurementService;

public class QuantityMeasurementController {
    private final IQuantityMeasurementService service;

    // Constructor
    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public void performComparison(QuantityDTO q1, QuantityDTO q2) {
        boolean result = service.compare(q1, q2);
        System.out.println("Comparison Result : " + result);
    }

    public void performConversion(QuantityDTO q, String targetUnit) {
        QuantityDTO result = service.convert(q, targetUnit);
        System.out.println("Converted Value : " +result.getValue() + " " + result.getUnit());
    }

    public void performAddition(QuantityDTO q1, QuantityDTO q2) {
        QuantityDTO result = service.add(q1, q2);
        System.out.println("Addition Result : " +result.getValue() + " " + result.getUnit());
    }

    public void performSubtraction(QuantityDTO q1, QuantityDTO q2) {
        QuantityDTO result = service.subtract(q1, q2);
        System.out.println("Subtraction Result : " +result.getValue() + " " + result.getUnit());
    }

    public void performDivision(QuantityDTO q1, QuantityDTO q2) {
        double result = service.divide(q1, q2);
        System.out.println("Division Result : " + result);
    }
}
