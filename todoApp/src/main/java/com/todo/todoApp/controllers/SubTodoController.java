package com.todo.todoApp.controllers;

import com.todo.todoApp.advice.ApiResponse;
import com.todo.todoApp.dto.SubTodoDTO;
import com.todo.todoApp.repository.SubTodoRepository;
import com.todo.todoApp.services.SubTodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sub-todo")
public class SubTodoController {

    public final SubTodoService subTodoService;

    @PostMapping("/register-sub-todo/{todoId}")
    public ResponseEntity<ApiResponse<SubTodoDTO>> registerTodo(@PathVariable String todoId, @RequestBody SubTodoDTO subTodoDTO){
        try {
            SubTodoDTO subTodo = subTodoService.registerSubTodo(todoId, subTodoDTO);
            return ResponseEntity.ok(new ApiResponse<>(subTodo));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
