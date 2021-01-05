package jpa;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CitiesRepository extends MongoRepository<City, String> {
}
