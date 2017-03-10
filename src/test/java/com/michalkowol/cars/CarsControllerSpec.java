package com.michalkowol.cars;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CarsControllerSpec {

    @Test
    public void itShouldFindAllCars() {
        // given
        CarsRepository carsRepository = mock(CarsRepository.class);
        when(carsRepository.findAll()).thenReturn(ImmutableList.of(new CarEntity(1, "Audi"), new CarEntity(2, "Ford")));
        CarsController carsController = new CarsController(carsRepository);
        // when
        List<Car> cars = carsController.cars();
        // then
        assertThat(cars, hasSize(2));
        assertThat(cars.get(0), equalTo(new Car(1, "Audi")));
    }
}
