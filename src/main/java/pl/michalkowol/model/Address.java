package pl.michalkowol.model;

import lombok.NonNull;
import lombok.Value;

@Value
public class Address {
    @NonNull String name;
    @NonNull String city;
}
