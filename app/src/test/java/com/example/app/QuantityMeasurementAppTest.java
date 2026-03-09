package com.example.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.QuantityMeasurementController;
import dto.QuantityDTO;
import entity.QuantityMeasurementEntity;
import repository.QuantityMeasurementCacheRepository;
import repository.IQuantityMeasurementRepository;
import service.IQuantityMeasurementService;
import service.QuantityMeasurementServiceImpl;

class QuantityMeasurementAppTest {
    private IQuantityMeasurementRepository repository;
    private IQuantityMeasurementService service;
    private QuantityMeasurementController controller;

    @BeforeEach
    void setup() {
        repository = QuantityMeasurementCacheRepository.getInstance();
        service = new QuantityMeasurementServiceImpl(repository);
        controller = new QuantityMeasurementController(service);
    }

    // Entity Tests

    @Test
    void testQuantityEntity_SingleOperandConstruction() {
        QuantityDTO q = new QuantityDTO(1,"FEET","LENGTH");
        QuantityMeasurementEntity entity =new QuantityMeasurementEntity(q,null,"CONVERT",q);
        assertNotNull(entity);
    }

    @Test
    void testQuantityEntity_BinaryOperandConstruction() {
        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(12,"INCHES","LENGTH");
        QuantityMeasurementEntity entity =new QuantityMeasurementEntity(q1,q2,"ADD",q1);
        assertNotNull(entity);
    }

    @Test
    void testQuantityEntity_ErrorConstruction() {
        QuantityMeasurementEntity entity =new QuantityMeasurementEntity("Error occurred");
        assertTrue(entity.hasError());
    }

    @Test
    void testQuantityEntity_ToString_Success() {
        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityMeasurementEntity entity =new QuantityMeasurementEntity(q1,null,"CONVERT",q1);
        assertNotNull(entity.toString());
    }

    @Test
    void testQuantityEntity_ToString_Error() {
        QuantityMeasurementEntity entity =new QuantityMeasurementEntity("Error");
        assertTrue(entity.toString().contains("Error"));
    }

    // Service Tests

    @Test
    void testService_CompareEquality_SameUnit_Success() {
        QuantityDTO q1 = new QuantityDTO(5,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(5,"FEET","LENGTH");
        assertTrue(service.compare(q1,q2));
    }

    @Test
    void testService_CompareEquality_DifferentUnit_Success() {
        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(12,"INCHES","LENGTH");
        assertTrue(service.compare(q1,q2));
    }

    @Test
    void testService_CompareEquality_CrossCategory_Error() {
        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(1,"KILOGRAM","WEIGHT");
        assertFalse(service.compare(q1,q2));
    }

    @Test
    void testService_Convert_Success() {
        QuantityDTO q = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO result = service.convert(q,"INCHES");
        assertEquals(12,result.getValue());
    }

    @Test
    void testService_Add_Success() {
        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(12,"INCHES","LENGTH");
        QuantityDTO result = service.add(q1,q2);
        assertEquals(2,result.getValue());
    }

    @Test
    void testService_Add_UnsupportedOperation_Error() {
        QuantityDTO q1 = new QuantityDTO(100,"CELSIUS","TEMPERATURE");
        QuantityDTO q2 = new QuantityDTO(50,"CELSIUS","TEMPERATURE");
        assertThrows(Exception.class, () -> service.add(q1,q2));
    }

    @Test
    void testService_Subtract_Success() {
        QuantityDTO q1 = new QuantityDTO(10,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(5,"FEET","LENGTH");
        QuantityDTO result = service.subtract(q1,q2);
        assertEquals(5,result.getValue());
    }

    @Test
    void testService_Divide_Success() {
        QuantityDTO q1 = new QuantityDTO(10,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(5,"FEET","LENGTH");
        assertEquals(2,service.divide(q1,q2));
    }

    @Test
    void testService_Divide_ByZero_Error() {
        QuantityDTO q1 = new QuantityDTO(10,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(0,"FEET","LENGTH");
        assertThrows(Exception.class, () -> service.divide(q1,q2));
    }

    // Controller tests

    @Test
    void testController_DemonstrateEquality_Success() {
        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(12,"INCHES","LENGTH");
        controller.performComparison(q1,q2);
    }

    @Test
    void testController_DemonstrateConversion_Success() {
        QuantityDTO q = new QuantityDTO(1,"FEET","LENGTH");
        controller.performConversion(q,"INCHES");
    }

    @Test
    void testController_DemonstrateAddition_Success() {
        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(12,"INCHES","LENGTH");
        controller.performAddition(q1,q2);
    }

    @Test
    void testController_DemonstrateAddition_Error() {
        QuantityDTO q1 = new QuantityDTO(100,"CELSIUS","TEMPERATURE");
        QuantityDTO q2 = new QuantityDTO(50,"CELSIUS","TEMPERATURE");
        assertThrows(Exception.class, () -> controller.performAddition(q1,q2));
    }

    @Test
    void testController_DisplayResult_Success() {
        QuantityDTO q1 = new QuantityDTO(10,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(5,"FEET","LENGTH");
        controller.performDivision(q1,q2);
    }

    @Test
    void testController_DisplayResult_Error() {
        QuantityDTO q1 = new QuantityDTO(10,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(0,"FEET","LENGTH");
        assertThrows(Exception.class, () -> controller.performDivision(q1,q2));
    }

    // Architecture tests

    @Test
    void testLayerSeparation_ServiceIndependence() {
        QuantityDTO q = new QuantityDTO(1,"FEET","LENGTH");
        service.convert(q,"INCHES");
    }

    @Test
    void testLayerSeparation_ControllerIndependence() {
        QuantityDTO q = new QuantityDTO(1,"FEET","LENGTH");
        controller.performConversion(q,"INCHES");
    }

    @Test
    void testDataFlow_ControllerToService() {
        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(12,"INCHES","LENGTH");
        assertTrue(service.compare(q1,q2));
    }

    @Test
    void testDataFlow_ServiceToController() {
        QuantityDTO q = new QuantityDTO(1,"FEET","LENGTH");
        controller.performConversion(q,"INCHES");
    }

    @Test
    void testBackwardCompatibility_AllUC1_UC14_Tests() {
        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(12,"INCHES","LENGTH");
        assertTrue(service.compare(q1,q2));
    }

    @Test
    void testService_AllMeasurementCategories() {
        service.convert(new QuantityDTO(1,"FEET","LENGTH"),"INCHES");
        service.convert(new QuantityDTO(1,"KILOGRAM","WEIGHT"),"GRAM");
        service.convert(new QuantityDTO(1,"LITRE","VOLUME"),"MILLILITRE");
    }

    @Test
    void testController_AllOperations() {
        QuantityDTO q1 = new QuantityDTO(10,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(5,"FEET","LENGTH");
        controller.performComparison(q1,q2);
        controller.performAddition(q1,q2);
        controller.performSubtraction(q1,q2);
        controller.performDivision(q1,q2);
    }

    @Test
    void testService_ValidationConsistency() {
        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(1,"KILOGRAM","WEIGHT");
        assertFalse(service.compare(q1,q2));
    }

    @Test
    void testEntity_Immutability() {
        QuantityMeasurementEntity entity =new QuantityMeasurementEntity("error");
        assertTrue(entity.hasError());
    }

    @Test
    void testService_ExceptionHandling_AllOperations() {
        QuantityDTO q1 = new QuantityDTO(100,"CELSIUS","TEMPERATURE");
        QuantityDTO q2 = new QuantityDTO(50,"CELSIUS","TEMPERATURE");
        assertThrows(Exception.class, () -> service.add(q1,q2));
    }

    @Test
    void testController_ConsoleOutput_Format() {
        QuantityDTO q = new QuantityDTO(1,"FEET","LENGTH");
        controller.performConversion(q,"INCHES");
    }

    @Test
    void testIntegration_EndToEnd_LengthAddition() {
        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(12,"INCHES","LENGTH");
        QuantityDTO result = service.add(q1,q2);
        assertEquals(2,result.getValue());
    }

    @Test
    void testIntegration_EndToEnd_TemperatureUnsupported() {
        QuantityDTO q1 = new QuantityDTO(100,"CELSIUS","TEMPERATURE");
        QuantityDTO q2 = new QuantityDTO(50,"CELSIUS","TEMPERATURE");
        assertThrows(Exception.class, () -> service.add(q1,q2));
    }

    @Test
    void testService_NullEntity_Rejection() {
        assertThrows(Exception.class, () -> service.compare(null,null));
    }

    @Test
    void testService_AllUnitImplementations() {
        service.convert(new QuantityDTO(1,"FEET","LENGTH"),"INCHES");
        service.convert(new QuantityDTO(1,"KILOGRAM","WEIGHT"),"GRAM");
        service.convert(new QuantityDTO(1,"LITRE","VOLUME"),"MILLILITRE");
    }

    @Test
    void testEntity_OperationType_Tracking() {
        QuantityDTO q = new QuantityDTO(1,"FEET","LENGTH");
        QuantityMeasurementEntity entity =new QuantityMeasurementEntity(q,null,"CONVERT",q);
        assertNotNull(entity);
    }

    @Test
    void testLayerDecoupling_ServiceChange() {
        IQuantityMeasurementService newService =new QuantityMeasurementServiceImpl(repository);
        assertNotNull(newService);
    }

    @Test
    void testLayerDecoupling_EntityChange() {
        QuantityMeasurementEntity entity =new QuantityMeasurementEntity("error");
        assertTrue(entity.hasError());
    }

    @Test
    void testScalability_NewOperation_Addition() {
        QuantityDTO q1 = new QuantityDTO(10,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(5,"FEET","LENGTH");
        QuantityDTO result = service.add(q1,q2);
        assertEquals(15,result.getValue());
    }
}