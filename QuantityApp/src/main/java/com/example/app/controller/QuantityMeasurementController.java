package com.example.app.controller;

import com.example.app.dto.QuantityDTO;
import com.example.app.entity.QuantityMeasurementEntity;
import com.example.app.service.IQuantityMeasurementService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quantity")
public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    @PostMapping("/compare")
    public boolean compare(@Valid @RequestBody QuantityDTO dto) {
        return service.compare(dto);
    }

    @PostMapping("/add")
    public double add(@Valid @RequestBody QuantityDTO dto) {
        return service.add(dto);
    }

    @GetMapping("/history")
    public List<QuantityMeasurementEntity> getHistory() {
        return service.getHistory();
    }
}