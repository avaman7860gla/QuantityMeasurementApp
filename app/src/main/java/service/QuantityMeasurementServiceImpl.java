package service;

import com.example.app.IMeasurable;
import com.example.app.Quantity;

import dto.QuantityDTO;
import entity.QuantityMeasurementEntity;
import repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {
    private final IQuantityMeasurementRepository repository;

    // Constructor
    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    private Quantity<IMeasurable> createQuantity(QuantityDTO dto) {
        IMeasurable unit =IMeasurable.getUnit(dto.getMeasurementType(), dto.getUnit());
        return new Quantity<>(dto.getValue(), unit);
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        Quantity<IMeasurable> quantity1 = createQuantity(q1);
        Quantity<IMeasurable> quantity2 = createQuantity(q2);
        boolean result = quantity1.equals(quantity2);
        repository.save(new QuantityMeasurementEntity(q1, q2, "COMPARE",
                        new QuantityDTO(result ? 1 : 0, "BOOLEAN", "RESULT")));
        return result;
    }

    @Override
    public QuantityDTO convert(QuantityDTO quantity, String targetUnit) {
        Quantity<IMeasurable> q = createQuantity(quantity);
        IMeasurable target =IMeasurable.getUnit(quantity.getMeasurementType(), targetUnit);
        Quantity<IMeasurable> result = q.convertTo(target);
        QuantityDTO dto =new QuantityDTO(result.getValue(), targetUnit, quantity.getMeasurementType());
        repository.save(new QuantityMeasurementEntity(quantity, null, "CONVERT", dto));
        return dto;
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        Quantity<IMeasurable> quantity1 = createQuantity(q1);
        Quantity<IMeasurable> quantity2 = createQuantity(q2);
        Quantity<IMeasurable> result = quantity1.add(quantity2);
        QuantityDTO dto =new QuantityDTO(result.getValue(),result.getUnit().getUnitName(),q1.getMeasurementType());
        repository.save(new QuantityMeasurementEntity(q1, q2, "ADD", dto));
        return dto;
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        Quantity<IMeasurable> quantity1 = createQuantity(q1);
        Quantity<IMeasurable> quantity2 = createQuantity(q2);
        Quantity<IMeasurable> result = quantity1.subtract(quantity2);
        QuantityDTO dto =new QuantityDTO(result.getValue(),result.getUnit().getUnitName(),q1.getMeasurementType());
        repository.save(new QuantityMeasurementEntity(q1, q2, "SUBTRACT", dto));
        return dto;
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {
        Quantity<IMeasurable> quantity1 = createQuantity(q1);
        Quantity<IMeasurable> quantity2 = createQuantity(q2);
        double result = quantity1.divide(quantity2);
        repository.save(new QuantityMeasurementEntity(q1, q2, "DIVIDE",new QuantityDTO(result, "SCALAR", "RESULT")));
        return result;
    }
}