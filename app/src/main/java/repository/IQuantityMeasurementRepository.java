package repository;

import entity.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementRepository {

    // Save measurement to repository (cache or database)
    void save(QuantityMeasurementEntity entity);

    // Get all stored measurements
    List<QuantityMeasurementEntity> getAllMeasurements();

    // Get measurements by operation type (ADD, SUBTRACT, CONVERT, etc.)
    List<QuantityMeasurementEntity> getMeasurementsByOperation(String operationType);

    // Get measurements by measurement type (LENGTH, WEIGHT, VOLUME, TEMPERATURE)
    List<QuantityMeasurementEntity> getMeasurementsByType(String measurementType);

    // Get total number of measurements stored
    int getTotalCount();

    // Delete all measurements (useful for testing / reset)
    void deleteAll();

    // Optional: repository implementations can override this
    default String getPoolStatistics() {
        return "Pool statistics not available";
    }

    // Optional: cleanup resources (database connections, caches, etc.)
    default void releaseResources() {
        // default empty implementation
    }
}