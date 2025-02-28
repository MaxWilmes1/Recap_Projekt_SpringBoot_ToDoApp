package org.example.springpractice.ToDoList.Service;

import org.example.springpractice.ToDoList.Repository.ToDoRepository;
import org.example.springpractice.ToDoList.model.Status;
import org.example.springpractice.ToDoList.model.ToDo;
import org.example.springpractice.ToDoList.utils.IdService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToDoServiceTest {

    ToDoRepository mocktoDoRepository = mock(ToDoRepository.class);
    IdService mockIdService = mock(IdService.class);
    ToDoService toDoService = new ToDoService(mocktoDoRepository, mockIdService);

    @Test
    void getAllToDos() {
        //GIVEN
        ToDo t1 = new ToDo( "1","Test", Status.OPEN);
        when(mocktoDoRepository.findAll()).thenReturn(List.of(t1));
        //WHEN
        List<ToDo> actual = toDoService.getAllToDos();
        //THEN
        verify(mocktoDoRepository).findAll();
        assertEquals(List.of(t1),actual);
    }

    @Test
    void addToDo() {
        //GIVEN
        ToDo noId = ToDo.builder()
                .description("test")
                .status(Status.OPEN)
                .build();
        ToDo withId = noId.withId("42");
        when(mockIdService.generateId()).thenReturn("42");
        when(mocktoDoRepository.save(withId)).thenReturn(withId);
        //WHEN
        ToDo actual = toDoService.addToDo(noId);
        //THEN
        verify(mockIdService).generateId();
        verify(mocktoDoRepository).save(withId);
        assertEquals(withId,actual);
    }

    @Test
    void updateToDo() {
        //GIVEN
        String id = "1234";
        ToDo oldTodo = ToDo.builder()
                .description("test")
                .status(Status.OPEN)
                .build();
        ToDo updatedTodo = ToDo.builder()
                .id("1234")
                .description("test")
                .status(Status.OPEN)
                .build();
        when(mocktoDoRepository.save(updatedTodo)).thenReturn(updatedTodo);
        //WHEN
        ToDo actual = toDoService.updateToDo(id, oldTodo);

        //THEN
        verify(mocktoDoRepository).save(updatedTodo);
        assertEquals(updatedTodo, actual);

    }

    @Test
    void findByIdTest_WhenValidId_ThenReturnToDo() {
        //GIVEN
        ToDo t1 = new ToDo("1", "test", Status.OPEN);
        when(mocktoDoRepository.findById("1")).thenReturn(Optional.of(t1));
        //WHEN
        ToDo actual = toDoService.findById("1");
        //THEN
        verify(mocktoDoRepository).findById("1");
        assertEquals(t1,actual);
    }

    @Test
    void findByIdTest_WhenIdInvalid_ThenNoSuchElementException() {
        //GIVEN
        when(mocktoDoRepository.findById("1")).thenReturn(Optional.empty());
        //WHEN
        assertThrows(NoSuchElementException.class, ()-> toDoService.findById("1"));
        //THEN
        verify(mocktoDoRepository).findById("1");

    }

    @Test
    void deleteByIdTest() {
        //GIVEN
        String id = "1";
        doNothing().when(mocktoDoRepository).deleteById(id);
        //WHEN
        toDoService.deleteById(id);
        //THEN
        verify(mocktoDoRepository).deleteById(id);
    }
}