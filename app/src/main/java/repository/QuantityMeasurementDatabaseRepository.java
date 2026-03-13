package repository;

import entity.QuantityMeasurementEntity;
import util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    private static final String INSERT_SQL =
            "INSERT INTO quantity_measurement (operation_type, measurement_type, result_value) VALUES (?, ?, ?)";

    @Override
    public void save(QuantityMeasurementEntity entity) {

        
        try (
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL)
        ) {

            String operationType = entity.getOperationType();

            String measurementType = null;
            double resultValue = 0;

            if (entity.getResult() != null) {
                measurementType = entity.getResult().getMeasurementType();
                resultValue = entity.getResult().getValue();
            }

            ps.setString(1, operationType);
            ps.setString(2, measurementType);
            ps.setDouble(3, resultValue);
            
            

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Error saving measurement", e);
        }
    }

    @Override
    public java.util.List<QuantityMeasurementEntity> getAllMeasurements() {
        return new java.util.ArrayList<>();
    }

    @Override
    public java.util.List<QuantityMeasurementEntity> getMeasurementsByOperation(String operationType) {
        return new java.util.ArrayList<>();
    }

    @Override
    public java.util.List<QuantityMeasurementEntity> getMeasurementsByType(String measurementType) {
        return new java.util.ArrayList<>();
    }

    @Override
    public int getTotalCount() {
        return 0;
    }

    @Override
    public void deleteAll() {
    }

    @Override
    public String getPoolStatistics() {
        return "Connection pool active";
    }

    @Override
    public void releaseResources() {
    }
}