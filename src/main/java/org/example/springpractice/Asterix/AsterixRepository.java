package org.example.springpractice.Asterix;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AsterixRepository extends MongoRepository<Character,String> {
}
