package com.example.app.service;

import com.example.app.dto.QuantityDTO;
import com.example.app.entity.QuantityMeasurementEntity;
import com.example.app.repository.QuantityMeasurementRepository;
import com.example.app.util.OperationType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private final QuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(QuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean compare(QuantityDTO dto) {

        boolean result = dto.getValue1() == dto.getValue2();

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue1(dto.getValue1());
        entity.setValue2(dto.getValue2());
        entity.setUnit(dto.getUnit());
        entity.setOperation(OperationType.COMPARE.name());
        entity.setResult(result ? 1 : 0);

        repository.save(entity);

        return result;
    }

    @Override
    public double add(QuantityDTO dto) {

        double result = dto.getValue1() + dto.getValue2();

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue1(dto.getValue1());
        entity.setValue2(dto.getValue2());
        entity.setUnit(dto.getUnit());
        entity.setOperation(OperationType.ADD.name());
        entity.setResult(result);

        repository.save(entity);

        return result;
    }

    @Override
    public List<QuantityMeasurementEntity> getHistory() {
        return repository.findAll();
    }
}