package pl.michalkowol.model.simple;

import lombok.NonNull;
import lombok.Value;

@Value
public class Address {
    @NonNull String street;
    @NonNull String city;
}
