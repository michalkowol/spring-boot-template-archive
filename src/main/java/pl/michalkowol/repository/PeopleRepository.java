package pl.michalkowol.repository;

import org.springframework.data.repository.CrudRepository;
import pl.michalkowol.model.jpa.Person;

import java.util.List;

public interface PeopleRepository extends CrudRepository<Person, Long> {
    List<Person> findByName(String name);
}
