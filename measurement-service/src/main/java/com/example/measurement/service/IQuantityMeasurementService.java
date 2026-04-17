package com.example.measurement.service;

import com.example.measurement.dto.QuantityDTO;

import jakarta.validation.Valid;

public interface IQuantityMeasurementService {

    double convert(@Valid QuantityDTO dto);

    double add(@Valid QuantityDTO dto);

    boolean compare(@Valid QuantityDTO dto);
    
    double subtract(QuantityDTO dto);

    double divide(QuantityDTO dto);
}