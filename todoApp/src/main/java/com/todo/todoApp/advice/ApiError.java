package com.todo.todoApp.advice;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@Builder
public class ApiError {

    private HttpStatus status;
    private String message;

    public ApiError(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }

}
