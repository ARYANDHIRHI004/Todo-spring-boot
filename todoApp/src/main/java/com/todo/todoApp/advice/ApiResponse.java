package com.todo.todoApp.advice;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ApiResponse<T> {

    private T data;
    private ApiError apiError;

    public ApiResponse(T data){
        this.data = data;
    }

    public ApiResponse(ApiError apiError){
        this.apiError = apiError;
    }

}
