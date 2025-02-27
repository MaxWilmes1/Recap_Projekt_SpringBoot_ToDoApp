package org.example.springpractice.ToDoList.Controller;

import com.jayway.jsonpath.JsonPath;
import org.example.springpractice.ToDoList.Repository.ToDoRepository;
import org.example.springpractice.ToDoList.model.Status;
import org.example.springpractice.ToDoList.model.ToDo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ToDoIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ToDoRepository toDoRepository;

    @Test
    @DirtiesContext
    void getAll() throws Exception {
        //GIVEN

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            [ ]
                        """));
    }

    @Test
    @DirtiesContext
    void whenPost1Todo_ThenGet1ToDos() throws Exception {
        //GIVEN
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "description": "Test123",
                                  "status": "OPEN"
                                }
                                """))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                         {
                           "description": "Test123",
                           "status": "OPEN"
                          }
                        """))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    void updateToDo() throws Exception {
        //GIVEN
        ToDo todo = new ToDo("123", "test", Status.IN_PROGRESS);
        toDoRepository.save(todo);
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.put("/api/todo/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                 {
                                    "description": "Test123",
                                    "status": "IN_PROGRESS"
                                  }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "id": "123",
                        "description": "Test123",
                        "status": "IN_PROGRESS"
                        }
                        """));

    }

}