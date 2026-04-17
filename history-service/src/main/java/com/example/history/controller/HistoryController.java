package com.example.history.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.history.dto.HistoryDTO;
import com.example.history.entity.QuantityMeasurementEntity;
import com.example.history.service.HistoryService;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    private HistoryService service;

    @PostMapping("/save")
    public String save(@RequestBody HistoryDTO dto) {
        return service.save(dto);
    }

    @GetMapping("/all")
    public List<QuantityMeasurementEntity> getAll() {
        return service.getAll();
    }
}