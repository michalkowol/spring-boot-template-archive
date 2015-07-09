package pl.michalkowol;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.michalkowol.model.Person;

import java.util.List;
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
    public List<Person> people() {
        List<Person> people = peopleRepository.list();
        people.stream().map(p -> "Name " + p.getName()).forEach(System.out::println);
        return people;
    }

    @RequestMapping("/{id}")
    public Optional<Person> person(@PathVariable String id) {
        if (id.equals("X")) {
            throw new ArithmeticException(id);
        } else if (id.equals("Y")) {
            throw new NameException(id);
        }

        return peopleRepository.byId(id);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(NameException.class)
    public Exception wrongName(NameException ex) {
        log.error("Wrong name: {}", ex.getName(), ex);
        return ex;
    }
}
