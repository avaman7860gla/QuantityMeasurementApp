package com.example.history.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.history.dto.HistoryDTO;
import com.example.history.entity.QuantityMeasurementEntity;
import com.example.history.repository.QuantityMeasurementRepository;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private QuantityMeasurementRepository repository;

    @Override
    public String save(HistoryDTO dto) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setValue1(dto.getValue1());
        entity.setValue2(dto.getValue2());
        entity.setUnit(dto.getUnit());
        entity.setOperation(dto.getOperation());
        entity.setResult(dto.getResult());

        repository.save(entity);

        return "Saved successfully";
    }

    @Override
    public List<QuantityMeasurementEntity> getAll() {
        return repository.findAll();
    }
}