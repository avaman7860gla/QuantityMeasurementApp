package com.example.measurement.controller;

import com.example.measurement.dto.QuantityDTO;
import com.example.measurement.service.IQuantityMeasurementService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/measurement/quantity")
@CrossOrigin(origins = "*")
public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    @PostMapping("/convert")
    public double convert(@RequestBody QuantityDTO dto) {
        return service.convert(dto);
    }
    
    @PostMapping("/add")
    public double add(@RequestBody QuantityDTO dto) {
        return service.add(dto);
    }

    @PostMapping("/subtract")
    public double subtract(@RequestBody QuantityDTO dto) {
        return service.subtract(dto);
    }

    @PostMapping("/divide")
    public double divide(@RequestBody QuantityDTO dto) {
        return service.divide(dto);
    }
    
    @PostMapping("/compare")
    public boolean compare(@RequestBody QuantityDTO dto) {
        return service.compare(dto);
    }
}