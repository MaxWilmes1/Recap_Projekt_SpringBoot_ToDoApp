package org.example.springpractice.ToDoList.Controller;

import com.jayway.jsonpath.JsonPath;
import org.example.springpractice.ToDoList.model.ToDo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ToDoIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        //GIVEN

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                //THEN
                .andExpect(status().isOk()).andExpect(content().json("""
                            [ ]
                        """));
    }

    @Test
    void whenPost1Todo_ThenGet1ToDos() throws Exception {
        //GIVEN
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                          "description": "Test123",
                          "status": "DONE"
                        }
                        """))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                         {
                           "description": "Test123",
                           "status": "DONE"
                          }
                        """))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }


}