package com.todo.todoApp.controllers;

import com.todo.todoApp.advice.ApiError;
import com.todo.todoApp.advice.ApiResponse;
import com.todo.todoApp.dto.UserSignUpDTO;
import com.todo.todoApp.dto.UserSignUpResponseDTO;
import com.todo.todoApp.exceptions.ResourceNotFoundException;
import com.todo.todoApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register-user")
    public ResponseEntity<ApiResponse<UserSignUpResponseDTO>> registerUser(@RequestBody UserSignUpDTO userSignUpDTO){
       try{

           UserSignUpResponseDTO user = userService.registerUser(userSignUpDTO);
           return ResponseEntity.ok(new ApiResponse<>(user));
       } catch (Exception e) {
           throw new RuntimeException(e);
       }

    }
}
