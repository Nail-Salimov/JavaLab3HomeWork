package ru.itis.mongohateoas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.mongohateoas.entities.Auto;

public interface AutoRepository extends MongoRepository<Auto, String> {
}
