package com.todo.todoApp.services;

import com.todo.todoApp.dto.TodoDTO;
import com.todo.todoApp.entity.TodoEntity;
import com.todo.todoApp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    public TodoDTO registerTodo(TodoDTO todoDTO) {
        TodoEntity todoEntity = modelMapper.map(todoDTO, TodoEntity.class);
        TodoEntity todo =  todoRepository.save(todoEntity);
        return modelMapper.map(todo, TodoDTO.class);
    }

    public TodoDTO getTodoById(String todoId) throws Exception {
        TodoEntity todo = todoRepository.findById(todoId).orElseThrow(()-> new Exception("Todo Not found"));
        return modelMapper.map(todo, TodoDTO.class);
    }
}
