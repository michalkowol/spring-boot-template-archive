package pl.michalkowol;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class NameException extends RuntimeException {

    @NonNull String name;

    public NameException(String msg) {
        super(msg);
        this.name = msg;
    }
}
