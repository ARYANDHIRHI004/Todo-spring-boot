package com.todo.todoApp.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubTodoDTO {

    private String id;
    private String topic;
    private String description;
    private String todoId;
}
