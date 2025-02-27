package org.example.springpractice.ToDoList.Repository;


import org.example.springpractice.ToDoList.model.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDo, String> {
}
