package app;

import controller.QuantityMeasurementController;
import dto.QuantityDTO;
import repository.IQuantityMeasurementRepository;
import repository.QuantityMeasurementDatabaseRepository;
import service.IQuantityMeasurementService;
import service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // Use DATABASE repository
        IQuantityMeasurementRepository repository =
                new QuantityMeasurementDatabaseRepository();

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repository);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        QuantityDTO q1 = new QuantityDTO(1, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES", "LENGTH");

        controller.performComparison(q1, q2);
        controller.performAddition(q1, q2);
        controller.performConversion(q1, "INCHES");

        System.out.println("Data saved to MySQL successfully");
    }
}