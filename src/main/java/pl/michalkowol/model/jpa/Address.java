package pl.michalkowol.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.NonFinal;

import javax.persistence.*;
import java.util.List;

@Value
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonFinal
    long id;

    @NonFinal
    @NonNull
    String street;

    @JsonIgnore
    @NonFinal
    @NonNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "addresses_people",
            joinColumns = {@JoinColumn(name = "address_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id", referencedColumnName = "id")})
    List<Person> people;
}
