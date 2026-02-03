package com.todo.todoApp.services;

import com.todo.todoApp.dto.SubTodoDTO;
import com.todo.todoApp.entity.SubTodoEntity;
import com.todo.todoApp.entity.TodoEntity;
import com.todo.todoApp.exceptions.ResourceNotFoundException;
import com.todo.todoApp.repository.SubTodoRepository;
import com.todo.todoApp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubTodoService {

    private final SubTodoRepository subTodoRepository;
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    public SubTodoDTO registerSubTodo(String todoId, SubTodoDTO subTodoDTO) {
        SubTodoEntity subTodo = subTodoRepository.findByTopic(subTodoDTO.getTopic());

        if(subTodo != null) throw new ResourceNotFoundException("Sub Todo Already Registered");
        TodoEntity todo = todoRepository.findById(todoId).orElseThrow(()->new ResourceNotFoundException("Todo not found"));
        SubTodoEntity subTodoEntity = modelMapper.map(subTodoDTO, SubTodoEntity.class);
        subTodoEntity.setTodo(todo);
        SubTodoEntity createdSubTodo = subTodoRepository.save(subTodoEntity);
        return modelMapper.map(createdSubTodo, SubTodoDTO.class);

    }
}
