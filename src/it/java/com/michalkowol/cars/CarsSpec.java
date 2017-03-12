package com.michalkowol.cars;

import com.google.common.collect.ImmutableList;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarsSpec {

    @Inject
    private CarsController carsController;

    @Inject
    private CarsRepository carsRepository;

    @Inject
    private TestRestTemplate restTemplate;

    @Inject
    private DataSource dataSource;

    private final Operation deleteAllCars = deleteAllFrom("cars");

    private Operation insertCars = insertInto("cars")
        .columns("id", "name")
        .values(1, "Audi")
        .values(2, "Opel")
        .values(3, "BMW")
        .build();

    @Test
    public void itShouldSelectAllCars() {
        // given
        prepareDatabase(sequenceOf(deleteAllCars, insertCars));
        // when
        List<Car> cars = carsController.cars();
        // then
        assertThat(cars, hasSize(3));
        assertThat(cars.get(0), equalTo(new Car(1, "Audi")));
        assertThat(cars.get(1), equalTo(new Car(2, "Opel")));
        assertThat(cars.get(2), equalTo(new Car(3, "BMW")));
    }

    @Test
    public void itShouldSelectAllCarsWithRest() {
        // given
        prepareDatabase(sequenceOf(deleteAllCars));
        CarEntity audi = new CarEntity(1, "Audi");
        CarEntity bmw = new CarEntity(2, "VM");
        carsRepository.save(ImmutableList.of(audi, bmw));

        // when
        String json = restTemplate.getForObject("/cars", String.class);

        // then
        assertThat(json, startsWith("[{\"id\":1"));
        assertThat(json, containsString("VM"));
    }

    private void prepareDatabase(Operation operation) {
        new DbSetup(new DataSourceDestination(dataSource), operation).launch();
    }
}
