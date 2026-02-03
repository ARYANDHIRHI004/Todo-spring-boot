package com.todo.todoApp.controllers;

import com.todo.todoApp.advice.ApiError;
import com.todo.todoApp.advice.ApiResponse;
import com.todo.todoApp.dto.LoginResponseDTO;
import com.todo.todoApp.dto.UserLoginDTO;
import com.todo.todoApp.dto.UserSignUpDTO;
import com.todo.todoApp.dto.UserSignUpResponseDTO;
import com.todo.todoApp.exceptions.ResourceNotFoundException;
import com.todo.todoApp.services.AuthService;
import com.todo.todoApp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final AuthService authService;

    @PostMapping("/register-user")
    public ResponseEntity<ApiResponse<UserSignUpResponseDTO>> registerUser(@RequestBody UserSignUpDTO userSignUpDTO){
       try{

           UserSignUpResponseDTO user = authService.registerUser(userSignUpDTO);
           return ResponseEntity.ok(new ApiResponse<>(user));
       } catch (Exception e) {
           throw new RuntimeException(e);
       }

    }

    @PostMapping("/login-user")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> loginUser(@RequestBody UserLoginDTO userLoginDTO, HttpServletResponse response){
         LoginResponseDTO user = authService.loginUser(userLoginDTO, response);
        return ResponseEntity.ok(new ApiResponse<>(user));
    }
}
