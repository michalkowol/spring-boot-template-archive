package pl.michalkowol;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ControllerAdvice
@RestController
public class ExceptionControllerAdvice {

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public Exception handleException(Exception ex) {
        log.error("", ex);
        return ex;
    }

    @ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED)
    @ExceptionHandler({ArithmeticException.class, NullPointerException.class})
    public Exception handleTwoExceptions(Exception ex) {
        log.error("", ex);
        return ex;
    }
}
