package com.todo.todoApp.controllers;

import com.todo.todoApp.advice.ApiResponse;
import com.todo.todoApp.dto.TodoDTO;
import com.todo.todoApp.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/get-todos")
    public ResponseEntity<ApiResponse<String>> getTodos(){
        return ResponseEntity.ok(new ApiResponse<>("aryan"));
    }

    @PostMapping("/register-todo")
    public ResponseEntity<ApiResponse<TodoDTO>> registerTodo(@RequestBody TodoDTO todoDTO){
        try {
            TodoDTO todo = todoService.registerTodo(todoDTO);
            return ResponseEntity.ok(new ApiResponse<>(todo));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-todo-by-id/{todoId}")
    public ResponseEntity<ApiResponse<TodoDTO>> getTodoById(@PathVariable String todoId){
        try{
            TodoDTO todo = todoService.getTodoById(todoId);
            return ResponseEntity.ok(new ApiResponse<>(todo));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-all-todos")
    public ResponseEntity<ApiResponse<List<TodoDTO>>> getAllTodos(){
        try{
            List<TodoDTO> todo = todoService.getAllTodos();
            return ResponseEntity.ok(new ApiResponse<>(todo));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
