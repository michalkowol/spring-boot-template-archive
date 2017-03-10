package com.michalkowol;

import com.michalkowol.Errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
@RestController
class ExceptionControllerAdvice {

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    ServerError handleUnknownException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return InternalServerError.create(ex);
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler
    ServerError handleNotFoundException(NotFoundException ex) {
        log.info(ex.getMessage(), ex);
        return new NotFound(ex.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler
    ServerError handleBadRequestException(BadRequestException ex) {
        log.info(ex.getMessage(), ex);
        return new BadRequest(ex.getMessage());
    }
}
