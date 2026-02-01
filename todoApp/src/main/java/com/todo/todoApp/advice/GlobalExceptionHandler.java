package com.todo.todoApp.advice;

import com.todo.todoApp.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> resourceNotFound(ResourceNotFoundException exception){
        return new ResponseEntity<>(ApiError
                .builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build(), HttpStatus.NOT_FOUND);
    }
}
