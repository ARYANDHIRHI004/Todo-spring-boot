package com.todo.todoApp.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDTO {

    private String id;
    private String email;
    private String token;
}
