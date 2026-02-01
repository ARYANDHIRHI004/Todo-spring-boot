package com.todo.todoApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table
@Setter
@Getter
public class SubTodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String topic;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    private TodoEntity todoEntity;


}
