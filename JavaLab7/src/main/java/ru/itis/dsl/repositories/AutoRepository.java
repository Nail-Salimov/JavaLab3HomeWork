package ru.itis.dsl.repositories;

import ru.itis.dsl.entities.Auto;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AutoRepository extends MongoRepository<Auto, String> {
}
