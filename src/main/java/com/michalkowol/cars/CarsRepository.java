package com.michalkowol.cars;

import org.springframework.data.repository.CrudRepository;

interface CarsRepository extends CrudRepository<CarEntity, Integer> {
}
