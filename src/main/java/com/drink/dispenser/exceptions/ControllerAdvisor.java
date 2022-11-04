package com.drink.dispenser.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {


    private static final String TIMESTAMP = "timestamp";
    private static final String MESSAGE = "message";

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> generalException(
            final Exception ex,
            final WebRequest request
    ) {
        return new ResponseEntity<>(this.getMap(LocalDateTime.now(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private Map<String, String> getMap(final LocalDateTime localDateTime, final String message) {
        final Map<String, String> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, localDateTime.toString());
        body.put(MESSAGE, message);

        return body;
    }

}