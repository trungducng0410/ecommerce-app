package io.ducnt.ecommerce.exceptions.handlers;

import io.ducnt.ecommerce.exceptions.ApiError;
import io.ducnt.ecommerce.exceptions.DuplicateOrderException;
import io.ducnt.ecommerce.exceptions.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(value = OrderNotFoundException.class)
    public final ResponseEntity<ApiError> handleException(OrderNotFoundException ex) {
        ApiError error = new ApiError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DuplicateOrderException.class)
    public final ResponseEntity<ApiError> handleException(DuplicateOrderException ex) {
        ApiError error = new ApiError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
