package io.ducnt.ecommerce.exceptions.handlers;

import io.ducnt.ecommerce.exceptions.ApiError;
import io.ducnt.ecommerce.exceptions.DuplicateCategoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CategoryExceptionHandler {

    @ExceptionHandler(DuplicateCategoryException.class)
    public ResponseEntity<ApiError> handleException(DuplicateCategoryException e) {
        ApiError error = new ApiError(
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
