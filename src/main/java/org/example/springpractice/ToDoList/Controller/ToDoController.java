package org.example.springpractice.ToDoList.Controller;

import lombok.AllArgsConstructor;
import org.example.springpractice.ToDoList.Service.ToDoService;
import org.example.springpractice.ToDoList.model.ToDo;
import org.example.springpractice.ToDoList.model.ToDoRequestDto;
import org.example.springpractice.ToDoList.model.ToDoResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todo")
@AllArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping
    public List<ToDo> getAllToDos(){
        return toDoService.getAllToDos();
    }

    @PostMapping
    public ToDoResponseDto addToDo(@RequestBody ToDoRequestDto newToDoRequestDto){
        ToDo newToDo = ToDo.builder()
                .description(newToDoRequestDto.description())
                .status(newToDoRequestDto.status())
                .build();
        ToDo savedToDo = toDoService.addToDo(newToDo);
        return new ToDoResponseDto(
                savedToDo.id(),
                savedToDo.description(),
                savedToDo.status()
        );
    }
//
//    @GetMapping("/{id}")
//    public ToDo findById(@PathVariable String id){
//        return toDoService.findById(id);
//    }
//
//    @PutMapping("/{id}")
//    public ToDo updateById(@PathVariable String id, @RequestBody ToDo updatedToDo){
//        return toDoService.updateById(id, updatedToDo);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteById(@PathVariable String id){
//        toDoService.deleteById(id);
//    }

}
