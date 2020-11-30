package ru.itis.mongohateoas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.mongohateoas.entities.City;

public interface CitiesRepository extends MongoRepository<City, String> {
}
