package org.example.springpractice.ToDoList.exception;

import java.time.LocalDateTime;

public record ErrorMessage(
        String message,
        LocalDateTime timestamp
) {
}
