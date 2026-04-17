package com.example.history.service;

import com.example.history.dto.HistoryDTO;
import com.example.history.entity.QuantityMeasurementEntity;

import java.util.List;

public interface HistoryService {

    String save(HistoryDTO dto);

    List<QuantityMeasurementEntity> getAll();
}