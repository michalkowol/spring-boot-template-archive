package com.michalkowol.cars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.michalkowol.Errors.BadRequestException;
import static com.michalkowol.Errors.NotFoundException;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/cars", produces = "application/json")
class CarsController {

    private final CarsRepository carsRepository;

    @Autowired
    CarsController(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @RequestMapping
    List<Car> cars() {
        Iterable<CarEntity> carsEntities = carsRepository.findAll();
        List<Car> cars = StreamSupport.stream(carsEntities.spliterator(), false).map(carEntity -> new Car(carEntity.getId(), carEntity.getName())).collect(Collectors.toList());
        return cars;
    }

    @RequestMapping("/{id}")
    Car carById(@PathVariable int id) {
        CarEntity carEntity = Optional.ofNullable(carsRepository.findOne(id)).orElseThrow(() -> new NotFoundException("Car with id=" + id + " not found"));
        Car car = new Car(carEntity.getId(), carEntity.getName());
        return car;
    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    Car createCar(@RequestBody Car car) {
        CarEntity carEntity = new CarEntity(car.getId(), car.getName());
        CarEntity saved = carsRepository.save(carEntity);
        return new Car(saved.getId(), saved.getName());
    }

    @RequestMapping("/create")
    @ResponseStatus(CREATED)
    Car createCarWithQueryParams(@RequestParam int id, @RequestParam String name) {
        // not RESTfull - only for demo
        CarEntity carEntity = new CarEntity(id, name);
        CarEntity saved = carsRepository.save(carEntity);
        return new Car(saved.getId(), saved.getName());
    }

    @RequestMapping(path = "/{id}", method = PUT)
    Car changeCarName(@PathVariable("id") int id, @RequestBody Car car) {
        if (car.getId() != id) {
            throw new BadRequestException("Request id is not equal Car.id");
        }

        CarEntity carEntity = carsRepository.findOne(id);
        carEntity.setName(car.getName());
        CarEntity saved = carsRepository.save(carEntity);

        return new Car(saved.getId(), saved.getName());
    }

    @RequestMapping(path = "/{id}", method = DELETE)
    @ResponseStatus(NO_CONTENT)
    private void deleteCar(@PathVariable("id") int id) {
        carsRepository.delete(id);
    }
}
