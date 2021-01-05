package ru.itis.dsl.repositories;

import ru.itis.dsl.entities.City;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CitiesRepository extends MongoRepository<City, String> {
}
