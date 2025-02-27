package org.example.springpractice.ToDoList.Service;

import lombok.AllArgsConstructor;
import org.example.springpractice.ToDoList.Repository.ToDoRepository;
import org.example.springpractice.ToDoList.model.Status;
import org.example.springpractice.ToDoList.model.ToDo;
import org.example.springpractice.ToDoList.utils.IdService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final IdService idService;

    public List<ToDo> getAllToDos() {
       return toDoRepository.findAll();
    }

    public ToDo addToDo(ToDo newtoDo) {
        String id = idService.generateId();
        ToDo newToDoWithUUID = newtoDo.withId(id);
        return toDoRepository.save(newToDoWithUUID);
    }
//
//    public ToDo findById(String id) {
//        return toDoRepository.findById(id).get();
//    }
//
    public ToDo updateToDo(String id, ToDo updatedToDo) {
        ToDo update = new ToDo(id, updatedToDo.description(), updatedToDo.status());
        return toDoRepository.save(update);
    }
//
//    public void deleteById(String id) {
//        toDoRepository.deleteById(id);
//    }
}
