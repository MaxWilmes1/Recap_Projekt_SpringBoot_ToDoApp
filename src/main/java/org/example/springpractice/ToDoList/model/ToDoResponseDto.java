package org.example.springpractice.ToDoList.model;

import lombok.Builder;

@Builder
public record ToDoResponseDto(
        String id,
        String description,
        Status status
) {
}
