package com.todo.todoApp.repository;

import com.todo.todoApp.entity.SubTodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubTodoRepository extends JpaRepository<SubTodoEntity, String> {
    SubTodoEntity findByTopic(String topic);
}
