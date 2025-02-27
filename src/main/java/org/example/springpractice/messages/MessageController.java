package org.example.springpractice.messages;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final List<Message> messages = new ArrayList<>();

    @PostMapping
    public Message postMessage(@RequestBody Message message){
        messages.add(message);
        return message;
    }

    @GetMapping
    public List<Message> getMessages(){
        return messages;
    }

    @DeleteMapping("delete/{id}")
    public void deleteMessageById(@PathVariable String id){
        messages.removeIf(message -> message.getId().equals(id));
    }
}
