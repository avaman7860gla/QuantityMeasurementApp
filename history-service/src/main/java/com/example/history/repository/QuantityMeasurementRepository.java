package com.example.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.history.entity.QuantityMeasurementEntity;

public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {
	
}