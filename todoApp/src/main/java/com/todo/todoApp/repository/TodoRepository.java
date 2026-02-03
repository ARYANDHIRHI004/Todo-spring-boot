package com.todo.todoApp.repository;

import com.todo.todoApp.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, String> {
    List<TodoEntity> findAllByUserId(String id);
}
