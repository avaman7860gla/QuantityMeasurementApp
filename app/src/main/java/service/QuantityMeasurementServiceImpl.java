package service;

import com.example.app.IMeasurable;
import com.example.app.Quantity;

import dto.QuantityDTO;
import entity.QuantityMeasurementEntity;
import repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }


    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        Quantity<IMeasurable> quantity1 =
                new Quantity<>(q1.getValue(),
                        IMeasurable.getUnit(q1.getMeasurementType(), q1.getUnit()));

        Quantity<IMeasurable> quantity2 =
                new Quantity<>(q2.getValue(),
                        IMeasurable.getUnit(q2.getMeasurementType(), q2.getUnit()));

        boolean result = quantity1.equals(quantity2);

        QuantityDTO resultDTO =
                new QuantityDTO(result ? 1 : 0, "RESULT", q1.getMeasurementType());

        repository.save(
                new QuantityMeasurementEntity(q1, q2, "COMPARE", resultDTO)
        );

        return result;
    }


    @Override
    public QuantityDTO convert(QuantityDTO quantity, String targetUnit) {

        IMeasurable unit =
                IMeasurable.getUnit(quantity.getMeasurementType(), quantity.getUnit());

        IMeasurable target =
                IMeasurable.getUnit(quantity.getMeasurementType(), targetUnit);

        Quantity<IMeasurable> q =
                new Quantity<>(quantity.getValue(), unit);

        Quantity<IMeasurable> result =
                q.convertTo(target);

        QuantityDTO dto =
                new QuantityDTO(
                        result.getValue(),
                        targetUnit,
                        quantity.getMeasurementType()
                );

        repository.save(
                new QuantityMeasurementEntity(quantity, "CONVERT", dto)
        );

        return dto;
    }


    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        Quantity<IMeasurable> quantity1 =
                new Quantity<>(q1.getValue(),
                        IMeasurable.getUnit(q1.getMeasurementType(), q1.getUnit()));

        Quantity<IMeasurable> quantity2 =
                new Quantity<>(q2.getValue(),
                        IMeasurable.getUnit(q2.getMeasurementType(), q2.getUnit()));

        Quantity<IMeasurable> result =
                quantity1.add(quantity2);

        QuantityDTO dto =
                new QuantityDTO(
                        result.getValue(),
                        result.getUnit().toString(),
                        q1.getMeasurementType()
                );

        repository.save(
                new QuantityMeasurementEntity(q1, q2, "ADD", dto)
        );

        return dto;
    }


    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

        Quantity<IMeasurable> quantity1 =
                new Quantity<>(q1.getValue(),
                        IMeasurable.getUnit(q1.getMeasurementType(), q1.getUnit()));

        Quantity<IMeasurable> quantity2 =
                new Quantity<>(q2.getValue(),
                        IMeasurable.getUnit(q2.getMeasurementType(), q2.getUnit()));

        Quantity<IMeasurable> result =
                quantity1.subtract(quantity2);

        QuantityDTO dto =
                new QuantityDTO(
                        result.getValue(),
                        result.getUnit().toString(),
                        q1.getMeasurementType()
                );

        repository.save(
                new QuantityMeasurementEntity(q1, q2, "SUBTRACT", dto)
        );

        return dto;
    }


    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        Quantity<IMeasurable> quantity1 =
                new Quantity<>(q1.getValue(),
                        IMeasurable.getUnit(q1.getMeasurementType(), q1.getUnit()));

        Quantity<IMeasurable> quantity2 =
                new Quantity<>(q2.getValue(),
                        IMeasurable.getUnit(q2.getMeasurementType(), q2.getUnit()));

        double result =
                quantity1.divide(quantity2);

        QuantityDTO dto =
                new QuantityDTO(result, "SCALAR", "RESULT");

        repository.save(
                new QuantityMeasurementEntity(q1, q2, "DIVIDE", dto)
        );

        return result;
    }
}