package com.todo.todoApp.advice;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResponse<T> {

    private HttpStatus status;
    private T data;
    private String message;
    private ApiError apiError;

    public ApiResponse(T data){
        this.data = data;
    }

    public ApiResponse(ApiError apiError){
        this.apiError = apiError;
    }

}
