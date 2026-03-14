package com.example.app.service;

import com.example.app.dto.QuantityDTO;
import com.example.app.entity.QuantityMeasurementEntity;
import java.util.List;

public interface IQuantityMeasurementService {

    boolean compare(QuantityDTO dto);

    double add(QuantityDTO dto);

    List<QuantityMeasurementEntity> getHistory();

}