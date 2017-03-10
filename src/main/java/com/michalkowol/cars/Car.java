package com.michalkowol.cars;

import lombok.NonNull;
import lombok.Value;

@Value
class Car {
    private final int id;
    @NonNull
    private final String name;
}
