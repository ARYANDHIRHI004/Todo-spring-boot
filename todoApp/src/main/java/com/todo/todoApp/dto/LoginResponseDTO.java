package com.todo.todoApp.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDTO {
    private String email;
    private String password;
    private String token;
}
