package org.example.springpractice.ToDoList.model;

public record ToDoRequestDto(
        String description,
        Status status
) {

}
