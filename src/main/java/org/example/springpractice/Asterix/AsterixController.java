package org.example.springpractice.Asterix;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asterix")
@AllArgsConstructor
public class AsterixController {

    private final AsterixRepository repo;

    @GetMapping("/characters")
    public List<Character> getCharacters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String profession) {

        List<Character> allCharacters = repo.findAll();

        return allCharacters.stream()
                .filter(character -> name == null || character.name().equalsIgnoreCase(name))
                .filter(character -> age == null || character.age() == age)
                .filter(character -> profession == null || character.profession().equalsIgnoreCase(profession))
                .toList();
    }

    @PostMapping("/characters")
    public Character postCharacter(@RequestBody Character newCharacter){
        repo.save(newCharacter);
        return newCharacter;
    }

    @DeleteMapping("/characters/{id}")
    public void deleteCharacter(@PathVariable String id){
        repo.deleteById(id);
    }

    @PutMapping("/characters/{id}")
    public ResponseEntity<Character> changeCharacter(@PathVariable String id, @RequestBody Character updatedCharacter) {
        return repo.findById(id)
                .map(existingCharacter -> {
                    // Neue Character-Instanz mit der bestehenden ID und aktualisierten Werten erstellen
                    Character updated = new Character(
                            existingCharacter.id(), // ID beibehalten
                            updatedCharacter.name(),
                            updatedCharacter.age(),
                            updatedCharacter.profession()
                    );
                    repo.save(updated);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
