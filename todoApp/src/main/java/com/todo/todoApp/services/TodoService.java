package com.todo.todoApp.services;

import com.todo.todoApp.dto.TodoDTO;
import com.todo.todoApp.entity.TodoEntity;
import com.todo.todoApp.entity.UserEntity;
import com.todo.todoApp.repository.TodoRepository;
import com.todo.todoApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserEntity getUser(){
        Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
        assert authenticatedUser != null;
        UserEntity userEntity = (UserEntity) authenticatedUser.getPrincipal();
        return userRepository.findById(userEntity.getId()).orElseThrow();
    }

    @Transactional
    public TodoDTO registerTodo(TodoDTO todoDTO) {
        TodoEntity todoEntity = modelMapper.map(todoDTO, TodoEntity.class);
        UserEntity user = getUser();
        todoEntity.setUser(user);
        TodoEntity todo =  todoRepository.save(todoEntity);
        return modelMapper.map(todo, TodoDTO.class);
    }

    public TodoDTO getTodoById(String todoId) throws Exception {
        TodoEntity todo = todoRepository.findById(todoId).orElseThrow(()-> new Exception("Todo Not found"));
        return modelMapper.map(todo, TodoDTO.class);
    }

    public List<TodoDTO> getAllTodos(){
        UserEntity user = getUser();
        List<TodoEntity> todos =todoRepository.findAllByUserId(user.getId());
        return  todos
                .stream()
                .map(todo -> modelMapper.map(todo, TodoDTO.class) )
                .collect(Collectors.toList());
    }
}
