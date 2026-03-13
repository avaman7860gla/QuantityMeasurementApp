-- Run this script before running the application

create database quantity_measurement_db; 

use quantity_measurement_db;

CREATE TABLE quantity_measurement (

    id INT AUTO_INCREMENT PRIMARY KEY,

    operation_type VARCHAR(50),

    measurement_type VARCHAR(50),

    result_value DOUBLE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);