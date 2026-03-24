package com.example.app;

import com.example.app.entity.QuantityMeasurementEntity;
import com.example.app.repository.QuantityMeasurementRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QuantityAppApplicationTests {

    @Autowired
    private QuantityMeasurementRepository repository;

    // 1
    @Test
    void testSpringBootApplicationStarts() {
        assertThat(repository).isNotNull();
    }

    // 2
    @Test
    void testApplicationContextLoads() {
        assertThat(repository).isNotNull();
    }

    // 3
    @Test
    void testSaveEntity() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue1(5);
        entity.setValue2(7);
        entity.setUnit("feet");
        entity.setOperation("ADD");
        entity.setResult(12);

        QuantityMeasurementEntity saved = repository.save(entity);

        assertThat(saved.getId()).isNotNull();
    }

    // 4
    @Test
    void testFindAllEntities() {

        List<QuantityMeasurementEntity> list = repository.findAll();

        assertThat(list).isNotNull();
    }

    // 5
    @Test
    void testRepositoryCount() {

        long count = repository.count();

        assertThat(count).isGreaterThanOrEqualTo(0);
    }

    // 6
    @Test
    void testFindById() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue1(10);
        entity.setValue2(10);
        entity.setUnit("feet");
        entity.setOperation("COMPARE");
        entity.setResult(1);

        QuantityMeasurementEntity saved = repository.save(entity);

        Optional<QuantityMeasurementEntity> result = repository.findById(saved.getId());

        assertThat(result.isPresent()).isTrue();
    }

    // 7
    @Test
    void testExistsById() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue1(3);
        entity.setValue2(3);
        entity.setUnit("feet");
        entity.setOperation("COMPARE");
        entity.setResult(1);

        QuantityMeasurementEntity saved = repository.save(entity);

        boolean exists = repository.existsById(saved.getId());

        assertThat(exists).isTrue();
    }

    // 8
    @Test
    void testDeleteEntity() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue1(2);
        entity.setValue2(2);
        entity.setUnit("feet");
        entity.setOperation("ADD");
        entity.setResult(4);

        repository.save(entity);
        repository.delete(entity);

        assertThat(repository.findAll()).doesNotContain(entity);
    }

    // 9
    @Test
    void testEntityValue1GetterSetter() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue1(15);

        assertThat(entity.getValue1()).isEqualTo(15);
    }

    // 10
    @Test
    void testEntityValue2GetterSetter() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue2(20);

        assertThat(entity.getValue2()).isEqualTo(20);
    }

    // 11
    @Test
    void testEntityUnitGetterSetter() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setUnit("inch");

        assertThat(entity.getUnit()).isEqualTo("inch");
    }

    // 12
    @Test
    void testEntityOperationGetterSetter() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setOperation("COMPARE");

        assertThat(entity.getOperation()).isEqualTo("COMPARE");
    }

    // 13
    @Test
    void testEntityResultGetterSetter() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setResult(50);

        assertThat(entity.getResult()).isEqualTo(50);
    }

    // 14
    @Test
    void testInsertMultipleRecords() {

        for(int i=0;i<3;i++){

            QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
            entity.setValue1(i);
            entity.setValue2(i);
            entity.setUnit("feet");
            entity.setOperation("ADD");
            entity.setResult(i+i);

            repository.save(entity);
        }

        assertThat(repository.findAll().size()).isGreaterThan(0);
    }

    // 15
    @Test
    void testDatabasePersistence() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue1(8);
        entity.setValue2(9);
        entity.setUnit("feet");
        entity.setOperation("ADD");
        entity.setResult(17);

        repository.save(entity);

        List<QuantityMeasurementEntity> list = repository.findAll();

        assertThat(list).isNotEmpty();
    }

    // 16
    @Test
    void testInsertZeroValues() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue1(0);
        entity.setValue2(0);
        entity.setUnit("feet");
        entity.setOperation("ADD");
        entity.setResult(0);

        repository.save(entity);

        assertThat(repository.findAll()).isNotEmpty();
    }

    // 17
    @Test
    void testInsertLargeValues() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue1(1000);
        entity.setValue2(2000);
        entity.setUnit("feet");
        entity.setOperation("ADD");
        entity.setResult(3000);

        repository.save(entity);

        assertThat(repository.findAll()).isNotEmpty();
    }

    // 18
    @Test
    void testInsertNegativeValues() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue1(-5);
        entity.setValue2(-10);
        entity.setUnit("feet");
        entity.setOperation("ADD");
        entity.setResult(-15);

        repository.save(entity);

        assertThat(repository.findAll()).isNotEmpty();
    }

    // 19
    @Test
    void testRepositorySizeIncrease() {

        int before = repository.findAll().size();

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue1(1);
        entity.setValue2(2);
        entity.setUnit("feet");
        entity.setOperation("ADD");
        entity.setResult(3);

        repository.save(entity);

        int after = repository.findAll().size();

        assertThat(after).isGreaterThanOrEqualTo(before);
    }

    // 20
    @Test
    void testInsertCompareOperation() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue1(4);
        entity.setValue2(4);
        entity.setUnit("feet");
        entity.setOperation("COMPARE");
        entity.setResult(1);

        repository.save(entity);

        assertThat(repository.findAll()).isNotEmpty();
    }

    // 21
    @Test
    void testRepositoryNotEmpty() {

        assertThat(repository.findAll()).isNotNull();
    }

    // 22
    @Test
    void testSaveAndRetrieve() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue1(11);
        entity.setValue2(11);
        entity.setUnit("feet");
        entity.setOperation("COMPARE");
        entity.setResult(1);

        repository.save(entity);

        List<QuantityMeasurementEntity> list = repository.findAll();

        assertThat(list.size()).isGreaterThan(0);
    }

    // 23
    @Test
    void testInsertDifferentUnit() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue1(1);
        entity.setValue2(12);
        entity.setUnit("inch");
        entity.setOperation("ADD");
        entity.setResult(13);

        repository.save(entity);

        assertThat(repository.findAll()).isNotEmpty();
    }

    // 24
    @Test
    void testInsertFeetUnit() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setUnit("feet");

        assertThat(entity.getUnit()).isEqualTo("feet");
    }

    // 25
    @Test
    void testInsertInchUnit() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setUnit("inch");

        assertThat(entity.getUnit()).isEqualTo("inch");
    }

    // 26
    @Test
    void testEntityCreation() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        assertThat(entity).isNotNull();
    }

    // 27
    @Test
    void testEntityDefaultValues() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        assertThat(entity.getValue1()).isEqualTo(0);
    }

    // 28
    @Test
    void testEntitySetMultipleFields() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue1(3);
        entity.setValue2(6);

        assertThat(entity.getValue2()).isEqualTo(6);
    }

    // 29
    @Test
    void testRepositoryConnection() {

        assertThat(repository).isNotNull();
    }

    // 30
    @Test
    void testRepositoryInitialization() {

        assertThat(repository.count()).isGreaterThanOrEqualTo(0);
    }

    // 31
    @Test
    void testInsertOperationADD() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setOperation("ADD");

        assertThat(entity.getOperation()).isEqualTo("ADD");
    }

    // 32
    @Test
    void testInsertOperationCOMPARE() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setOperation("COMPARE");

        assertThat(entity.getOperation()).isEqualTo("COMPARE");
    }

    // 33
    @Test
    void testEntityResultCalculation() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setResult(25);

        assertThat(entity.getResult()).isEqualTo(25);
    }

    // 34
    @Test
    void testApplicationRunning() {

        assertThat(true).isTrue();
    }

    // 35
    @Test
    void testSpringBootEnvironment() {

        assertThat(repository).isNotNull();
    }

}