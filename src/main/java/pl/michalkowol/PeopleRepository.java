package pl.michalkowol;

import lombok.NonNull;
import lombok.val;
import org.springframework.stereotype.Repository;
import pl.michalkowol.model.Address;
import pl.michalkowol.model.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
| Annotation | Meaning                                             |
+------------+-----------------------------------------------------+
| @Component | generic stereotype for any Spring-managed component |
| @Repository| stereotype for persistence layer                    |
| @Service   | stereotype for service layer                        |
| @Controller| stereotype for presentation layer (spring-mvc)      |
 */

@Repository
public class PeopleRepository {
    public Optional<Person> byId(@NonNull String id) {
        if (id.equals("1")){
            return Optional.of(new Person("Michal", Arrays.asList(new Address("Chemiczna", "Gliwice"), new Address("Pulawska", "Warszawa"))));
        } else if (id.equals("2")) {
            return Optional.of(new Person("Kasia", Collections.singletonList(new Address("Przybylskiego", "Warszawa"))));
        }
        return Optional.empty();
    }

    public List<Person> list() {
        val people = Arrays.asList(byId("1"), byId("2"));
        val filtered = people.stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
        return filtered;
    }
}
