package pl.michalkowol.model.simple;

import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
public class Person {
    @NonNull String name;
    @NonNull List<Address> addresses;
}
