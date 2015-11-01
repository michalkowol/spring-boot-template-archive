package pl.michalkowol;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.michalkowol.model.jpa.Person;
import pl.michalkowol.repository.PeopleRepository;

import java.util.Optional;

@Slf4j
@RestController
@EnableAutoConfiguration
@RequestMapping("/people")
public class PeopleController {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleController(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @RequestMapping
    public Iterable<Person> people() {
        Iterable<Person> people = peopleRepository.findAll();
        Lists.newArrayList(people).stream().map(p -> "Name " + p.getName()).forEach(System.out::println);
        return people;
    }

    @RequestMapping(params = "name")
    public Iterable<Person> peopleByName(@RequestParam String name) {
        return peopleRepository.findByName(name);
    }

    @RequestMapping("/{id}")
    public Optional<Person> person(@PathVariable Long id) {
        if (id == 10) {
            throw new ArithmeticException(id.toString());
        } else if (id == 11) {
            throw new NameException(id.toString());
        } if (id == 12) {
            throw new IllegalStateException(id.toString());
        }

        Optional<Person> person = Optional.ofNullable(peopleRepository.findOne(id));
        return person;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NameException.class)
    public Exception wrongName(NameException ex) {
        log.error("Wrong name: {}", ex.getName(), ex);
        return ex;
    }
}
