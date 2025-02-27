package org.example.springpractice.ToDoList.model;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record ToDo(
        String id,
        String description,
        String status
) {
}
