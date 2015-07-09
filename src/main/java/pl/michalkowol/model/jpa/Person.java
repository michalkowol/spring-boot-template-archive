package pl.michalkowol.model.jpa;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.NonFinal;

import javax.persistence.*;
import java.util.List;

@Value
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonFinal
    long id;

    @NonFinal
    @NonNull
    String name;

    @NonFinal
    int age;

    @NonFinal
    @NonNull
    @ManyToMany
    @JoinTable(
            name = "addresses_people",
            joinColumns = {@JoinColumn(name = "person_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "address_id", referencedColumnName = "id")})
    List<Address> addresses;
}
