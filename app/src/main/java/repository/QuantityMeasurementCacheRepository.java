package repository;

import java.util.ArrayList;
import java.util.List;

import entity.QuantityMeasurementEntity;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {

    private static QuantityMeasurementCacheRepository instance;

    private final List<QuantityMeasurementEntity> cache = new ArrayList<>();

    private QuantityMeasurementCacheRepository() {}

    public static QuantityMeasurementCacheRepository getInstance() {

        if (instance == null) {
            instance = new QuantityMeasurementCacheRepository();
        }

        return instance;
    }


    @Override
    public void save(QuantityMeasurementEntity entity) {
        cache.add(entity);
    }


    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return new ArrayList<>(cache);
    }


    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operationType) {

        List<QuantityMeasurementEntity> result = new ArrayList<>();

        for (QuantityMeasurementEntity entity : cache) {

            if (entity.getOperationType().equalsIgnoreCase(operationType)) {
                result.add(entity);
            }
        }

        return result;
    }


    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByType(String measurementType) {

        List<QuantityMeasurementEntity> result = new ArrayList<>();

        for (QuantityMeasurementEntity entity : cache) {

            if (entity.getResult() != null &&
                entity.getResult().getMeasurementType().equalsIgnoreCase(measurementType)) {

                result.add(entity);
            }
        }

        return result;
    }


    @Override
    public int getTotalCount() {
        return cache.size();
    }


    @Override
    public void deleteAll() {
        cache.clear();
    }


    @Override
    public String getPoolStatistics() {
        return "Cache repository - no connection pool";
    }


    @Override
    public void releaseResources() {
        cache.clear();
    }
}