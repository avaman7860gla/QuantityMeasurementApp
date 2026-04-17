package com.example.measurement.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.measurement.dto.QuantityDTO;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private void saveHistory(double value1, double value2, String unit, String operation, double result) {

	    try {
	        Map<String, Object> request = new HashMap<>();
	        request.put("value1", value1);
	        request.put("value2", value2);
	        request.put("unit", unit);
	        request.put("operation", operation);
	        request.put("result", result);

	        restTemplate.postForObject(
	            "http://localhost:8083/api/history/save",
	            request,
	            String.class
	        );

	    } catch (Exception e) {
	        System.out.println("History service not available");
	    }
	}

    @Override
    public double convert(QuantityDTO dto) {

        String from = dto.getFromUnit().toUpperCase();
        String to = dto.getToUnit().toUpperCase();

        double result;

        if (isTemperature(from) && isTemperature(to)) {
            result = convertTemperature(dto.getValue(), from, to);
        } else {
            double valueInBase = convertToBase(dto.getValue(), from);
            result = convertFromBase(valueInBase, to);
        }
        
        saveHistory(
        	    dto.getValue(),
        	    0,
        	    from + "->" + to,
        	    "CONVERT",
        	    result
        	);

        return result;
    }

    private boolean isTemperature(String unit) {
        unit = unit.toUpperCase();
        return unit.equals("CELSIUS") || unit.equals("FAHRENHEIT") ||
               unit.equals("KELVIN") || unit.equals("C") ||
               unit.equals("F") || unit.equals("K");
    }

    private double convertTemperature(double value, String from, String to) {

        if (from.equals("C")) from = "CELSIUS";
        if (from.equals("F")) from = "FAHRENHEIT";
        if (from.equals("K")) from = "KELVIN";

        if (to.equals("C")) to = "CELSIUS";
        if (to.equals("F")) to = "FAHRENHEIT";
        if (to.equals("K")) to = "KELVIN";

        double celsius;

        switch (from) {
            case "CELSIUS": celsius = value; break;
            case "FAHRENHEIT": celsius = (value - 32) * 5/9; break;
            case "KELVIN": celsius = value - 273.15; break;
            default: return value;
        }

        switch (to) {
            case "CELSIUS": return celsius;
            case "FAHRENHEIT": return (celsius * 9/5) + 32;
            case "KELVIN": return celsius + 273.15;
            default: return value;
        }
    }

    private double convertToBase(double value, String unit) {
        switch (unit.toUpperCase()) {
            case "MILLIMETER": return value / 1000;
            case "CENTIMETER": return value / 100;
            case "METER": return value;
            case "KILOMETER": return value * 1000;
            case "INCH": return value * 0.0254;
            case "FOOT": return value * 0.3048;
            case "YARD": return value * 0.9144;
            case "MILE": return value * 1609.34;

            case "GRAM": return value / 1000;
            case "KILOGRAM": return value;
            case "POUND": return value * 0.453592;

            case "MILLILITER": return value / 1000;
            case "LITER": return value;
            case "GALLON": return value * 3.78541;

            default: return value;
        }
    }

    private double convertFromBase(double value, String unit) {
        switch (unit.toUpperCase()) {
            case "MILLIMETER": return value * 1000;
            case "CENTIMETER": return value * 100;
            case "METER": return value;
            case "KILOMETER": return value / 1000;
            case "INCH": return value / 0.0254;
            case "FOOT": return value / 0.3048;
            case "YARD": return value / 0.9144;
            case "MILE": return value / 1609.34;

            case "GRAM": return value * 1000;
            case "KILOGRAM": return value;
            case "POUND": return value / 0.453592;

            case "MILLILITER": return value * 1000;
            case "LITER": return value;
            case "GALLON": return value / 3.78541;

            default: return value;
        }
    }

    @Override
    public double add(QuantityDTO dto) {

        double value1 = dto.getValue1();
        double value2 = dto.getValue2();

        String unit1 = dto.getUnit1().toUpperCase();
        String unit2 = dto.getUnit2().toUpperCase();

        // Convert both to base unit
        double base1 = convertToBase(value1, unit1);
        double base2 = convertToBase(value2, unit2);

        // Convert back to desired unit
        double result = convertFromBase(base1 + base2, unit1);
        
        saveHistory(
        	    dto.getValue1(),
        	    dto.getValue2(),
        	    unit1,
        	    "ADD",
        	    result
        	);
        
        return result;
    }
    
    @Override
    public double subtract(QuantityDTO dto) {

        double base1 = convertToBase(dto.getValue1(), dto.getUnit1());
        double base2 = convertToBase(dto.getValue2(), dto.getUnit2());


        double result = convertFromBase(base1 - base2, dto.getUnit1());
        
        saveHistory(
        	    dto.getValue1(),
        	    dto.getValue2(),
        	    dto.getUnit1(),
        	    "SUBTRACT",
        	    result
        	);
        
        return result;
    }
    
    @Override
    public double divide(QuantityDTO dto) {

        double base1 = convertToBase(dto.getValue1(), dto.getUnit1());
        double base2 = convertToBase(dto.getValue2(), dto.getUnit2());

        if (base2 == 0) {
            throw new RuntimeException("Division by zero");
        }

        double result = base1 / base2;
        
        saveHistory(
        	    dto.getValue1(),
        	    dto.getValue2(),
        	    dto.getUnit1(),
        	    "DIVIDE",
        	    result
        	);

        	return result;
    }

    @Override
    public boolean compare(QuantityDTO dto) {

        double base1 = convertToBase(dto.getValue1(), dto.getUnit1());
        double base2 = convertToBase(dto.getValue2(), dto.getUnit2());

        boolean isEqual = Math.abs(base1 - base2) < 0.0001;
        
        saveHistory(
        	    dto.getValue1(),
        	    dto.getValue2(),
        	    dto.getUnit1(),
        	    "COMPARE",
        	    isEqual ? 1 : 0
        	);
        
        return isEqual;
    }
}